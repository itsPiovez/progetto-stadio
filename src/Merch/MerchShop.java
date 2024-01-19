package Merch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MerchShop {
    private static final List<String> Oggetti = CreazioneOggetti();
    private static final List<Double> PrezzoOggetti = CreazionePrezziOggetti();
    private boolean isOpen = false;

    public synchronized void Apertura() {
        if (!isOpen) {
            System.out.println("Il merch shop sta preparando ed è pronto ad aprire.");
            isOpen = true;
            System.out.println("Il merch shop è operativo.");
            notifyAll();
        }
    }

    public synchronized boolean isOpen() {
        return isOpen;
    }

    public synchronized void AspettaClient() {
        while (!isOpen) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }

    public synchronized void ServizioCliente(int NumeroCliente) {
        System.out.println("Cliente " + NumeroCliente + " è arrivato al merch shop.");

        double TotaleCosti = 0.0;
        Random random = new Random();

        for (String selectedItem : Oggetti) {
            // Scelgo se il cliente vuole acquistare l'oggetto
            if (random.nextBoolean()) {
                int Quantita = random.nextInt(3) + 1; // Quantità casuale tra 1 e 3
                double PrezzoOggetto = PrezzoOggetti.get(Oggetti.indexOf(selectedItem));
                double PrezzoTotale = PrezzoOggetto * Quantita;

                System.out.println("Cliente " + NumeroCliente + " vuole comprare " + Quantita + " " + (Quantita > 1 ? Plurale(selectedItem) : selectedItem));
                System.out.println("Prezzo singolo per " + selectedItem + ": €" + FormatoPrezzo(PrezzoOggetto));
                System.out.println("Prezzo totale per " + selectedItem + ": €" + FormatoPrezzo(PrezzoTotale));

                TotaleCosti += PrezzoTotale;
            }
        }

        System.out.println("Cliente " + NumeroCliente + " paga un totale di: €" + FormatoPrezzo(TotaleCosti));
        System.out.println("Il cliente " + NumeroCliente + " è uscito dallo shop.");
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

    private static String Plurale(String oggetti)
    {
        if (oggetti.endsWith("o"))
        {
            return oggetti.substring(0, oggetti.length() - 1) + "i";
        }
        else if (oggetti.endsWith("a"))
        {
            return oggetti.substring(0, oggetti.length() - 1) + "e";
        }
        else
        {
            return oggetti + "i";
        }
    }

    private static String FormatoPrezzo(double price)
    {
        return String.format("%.2f", price).replace(".", ",");
    }
}