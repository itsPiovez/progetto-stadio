package GestioneParcheggio;
import java.util.concurrent.locks.ReentrantLock;

public class Parcheggio {
    private boolean[] postiOccupati;
    private ReentrantLock lock;

    public Parcheggio(int numeroPosti) {
        postiOccupati = new boolean[numeroPosti];
        lock = new ReentrantLock();
    }

    public boolean parcheggia() {
        lock.lock();
        try {
            for (int i = 0; i < postiOccupati.length; i++) {
                if (!postiOccupati[i]) {
                    postiOccupati[i] = true;
                    System.out.println("Auto parcheggiata al posto " + i);
                    return true;
                }
            }
            System.out.println("Il parcheggio è pieno. Impossibile parcheggiare.");
            return false;
        } finally {
            lock.unlock();
        }
    }

    public boolean liberaParcheggio() {
        lock.lock();
        try {
            int i = (int) (Math.random() * postiOccupati.length);
            if (postiOccupati[i]) {
                postiOccupati[i] = false;
                System.out.println("Auto uscita dal posto " + i);
                return true;
            }
            System.out.println("Il posto " + i + " è già libero.");
            return false;
        } finally {
            lock.unlock();
        }
    }

    public int conteggioPostiOccupati() {
        lock.lock();
        try {
            int contatore = 0;
            for (boolean posto : postiOccupati) {
                if (posto) {
                    contatore++;
                }
            }
            return contatore;
        } finally {
            lock.unlock();
        }
    }

    public double percentualeOccupazione() {
        lock.lock();
        try {
            int postiTotali = postiOccupati.length;
            int postiOccupati = conteggioPostiOccupati();
            return ((double) postiOccupati / postiTotali) * 100;
        } finally {
            lock.unlock();
        }
    }

    public void stampaStato() {
        lock.lock();
        try {
            System.out.print("Stato del parcheggio: [");
            for (int i = 0; i < postiOccupati.length; i++) {
                System.out.print(postiOccupati[i] ? "X" : "O");
                if (i < postiOccupati.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        } finally {
            lock.unlock();
        }
    }
}
