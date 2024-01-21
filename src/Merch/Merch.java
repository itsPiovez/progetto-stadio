package Merch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Merch {
    public static MerchShop merchShop = new MerchShop();
    public static List<Integer> numeriClientiTot = new ArrayList<>();
    public static List<Thread> clientiThreads = new ArrayList<>();

    public Merch() {
        merchShop.Apertura();
        List<Integer> numeriClienti = numeriClientiTot;
        int i = 0;
        Collections.shuffle(numeriClienti);
        List<Thread> clientiThreads = Merch.clientiThreads;
        for (Thread ClienteThread : clientiThreads) {
            try {
                ClienteThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
}