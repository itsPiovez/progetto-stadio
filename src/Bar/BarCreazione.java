package Bar;
import java.util.ArrayList;
import java.util.List;

public class BarCreazione {
    public static Bar.Coda<Bar.Cliente> coda = new Bar.Coda<>();
    public static Bar.Menu menu = new Bar.Menu();

    public BarCreazione() {
        List<Barista> baristi = new ArrayList<>();
        final int numClients = 10;

        // Creazione dei baristi
        for (int i = 1; i <= 2; i++) {
            Bar.UtenteUscitoHandler utenteUscitoHandler = new Bar.UtenteUscitoHandler();
            baristi.add(new Barista("Barista " + i, coda, utenteUscitoHandler, numClients));
        }

        // Avvio dei thread
        for (Barista barista : baristi) {
            new Thread(barista).start();
        }

        // Creazione dei clienti e inserimento nella coda
        List<Thread> clientThreads = new ArrayList<>();
        for (int i = 1; i <= numClients; i++) {
            Bar.Cliente cliente = new Bar.Cliente("Cliente " + i, coda, menu);
            Thread clientThread = new Thread(cliente);
            clientThreads.add(clientThread);
            clientThread.start(); // Avvia il thread del cliente
        }

        // Attendi che tutti i clienti siano serviti
        for (Thread clientThread : clientThreads) {
            try {
                clientThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Stampa un messaggio di chiusura quando tutti i clienti sono serviti
        System.out.println("Il bar è chiuso. Tutti i clienti sono stati serviti.");
    }

    public static void main(String[] args) {
        new BarCreazione();
    }
}
