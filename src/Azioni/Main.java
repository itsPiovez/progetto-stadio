package Azioni;

import Merch.*;
import Bar.*;
import Ristorante.*;
import Bagni.*;
import TransferMarket.*;
import java.util.List;
import CambioColore.Colore;
import Annunci.*;
import java.util.Scanner;

public class Main {
    public static boolean random;
    public static void main(String[] args)
    {
        Caricamento();

        // Aggiunta gestione dell'input per la domanda sui colori random
        Scanner scanner = new Scanner(System.in);
        char answer = ' ';
        boolean inputValido = false;

        while (!inputValido) {
            try {
                System.out.print("Vuoi visualizzare le azioni dei tifosi con colori random? (S/N): ");
                System.out.flush();  // Flush the output to ensure the prompt is displayed immediately

                answer = scanner.nextLine().charAt(0);

                if (answer == 'S' || answer == 's' || answer == 'N' || answer == 'n') {
                    inputValido = true;
                } else {
                    System.out.println("Inserisci un carattere valido (S/N).");
                }
            } catch (Exception e) {
                System.out.print("");
            }
        }

        if (answer == 'S' ||     answer == 's') {
            System.out.println("Ok, ora puoi vedere le azioni dei tifosi con colori random");
            random = true;
        } else {
            System.out.println("Ok, ora puoi vedere le azioni dei tifosi con colori standard");
            random = false;
        }

        // Chiedi all'utente se vuole entrare nel Transfermarket
        System.out.print("\nVuoi entrare nel Transfermarket? (S/N): ");
        char choice = scanner.nextLine().charAt(0);

        if (choice == 'S' || choice == 's') {
            AgentiCalcistici agentiCalcistici = new AgentiCalcistici(); // Creare un'istanza di AgentiCalcistici
            Transfermarket transfermarket = new Transfermarket();
            transfermarket.start();

            // Attendi che la trattativa del Transfermarket sia completata con successo o entro il timeout
            long timeout = System.currentTimeMillis() + 30000; // Timeout massimo di 30 secondi
            while (!agentiCalcistici.isTrattativaAccettata() && System.currentTimeMillis() < timeout) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (!agentiCalcistici.isTrattativaAccettata()) {
                // Timeout scaduto
            }
        }
        Merch merch = new Merch();
        Caffetteria caffetteria = new Caffetteria();
        Ristorante.RistoranteCreazione ristoranteCreazione = new Ristorante.RistoranteCreazione();
        CreaAnnunci annunci = new CreaAnnunci();
        Bagni.BagniCreazione bagniCreazione = new Bagni.BagniCreazione();
        

        // Ciclo dei tifosi
        List<Tifoso> tot = CreaTifo.CreaTifoso(100);
        if (tot != null && !tot.isEmpty()) {
            for (Tifoso tifoso : tot) {
                AzioneTifoso azioneTifoso = new AzioneTifoso(tifoso.GetNome());
                new Thread(azioneTifoso).start();
            }
        }

        scanner.close();
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
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("");
    }
}


