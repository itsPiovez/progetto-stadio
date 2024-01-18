package Bar;

import java.util.LinkedList;

public class Coda<T> {
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
