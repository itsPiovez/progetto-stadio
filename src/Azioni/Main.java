package Azioni;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import Merch.*;
import Ristorante.*;
import Bagni.*;
import Bar.*;
import TransferMarket.*;
import java.util.List;
import CambioColore.Colore;


public class Main {
    public static void main(String[] args) {
        Transfermarket transfermarket = new Transfermarket();
        transfermarket.start();
        try {
            transfermarket.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        AggiungiSleep();
        Caricamento();

        Merch merch = new Merch();
        Bar bar = new Bar();
        Ristorante.RistoranteCreazione ristoranteCreazione = new Ristorante.RistoranteCreazione();
        Bagni.BagniCreazione bagniCreazione = new Bagni.BagniCreazione();
        List<Tifoso> tot = CreaTifo.CreaTifoso(100);
        if (tot != null && !tot.isEmpty()) {
            // voglio che i tifosi che entrano in questo ciclo vengono sbalzati nella classe azionetifoso
            for (Tifoso tifoso : tot) {
                AzioneTifoso azioneTifoso = new AzioneTifoso(tifoso.GetNome());
                new Thread(azioneTifoso).start();
            }
        }
    }

    public static void AggiungiSleep(){
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void Caricamento() {   //feature Marco
        int numeroIterazioni = 20;

        for (int i = 0; i <= numeroIterazioni; i++) {
            try {
                // Aggiungi un ritardo per rendere più evidente l'effetto
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Pulisci la linea precedente per l'effetto di sovrascrittura
            System.out.print("\r");

            // Calcola la percentuale completata
            int percentuale = (i * 100) / numeroIterazioni;

            // Costruisci la barra di caricamento animata
            StringBuilder loadingBar = new StringBuilder("[");
            for (int j = 0; j < numeroIterazioni; j++) {
                if (j < i) {
                    loadingBar.append("#");
                } else {
                    loadingBar.append(" ");
                }
            }
            loadingBar.append("]");

            // Visualizza la barra di caricamento e la percentuale
            System.out.printf(Colore.getRandomColor("Caricamento in corso: %-20s %d%%"), loadingBar, percentuale);
        }

        System.out.println("\nCaricamento completato!");
        System.out.println("");
        System.out.print(("Benvenuto all'Allianz Stadium") + "\n");
        System.out.println("Stai per assistere alla gestione dei tifosi durante una partita di calcio");
        System.out.println("Iniziamo con il caricamento dei tifosi...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

