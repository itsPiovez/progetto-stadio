package GestioneSicurezza;

import GestioneBiglietteriaNuova.Tifoso;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Medici extends Thread {
    private List<Tifoso> tifosi;
    private AtomicInteger tifosiSoccorsi;
    private int numeroTifosiDaSoccorrere;
    private volatile boolean shouldTerminate;
    private static final Random randomGenerator = new Random();


    public Medici(List<Tifoso> tifosi, int numeroTifosiDaSoccorrere) {
        this.tifosi = tifosi;
        this.tifosiSoccorsi = new AtomicInteger(0);
        this.numeroTifosiDaSoccorrere = numeroTifosiDaSoccorrere;
        this.shouldTerminate = false;
    }

    public void terminate() {
        shouldTerminate = true;
    }

    @Override
    public void run() {
        while (!shouldTerminate) {
            try {
                // Attendi l'arrivo di 500 tifosi
                Thread.sleep(500);

                // Effettua il soccorso ai tifosi che si sono fatti male
                int count = 0;
                synchronized (tifosi) {
                    for (Tifoso tifoso : tifosi) {
                        if (tifoso.siEFattoMale()) {
                            // Simula variabilità nella gravità della situazione
                            boolean situazioneGrave = new Random().nextBoolean();

                            if (situazioneGrave) {
                                System.out.println(tifoso.getNome() + " ha subito un infortunio grave. Richiesto intervento urgente dei Medici!");
                            } else {
                                System.out.println(tifoso.getNome() + " si è fatto male. Soccorso immediato da parte dei Medici.");
                            }

                            count++;
                            tifosiSoccorsi.incrementAndGet();
                        }
                    }
                }

                // Simula il tempo impiegato per soccorrere i tifosi
                Thread.sleep(5000);

                if (count > 0) {
                    System.out.println(count + " tifosi soccorsi.");
                } else {
                    System.out.println("Nessun tifoso ha bisogno di soccorso al momento.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


