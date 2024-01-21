package Bagni;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;

public class BagniCreazione extends Thread{
    public static Object tifosoLock = new Object();
    public static List <Toilet> donne = new ArrayList<>();
    public static List<Toilet> uomini = new ArrayList<>();
    public BagniCreazione()  {
        int id = 0;
        //creo 3 bagni per uomini e 3 per donne
        for (int i = 0; i < 20; i++) {
            donne.add(new Toilet("donna"));
            uomini.add(new Toilet("uomo"));
        }

        int tempo=650;
        //creo i bagni
        Bagno b = new Bagno(donne, uomini); //creo bagno
        b.start();

        // Creo un nuovo thread per gestire l'attesa del tifoso
        new Thread(() -> {
            synchronized(tifosoLock) {
                while(true) {
                    if (b.haRotto()) {
                        System.out.println("I bagni sono stati puliti ");
                        break;
                    } else {
                        try {
                            tifosoLock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }).start();
    }
}