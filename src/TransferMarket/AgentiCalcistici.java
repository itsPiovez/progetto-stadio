package TransferMarket;

import java.text.NumberFormat;
import java.util.*;

public class AgentiCalcistici {
    private Trattativa trattativa;
    private boolean trattativaAccettata;

    public synchronized void negoziaTrattativa(Club clubVenditore, Club clubAcquirente, Giocatore giocatore,
                                               int prezzoIniziale, int durataContratto, int salarioGiocatore,
                                               int bonus, int clausolaRescissione) {
        // Verifica che i nomi dei club venditore e acquirente non siano uguali
        if (clubVenditore.getNome().equals(clubAcquirente.getNome())) {
            return;
        }

        trattativa = new Trattativa(clubVenditore, clubAcquirente, giocatore, prezzoIniziale,
                durataContratto, salarioGiocatore, bonus, clausolaRescissione, false);

        System.out.println("Inizia la trattativa per " + giocatore.getNome() +
                " con market value di " + formatCurrency(giocatore.getMarketValue()) +
                " tra " + clubVenditore.getNome() + " e " + clubAcquirente.getNome() +
                ". Richiesta: " + formatCurrency(prezzoIniziale));

        try {
            // Simula il processo di negoziazione
            Thread.sleep(new Random().nextInt(3000) + 2000);

            // Accetta l'offerta con una probabilità del 50%
            if (new Random().nextDouble() <= 0.5) {
                trattativa = new Trattativa(clubVenditore, clubAcquirente, giocatore, prezzoIniziale,
                        durataContratto, salarioGiocatore, bonus, clausolaRescissione, true);
                System.out.println("Trattativa accettata per " + giocatore.getNome() +
                        " tra " + clubVenditore.getNome() + " e " + clubAcquirente.getNome() +
                        ". Contratto: " + formatCurrency(prezzoIniziale));

                // Stampa i dettagli del contratto
                trattativa.stampaDettagliContratto();

                trattativaAccettata = true;
            } else {
                System.out.println("Trattativa fallita per " + giocatore.getNome() +
                        " tra " + clubVenditore.getNome() + " e " + clubAcquirente.getNome());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notify(); // Notifica al thread acquirente che la trattativa è stata accettata o fallita
    }

    public synchronized void completaTrattativa() {
        while (trattativa == null || !trattativa.isSuccesso()) {
            try {
                wait(); // Attendi che la trattativa venga inizializzata e sia accettata dal thread venditore
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Simula il completamento della trattativa
        System.out.println("Trattativa completata con successo per " + trattativa.getGiocatore().getNome() +
                ". " + trattativa.getClubAcquirente().getNome() + " paga " + formatCurrency(trattativa.getPrezzoTrattativa()) + " al " +
                trattativa.getClubVenditore().getNome());

        trattativa = null;
        trattativaAccettata = false;
    }

    public synchronized boolean isTrattativaAccettata() {
        return trattativaAccettata;
    }

    private String formatCurrency(int amount) {
        return NumberFormat.getCurrencyInstance(Locale.ITALY).format(amount);
    }
}