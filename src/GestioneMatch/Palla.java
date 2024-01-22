package GestioneMatch;

public class Palla {
    private volatile boolean pallaAcquisita = false;
    public synchronized void acquisisciPalla(String giocatore) {
        while (pallaAcquisita) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        pallaAcquisita = true;
    }

    public synchronized void rilasciaPalla(String giocatore) {
        pallaAcquisita = false;
        notifyAll();
    }
}
