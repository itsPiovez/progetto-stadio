package Merch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Merch {
    public static List<Thread> Cliente= new ArrayList<>();

    public static void main(String[] args) {
        MerchShop merchShop = new MerchShop();
        merchShop.Apertura();

        int NumeroClienti = 200;

        Random random = new Random();

        for (int i = 0; i < NumeroClienti; i++) {
            int NumeroCliente = random.nextInt(NumeroClienti) + 1;
            Thread ClienteThread = new ClientiMerch(NumeroCliente, merchShop);
            Cliente.add(ClienteThread);
            ClienteThread.start();
        }

        for (Thread ClienteThread : Cliente) {
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
