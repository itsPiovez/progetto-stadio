    package Bar;
    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.List;
    import java.util.Random;
    import java.util.concurrent.Semaphore;

    import static Merch.Merch.clientiThreads;

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
                System.out.println("\u001B[34m"+"----------------------------------------"+"\u001B[0m");
                // Stampa lista prezzi con la prima lettera maiuscola
                System.out.println("Menu:");
                for (int i = 0; i < Prodotti.size(); i++) {
                    String prodotto = Prodotti.get(i);
                    prodotto = Character.toUpperCase(prodotto.charAt(0)) + prodotto.substring(1);
                    System.out.println(prodotto + ": €" + FormatoPrezzo(PrezzoProdotti.get(i)));
                }
                System.out.println("\u001B[34m"+"----------------------------------------"+"\u001B[0m");
                System.out.println();
                notifyAll();
            }
        }

        public synchronized boolean isOpen() {
            return isOpen;
        }

        public synchronized boolean tryAcquire() {
            return semaphore.tryAcquire();
        }

        public synchronized void release() {
            semaphore.release();
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
            try {
                semaphore.acquire();  // Acquisisce il permesso
                System.out.println(NumeroCliente + " è arrivato al bar.");

                double TotaleCosti = 0.0;
                boolean haAcquistato = false;
                Random random = new Random();

                for (String selectedProduct : Prodotti) {
                    int Quantita = random.nextInt(3) + 1; // Quantità casuale tra 1 e 3
                    double PrezzoProdotto = PrezzoProdotti.get(Prodotti.indexOf(selectedProduct));
                    double PrezzoTotale = PrezzoProdotto * Quantita;

                    if (random.nextBoolean()) {
                        System.out.println(NumeroCliente + " vuole acquistare " + Quantita + " " + selectedProduct);

                        // Modifica qui: stampa il costo per l'oggetto
                        System.out.println(NumeroCliente + " paga " + FormatoPrezzo(PrezzoTotale) + " € " +
                                " (costo totale " + selectedProduct + ")");

                        TotaleCosti += PrezzoTotale;
                        haAcquistato = true;
                    }
                }

                if (haAcquistato) {
                    // Modifica qui: stampa il totale pagato dal cliente
                    System.out.println(NumeroCliente + " spende in totale " + FormatoPrezzo(TotaleCosti) + " €");
                } else {
                    System.out.println(NumeroCliente + " non ha comprato nulla.");
                }

                System.out.println(NumeroCliente + " è uscito dal bar.");

                semaphore.release(); // Rilascia il permesso
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        public synchronized void ChiudiBar() {
            isOpen = false;
            //System.out.println("Il bar è chiuso.");
            notifyAll();  // Notifica tutti i thread in attesa
            semaphore.release(10);  // Rilascia tutti i permessi
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