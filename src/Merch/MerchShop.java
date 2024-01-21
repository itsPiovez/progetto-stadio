package Merch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

import static Merch.Merch.clientiThreads;

public class MerchShop {
    private static final List<String> Oggetti = CreazioneOggetti();
    private static final List<Double> PrezzoOggetti = CreazionePrezziOggetti();
    private boolean isOpen = false;
    private Semaphore semaphore = new Semaphore(10); // Numero massimo di clienti che possono essere serviti contemporaneamente

    public synchronized void Apertura() {
        if (!isOpen) {
            System.out.println("\nIl merch shop sta preparando ed è pronto ad aprire.");
            isOpen = true;
            System.out.println("Il merch shop è operativo.\n");
            System.out.println("\u001B[34m"+"----------------------------------------"+"\u001B[0m");
            // Stampa lista prezzi con la prima lettera maiuscola
            System.out.println("Lista dei prezzi Merch:");
            for (int i = 0; i < Oggetti.size(); i++) {
                String oggetto = Oggetti.get(i);
                oggetto = Character.toUpperCase(oggetto.charAt(0)) + oggetto.substring(1);
                System.out.println(oggetto + ": €" + FormatoPrezzo(PrezzoOggetti.get(i)));
            }
            System.out.println("\u001B[34m"+"----------------------------------------"+"\u001B[0m");
            System.out.println();
            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){
            }
            notifyAll();
        }
    }

    public synchronized boolean isOpen() {
        return isOpen;
    }

    public synchronized void waitForClient() {
        while (clientiThreads.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // restore interrupted status
            }
        }
    }
    public void ServizioCliente(String NumeroCliente) {
        System.out.println(NumeroCliente+ " è arrivato al merch shop.");

        double TotaleCosti = 0.0;
        Random random = new Random();

        for (String selectedItem : Oggetti) {
            int Quantita = random.nextInt(3) + 1; // Quantità casuale tra 1 e 3
            double PrezzoOggetto = PrezzoOggetti.get(Oggetti.indexOf(selectedItem));
            double PrezzoTotale = PrezzoOggetto * Quantita;

            if (random.nextBoolean()) {
                System.out.println(NumeroCliente + " vuole comprare " + Quantita + " " + (Quantita > 1 ? Plurale(selectedItem) : selectedItem));


                // Modifica qui: stampa il costo per l'oggetto
                System.out.println(NumeroCliente + " spende €" + FormatoPrezzo(PrezzoTotale) +
                        " (costo totale " + (Quantita > 1 ? Plurale(selectedItem) : selectedItem) + ")");

                TotaleCosti += PrezzoTotale;
            }
        }

        // Modifica qui: stampa il totale pagato dal cliente
        System.out.println(NumeroCliente + " spende complessivamente €" + FormatoPrezzo(TotaleCosti));
        System.out.println(NumeroCliente + " è uscito dallo shop.");

        semaphore.release(); // Rilascia il permesso
    }

    public synchronized void ChiudiMerchShop() {
        isOpen = false;
        System.out.println("Il merch shop chiude.");
    }

    private static List<String> CreazioneOggetti() {
        List<String> oggetti = new ArrayList<>();
        oggetti.add("berretto");
        oggetti.add("maglietta");
        oggetti.add("sciarpa");
        oggetti.add("bandiera");
        oggetti.add("pantaloncino");
        oggetti.add("trombetta");
        oggetti.add("felpa");
        return oggetti;
    }

    private static List<Double> CreazionePrezziOggetti() {
        List<Double> prezzi = new ArrayList<>();
        prezzi.add(10.0);
        prezzi.add(15.0);
        prezzi.add(20.0);
        prezzi.add(25.0);
        prezzi.add(18.0);
        prezzi.add(12.0);
        prezzi.add(30.0);
        return prezzi;
    }

    private static String Plurale(String oggetti) {
        if (oggetti.endsWith("o")) {
            return oggetti.substring(0, oggetti.length() - 1) + "i";
        } else if (oggetti.endsWith("a")) {
            return oggetti.substring(0, oggetti.length() - 1) + "e";
        } else {
            return oggetti + "i";
        }
    }

    private static String FormatoPrezzo(double price) {
        return String.format("%.2f", price).replace(".", ",");
    }
}
