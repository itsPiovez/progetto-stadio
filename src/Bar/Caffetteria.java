package Bar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Caffetteria {
    public static void main(String[] args) {
        Bar bar = new Bar();
        bar.Apertura();

        int NumeroClienti = 200;
        List<Integer> numeriClienti = new ArrayList<>();
        for (int i = 1; i <= NumeroClienti; i++) {
            numeriClienti.add(i);
        }
        Collections.shuffle(numeriClienti);

        List<Thread> clientiThreads = new ArrayList<>();

        for (int i = 0; i < NumeroClienti; i++) {
            int NumeroCliente = numeriClienti.get(i);
            Thread ClienteThread = new Thread(new ClientiBar(NumeroCliente, bar));
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

        bar.ChiudiBar();
    }
}