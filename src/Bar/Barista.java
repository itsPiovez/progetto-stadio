package Bar;

import java.util.ArrayList;
import java.util.List;

public class Barista implements Runnable {
    private String nome;
    private Bar.Coda<Bar.Cliente> coda;
    private Bar.UtenteUscitoCallback utenteUscitoCallback;
    private final int totalClients;

    public Barista(String nome, Bar.Coda<Bar.Cliente> coda, Bar.UtenteUscitoCallback utenteUscitoCallback, int totalClients) {
        this.nome = nome;
        this.coda = coda;
        this.utenteUscitoCallback = utenteUscitoCallback;
        this.totalClients = totalClients;
    }

    @Override
    public void run() {
        System.out.println("Il barista " + nome + " sta sistemando il bar e lo pulisce.");
        // Altre operazioni di preparazione del bar

        while (utenteUscitoCallback.getClientsExited() < totalClients) {
            Bar.Cliente cliente = coda.pop();
            if (cliente == null) {
                break;  // Termina il thread se non ci sono più clienti
            }

            System.out.println("Barista " + nome + " serve il cliente " + cliente.getNome());
            // Simula la preparazione del servizio al cliente
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Il cliente lascia il bar dopo aver ordinato e pagato
            utenteUscitoCallback.utenteUscito();
        }

        System.out.println("Il barista " + nome + " ha chiuso il bar.");
    }
}
