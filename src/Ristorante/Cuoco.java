package Ristorante;

public class Cuoco implements Runnable {
    private String nome;
    private Ristorante.Coda<Ristorante.Cliente> coda;
    private final int totalClients;
    private Ristorante.UtenteUscitoCallback utenteUscitoCallback;

    public Cuoco(String nome, Ristorante.Coda<Ristorante.Cliente> coda, int totalClients, Ristorante.UtenteUscitoCallback utenteUscitoCallback) {
        this.nome = nome;
        this.coda = coda;
        this.totalClients = totalClients;
        this.utenteUscitoCallback = utenteUscitoCallback;
    }

    @Override
    public void run() {
        while (utenteUscitoCallback.getClientsExited() < totalClients) {
            Ristorante.Cliente cliente = coda.pop();
            if (cliente == null) {
                break;  // Termina il thread se non ci sono più clienti
            }
            System.out.println("Cuoco " + nome + " inizia a preparare l'ordine per il " + cliente.getNome());
            // Simula il tempo di preparazione dell'ordine
            try {
                Thread.sleep((int) (500 + Math.random() * 1500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Cuoco " + nome + " ha preparato l'ordine per il " + cliente.getNome());
        }
    }
}
