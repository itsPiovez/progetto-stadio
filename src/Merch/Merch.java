package Merch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Merch {
    public static MerchShop merchShop = new MerchShop();

    public Merch() {
        merchShop.Apertura();
        int NumeroClienti = 200;
        List<Integer> numeriClienti = new ArrayList<>();
        for (int i = 1; i <= NumeroClienti; i++) {
            numeriClienti.add(i);
        }
        Collections.shuffle(numeriClienti);

        List<Thread> clientiThreads = new ArrayList<>();

        for (int i = 0; i < NumeroClienti; i++) {
            int NumeroCliente = numeriClienti.get(i);
            Thread ClienteThread = new Thread(new ClientiMerch(NumeroCliente, merchShop));
            clientiThreads.add(ClienteThread);
            ClienteThread.start();
        }

        for (Thread ClienteThread : clientiThreads) {
            try {
                ClienteThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }

        merchShop.ChiudiMerchShop();
    }
}