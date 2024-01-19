package Bar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
public class Caffetteria {
    public static Bar bar = new Bar();
    public static List<Integer> numeriClienti = new ArrayList<>();
    public static List<Thread> clientiThreads = new ArrayList<>();
    public Caffetteria() {
        bar.Apertura();
        int NumeroClienti = 200;
        List<Integer> numeriClienti = Caffetteria.numeriClienti;
        int i = 0;
        while(numeriClienti.size() < NumeroClienti) {
            i++;
            numeriClienti.add(i);
        }
        List<Thread> clientiThreads = Caffetteria.clientiThreads;
        Collections.shuffle(numeriClienti);
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