package Bar;
public class UtenteUscitoHandler implements UtenteUscitoCallback {
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

interface UtenteUscitoCallback {
    void utenteUscito();

    int getClientsExited();
}