package Ristorante;

import java.util.LinkedList;

public class Coda<T> {
    private LinkedList<T> list;

    public Coda() {
        list = new LinkedList<T>();
    }

    public T pop() {
        if (list.isEmpty()) {
            return null;
        }
        return this.estrai();
    }

    private synchronized T estrai() {
        return list.removeFirst();
    }

    public synchronized void push(T element) {
        list.addLast(element);
    }

    public synchronized int size() {
        return list.size();
    }
}
