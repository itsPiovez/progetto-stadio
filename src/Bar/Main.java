package Bar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Bar bar = new Bar();

        // Thread del barista
        new Thread(() -> {
            bar.apri();

            ExecutorService executorService = Executors.newCachedThreadPool();

            for (int i = 1; i <= 10; i++) {
                executorService.execute(new Cliente("Cliente" + i, bar));
                try {
                    Thread.sleep(1000); // Aggiungi un ritardo tra i clienti
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            executorService.shutdown();

            // Attendi che tutti i clienti abbiano terminato prima di chiudere il bar
            try {
                synchronized (bar.getMonitor()) {
                    while (executorService.isTerminated()) {
                        bar.getMonitor().wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Chiudi il bar
            bar.chiudi();
        }).start();
    }
}
