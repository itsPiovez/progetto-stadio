package Ristorante;

public class Tavolo extends Thread {
    private int numero;
    private int posti;
    private Ristorante.Coda<Ristorante.Cliente> coda;

    public Tavolo(int numero, int posti) {
        this.numero = numero;
        this.posti = posti;
        this.coda = new Ristorante.Coda<>();
    }

    public int getNumero() {
        return numero;
    }

    public int getPosti() {
        return posti;
    }

    public Ristorante.Coda<Ristorante.Cliente> getCoda() {
        return coda;
    }

    @Override
    public void run() {
        // Logica del tavolo
        while (true) {
            Ristorante.Cliente cliente = coda.pop();
            if (cliente == null) {
                break;  // Termina il thread se non ci sono più clienti
            }
        }
    }

    public void ordinaCibo(Ristorante.Cliente cliente, int preparazione) {
        try{Thread.sleep(preparazione);}catch(InterruptedException e){}  // Simula il tempo di ordinazione
        coda.push(cliente);  // Aggiungi cliente alla coda del tavolo
    }

    public void liberaTavolo(Ristorante.Cliente cliente) {
        System.out.println("Il " + cliente.getNome() + " ha finito di mangiare al tavolo " + numero);
        tavolaOccupata();
        cliente.pagaConto();
        tavolaLibera();
    }

    private void tavolaOccupata() {
        try {
            Thread.sleep((int) (Math.random() * 2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void tavolaLibera() {
        try {
            Thread.sleep((int) (Math.random() * 2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

