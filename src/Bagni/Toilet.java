package Bagni;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Toilet {
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







