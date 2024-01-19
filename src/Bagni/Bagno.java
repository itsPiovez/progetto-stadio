package Bagni;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bagno extends Thread {
    private List<Toilet> donne;
    private List<Toilet> uomini;
    public static Coda<Persona> c = new Coda<Persona>(); //creo coda per i bagni
    Random m = new Random();

    public Bagno(List<Toilet> donne, List<Toilet> uomini) {
        this.donne = donne;
        this.uomini = uomini;
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
        while(true){
        while(c.isEmpty()){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
}

