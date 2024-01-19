package Ristorante;

import java.util.List;

public class Cameriere implements Runnable {
    private String nome;
    private Ristorante.Coda<Ristorante.Cliente> coda;
    private List<Tavolo> tavoli;
    private Ristorante.UtenteUscitoCallback utenteUscitoCallback;
    private final int totalClients;

    public Cameriere(String nome, Ristorante.Coda<Ristorante.Cliente> coda, List<Ristorante.Tavolo> tavoli, Ristorante.UtenteUscitoCallback utenteUscitoCallback, int totalClients) {
        this.nome = nome;
        this.coda = coda;
        this.tavoli = tavoli;
        this.utenteUscitoCallback = utenteUscitoCallback;
        this.totalClients = totalClients;

    }

    @Override
    public void run() {
        while (utenteUscitoCallback.getClientsExited() < totalClients) {
            Ristorante.Cliente cliente = coda.pop();
            if (cliente == null) {
                break;  // Termina il thread se non ci sono più clienti
            }
            Ristorante.Tavolo tavolo = trovaTavolo(cliente);
            if (tavolo != null) {
                System.out.println("Cameriere " + nome + " accomoda il " + cliente.getNome() + " al tavolo " + tavolo.getNumero());
                tavolo.getCoda().push(cliente);
                raccogliPosata(cliente); // FEATURE AGGIUNTA per raccogliere la posata dopo aver accomodato il cliente
            } else {
                // Se non trova un tavolo, il cliente deve uscire dal ristorante
                System.out.println("Cameriere " + nome + " non trova un tavolo per il " + cliente.getNome() + ". Il cliente lascia il ristorante.");
                utenteUscitoCallback.utenteUscito();
            }
        }
    }

    private Ristorante.Tavolo trovaTavolo(Ristorante.Cliente cliente) {
        for (Ristorante.Tavolo tavolo : tavoli) {
            if (tavolo.getCoda().size() < tavolo.getPosti()) {
                return tavolo;
            }
        }
        return null; // Dovrebbe essere gestito meglio, ad esempio ritentando dopo un certo intervallo
    }

    //FEATURE AGGIUNTA
    private void raccogliPosata(Ristorante.Cliente cliente) {
        System.out.println("Cameriere " + nome + " raccoglie la posata per il " + cliente.getNome());
    }

}
