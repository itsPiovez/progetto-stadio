package Ristorante;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;


// Interfaccia per gestire il callback di utente uscito
interface UtenteUscitoCallback {
    void utenteUscito();

    int getClientsExited();
}

// Implementazione di UtenteUscitoCallback
public class UtenteUscitoHandler implements Ristorante.UtenteUscitoCallback {
    private int clientsExited = 0;
    private final Object lock = new Object();  // Oggetto di sincronizzazione

    @Override
    public void utenteUscito() {
        synchronized (lock) {
            clientsExited++;
        }
    }

    public int getClientsExited() {
        synchronized (lock) {
            return clientsExited;
        }
    }
}


