package Bar;
import Bagni.Coda;
import Bagni.Persona;
import Bagni.Toilet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
public class Caffetteria {
    public static Bar bar = new Bar();
    public static Coda<ClientiBar> clientiThreads = new Coda<>();
    public Caffetteria() {
        bar.Apertura();

        // Aggiungi un timer o un criterio di terminazione basato su eventi o tempo
        long startTime = System.currentTimeMillis();
        long timeout = startTime + 3000; // Esempio: esegui per massimo 60 secondi

        while (System.currentTimeMillis() < timeout) {
            if (clientiThreads.isEmpty()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                ClientiBar clienteThread = clientiThreads.pop();
                clienteThread.start();
                try{
                    clienteThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Chiusura del bar alla fine del ciclo o in base a un criterio specifico
        bar.ChiudiBar();
    }
}
