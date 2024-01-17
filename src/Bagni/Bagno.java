package Bagni;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Toilet {
    private volatile int visitatori;
    private Condition condition;
    private String tipo; //donna o uomo
    private ReentrantLock lock;
    private boolean pulito;

    public Toilet(String t) {
        this.visitatori = 0;
        this.tipo = t;
        this.lock = new ReentrantLock();
        this.condition = lock.newCondition();
        this.pulito = true;
    }

    public synchronized void pulisci(Toilet b) {
        System.out.println("\nIl bagno per " + tipo + " è stato pulito\n");
        try {
            Thread.sleep(1000);
            setVisitatori(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.lock();
        try {
            pulito = true;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public synchronized void setVisitatori(int visitatori) {
        this.visitatori = visitatori;
    }
    public synchronized int getVisitatori() {
        return visitatori;
    }

    public void usa_bagno(String TID, Random r) {
        lock.lock();
        try {
            while (!pulito) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            visitatori++;
            System.out.println("La Persona " + TID + " è entrata nel bagno per " + tipo + " [visitatore n. " + visitatori + "]");
            try {
                Thread.sleep(r.nextInt(5) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("La Persona " + TID + " sta uscendo dal bagno per " + tipo);

            if (visitatori > 3) {
                pulito = false;
                pulisci(this);
            }
        } finally {
            lock.unlock();
        }
    }
}

class Coda<T> {
    private LinkedList<T> list;
    private boolean isFinished = false;


    public Coda() {
        list = new LinkedList<T>();
    }

    public T pop() {
        while (list.isEmpty() && isFinished) {
            try {
                Thread.sleep(10);
                System.out.println("dc");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  // Ripristina l'interruzione
            }
        }
        return list.isEmpty() ? null : this.estrai();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
    public void setFinished() {
        isFinished = !isFinished;
    }
    // Restituisce la dimensione della lista (numero di persone in coda)
    public synchronized int lenght() {
        return list.size();
    }

    public boolean isFinished() {
        return isFinished;
    }

    private synchronized T estrai() {
        return list.removeFirst();
    }

    public synchronized void push(T element) {
        list.addLast(element);
    }
}

public class Bagno extends Thread {
    private List<Toilet> donne;
    private List<Toilet> uomini;
    private int id;
    private Coda<Persona> c;
    Random m = new Random();

    public Bagno(List<Toilet> donne, List<Toilet> uomini, int id, Coda<Persona> c) {
        this.donne = donne;
        this.uomini = uomini;
        this.id = id;
        this.c = c;
    }

    private synchronized Persona estrai() {
        return c.pop();
    }
    public boolean haRotto(){
        return c.isFinished();
    }
    public void finish(){
        c.setFinished();
    }

    public void Scrivi(){
        System.out.println("I bagni sono stati puliti ");
    }

    @Override
    public void run() {
        while (!c.isEmpty() || !c.isFinished()) {
            Persona p = estrai();
            if (p == null) {
                finish();
                Scrivi();
                break;
            }
            List<Toilet> toilets = p.getTipo().equals("uomo") ? uomini : donne;
            int i = m.nextInt(3);
            p.setToilet(toilets.get(i));
            p.setBagno(this);
            try {
                p.start();
            } catch (Exception e) {
                System.err.println("Error starting thread: " + e.getMessage());
            }
        }
    }
}

class Persona extends Thread {
    String tipo; // donna o uomo
    private int numero;
    public Persona( String tipo, int numero) {
        this.numero = numero;
        this.tipo = tipo;
    }
    Random r = new Random(); // durata uso bagno
    private Toilet t;
    private Bagno b;
    public String getTipo() {
        return tipo;
    }
    public void setToilet(Toilet t) {
        this.t = t;
    }
    public Toilet getToilet() {
        return t;
    }
    public void setBagno(Bagno b) {
        this.b = b;
    }

    @Override
    public void run() {
        String TID = tipo +" "+ numero;
        t.usa_bagno(TID, r);
        try {
            Thread.sleep(r.nextInt(5) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

