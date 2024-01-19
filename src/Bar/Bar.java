package Bar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Bar {
    private static final List<String> Prodotti = CreazioneProdotti();
    private static final List<Double> PrezzoProdotti = CreazionePrezziProdotti();
    private boolean isOpen = false;
    private Semaphore semaphore = new Semaphore(10); // Numero massimo di clienti che possono essere serviti contemporaneamente

    public synchronized void Apertura() {
        if (!isOpen) {
            System.out.println("\nIl bar sta preparando ed è pronto ad aprire.");
            isOpen = true;
            System.out.println("Il bar è aperto.\n");
            // Stampa lista prezzi con la prima lettera maiuscola
            System.out.println("Menu:");
            for (int i = 0; i < Prodotti.size(); i++) {
                String prodotto = Prodotti.get(i);
                prodotto = Character.toUpperCase(prodotto.charAt(0)) + prodotto.substring(1);
                System.out.println(prodotto + ": €" + FormatoPrezzo(PrezzoProdotti.get(i)));
            }
            System.out.println();
            notifyAll();
        }
    }

    public synchronized boolean isOpen() {
        return isOpen;
    }

    public void AspettaClient() throws InterruptedException {
        semaphore.acquire(); // Acquisisci un permesso
        while (!isOpen) {
            wait();
        }
    }

    public void ServizioCliente(int NumeroCliente) {
        System.out.println("Cliente " + NumeroCliente + " è arrivato al bar.");

        double TotaleCosti = 0.0;
        Random random = new Random();

        for (String selectedProduct : Prodotti) {
            int Quantita = random.nextInt(3) + 1; // Quantità casuale tra 1 e 3
            double PrezzoProdotto = PrezzoProdotti.get(Prodotti.indexOf(selectedProduct));
            double PrezzoTotale = PrezzoProdotto * Quantita;

            if (random.nextBoolean()) {
                System.out.println("Cliente " + NumeroCliente + " vuole acquistare " + Quantita + " " + selectedProduct);

                // Modifica qui: stampa il costo per l'oggetto
                System.out.println("Cliente " + NumeroCliente + " paga" + FormatoPrezzo(PrezzoTotale) +" € "+
                        " (costo totale " + selectedProduct + ")");

                TotaleCosti += PrezzoTotale;
            }
        }

        // Modifica qui: stampa il totale pagato dal cliente
        System.out.println("Cliente " + NumeroCliente + " spende in totale " + FormatoPrezzo(TotaleCosti)+" €");
        System.out.println("Il cliente " + NumeroCliente + " è uscito dal bar.");

        semaphore.release(); // Rilascia il permesso
    }

    public synchronized void ChiudiBar() {
        isOpen = false;
        System.out.println("Il bar è chiuso.");
    }

    private static List<String> CreazioneProdotti() {
        List<String> prodotti = new ArrayList<>();
        prodotti.add("patatine");
        prodotti.add("bibita");
        prodotti.add("panino");
        prodotti.add("birra");
        return prodotti;
    }

    private static List<Double> CreazionePrezziProdotti() {
        List<Double> prezzi = new ArrayList<>();
        prezzi.add(5.0);
        prezzi.add(3.0);
        prezzi.add(7.0);
        prezzi.add(4.0);
        return prezzi;
    }

    private static String FormatoPrezzo(double price) {
        return String.format("%.2f", price).replace(".", ",");
    }
}