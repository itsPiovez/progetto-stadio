package GestioneSicurezza;

import GestioneBiglietteriaNuova.Tifoso;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Steward extends Thread {
    private List<Tifoso> tifosi;
    private AtomicInteger tifosiCacciati;
    private int numeroTifosiDaGenerare;
    private volatile boolean shouldTerminate;
    private static final Random randomGenerator = new Random();


    public Steward(List<Tifoso> tifosi, int numeroTifosiDaGenerare) {
        this.tifosi = tifosi;
        this.tifosiCacciati = new AtomicInteger(0);
        this.numeroTifosiDaGenerare = numeroTifosiDaGenerare;
        this.shouldTerminate = false;
    }

    public void terminate() {
        shouldTerminate = true;
    }

    @Override
    public void run() {
        while (!shouldTerminate && tifosiCacciati.get() < numeroTifosiDaGenerare) {
            try {
                // Attendi l'arrivo di 200 tifosi
                Thread.sleep(200);

                // Effettua il controllo sugli oggetti non permessi nello zaino
                int count = 0;
                synchronized (tifosi) {
                    for (Tifoso tifoso : tifosi) {
                        if (tifoso.haOggettoNonPermesso()) {
                            // Simula variabilità nella gravità della situazione
                            boolean situazioneGrave = randomGenerator.nextBoolean();

                            if (situazioneGrave) {
                                System.out.println(tifoso.getNome() + " ha un oggetto pericoloso. Richiesto intervento urgente dei Steward!");
                            } else {
                                System.out.println(tifoso.getNome() + " cacciato dagli Steward a causa di un oggetto non permesso.");
                            }

                            count++;
                            tifosiCacciati.incrementAndGet();
                        }
                    }
                }

                // Simula il tempo impiegato per eseguire i controlli
                Thread.sleep(5000);

                if (count > 0) {
                    System.out.println(count + " tifosi cacciati dagli Steward.");
                } else {
                    System.out.println("Nessun tifoso cacciato dagli Steward al momento.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
