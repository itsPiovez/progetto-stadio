package Ristorante;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.LinkedList;


public class Main {
    public static Ristorante.Coda<Ristorante.Cliente> coda = new Ristorante.Coda<>();
    public static List<Ristorante.Tavolo> tavoli = new ArrayList<>();
    public static Ristorante.Menu menu = new Ristorante.Menu();


    public static void main(String[] args) {
        List<Ristorante.Cameriere> camerieri = new ArrayList<>();
        List<Ristorante.Cuoco> cuochi = new ArrayList<>();
        final int numClients = 100;  // Rendi la variabile finale

        // Creazione dei tavoli
        Random rand = new Random();
        for (int i = 1; i <= 15; i++) {
            tavoli.add(new Ristorante.Tavolo(i, rand.nextInt(4) + 4)); // Range di posti da 5 a 10
        }

        // Creazione dei camerieri
        Ristorante.UtenteUscitoHandler utenteUscitoHandler = new Ristorante.UtenteUscitoHandler();

        for (int i = 1; i <= 5; i++) {
            camerieri.add(new Ristorante.Cameriere("Cameriere " + i, coda, tavoli, utenteUscitoHandler, numClients));
        }

        // Creazione dei cuochi
        for (int i = 1; i <= 2; i++) {
            cuochi.add(new Ristorante.Cuoco("Cuoco " + i, coda, numClients, utenteUscitoHandler));
        }

        // Avvio dei thread
        for (Ristorante.Tavolo tavolo : tavoli) {
            new Thread(tavolo).start();
        }

        for (Ristorante.Cameriere cameriere : camerieri) {
            new Thread(cameriere).start();
        }

        for (Ristorante.Cuoco cuoco : cuochi) {
            new Thread(cuoco).start();
        }

        // Creazione dei clienti e inserimento nella coda
        for (int i = 1; i <= numClients; i++) {
            Ristorante.Cliente cliente = new Ristorante.Cliente("Cliente " + i, tavoli, coda, menu);
            new Thread(cliente).start(); // Avvia il thread del cliente
            utenteUscitoHandler.utenteUscito();
        }
    }
}
