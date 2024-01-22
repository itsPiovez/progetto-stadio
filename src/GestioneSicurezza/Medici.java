package GestioneSicurezza;

import GestioneBiglietteriaNuova.Tifoso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static GestioneBiglietteriaNuova.Main.tifosiGenerati;

public class Medici extends Thread {
    private AtomicInteger tifosiSoccorsi;
    private int numeroTifosiDaSoccorrere;
    private volatile boolean shouldTerminate;
    private static final Random randomGenerator = new Random();


    public Medici(int numeroTifosiDaSoccorrere) {
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
                Thread.sleep(500);

                int count = 0;
                List<Tifoso> tifosiToRemove = new ArrayList<>();
                synchronized (tifosiGenerati) {
                    for (Tifoso tifoso : tifosiGenerati) {
                        if (tifoso.siEFattoMale()) {
                            boolean situazioneGrave = new Random().nextBoolean();

                            if (situazioneGrave) {
                                System.out.println(tifoso.getNome() + " ha subito un infortunio grave. Richiesto intervento urgente dei Medici!");
                            } else {
                                System.out.println(tifoso.getNome() + " si è fatto male. Soccorso immediato da parte dei Medici.");
                            }

                            count++;
                            tifosiToRemove.add(tifoso);
                            tifosiSoccorsi.incrementAndGet();
                        }
                    }
                    tifosiGenerati.removeAll(tifosiToRemove);
                }

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


