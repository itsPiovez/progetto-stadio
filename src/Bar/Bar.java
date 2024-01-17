package Bar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Bar {
    private boolean aperto = false;
    private List<String> menu = new ArrayList<>();
    private Object monitor = new Object();

    public Bar() {
        // Aggiungi alcuni elementi al menu
        menu.add("Birra");
        menu.add("Acqua");
        menu.add("Panino");
        menu.add("Coca-Cola");
        menu.add("Patatine");
    }

    public List<String> getMenu() {
        return menu;
    }

    public synchronized void apri() {
        if (!aperto) {
            System.out.println("Il bar sta pulendo e preparando. Pronto ad aprire.");
            aperto = true;
            notifyAll();
        }
    }

    public synchronized void chiudi() {
        System.out.println("Il bar sta chiudendo.");
        aperto = false;
    }

    public void servireCliente(String cliente, String ordine) {
        while (!aperto) {
            try {
                System.out.println("Il bar è chiuso. Aspettando l'apertura.");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Benvenuto, " + cliente + "! Il bar è aperto.");

        if (getMenu().contains(ordine)) {
            System.out.println("Servendo " + ordine + " a " + cliente + ".");
            consumaOrdine(cliente, ordine);
        } else {
            System.out.println("Spiacenti, " + ordine + " non è disponibile nel menu.");
        }

        // Aggiunta sincronizzazione per gestire l'uscita in ordine
        synchronized (monitor) {
            System.out.println(cliente + " ha finito di consumare l'ordine. Arrivederci, " + cliente + "!");
            monitor.notify(); // Notifica che il cliente ha terminato
        }
    }

    private void consumaOrdine(String cliente, String ordine) {
        try {
            // Simula il tempo di consumo
            System.out.println(cliente + " si siede e sta consumando " + ordine + ".");
            Thread.sleep(2000); // Tempo di consumo simulato: 2 secondi (ridotto per migliorare le prestazioni)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Object getMonitor() {
        return monitor;
    }
}

