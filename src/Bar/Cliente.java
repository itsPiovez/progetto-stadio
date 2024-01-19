package Bar;

import Azioni.Tifoso;

public class Cliente extends Tifoso implements Runnable {
    private String nome;
    private Coda<Cliente> coda;
    private Menu menu;
    private double totale = 0;

    public Cliente(String nome, Coda<Cliente> coda, Menu menu) {
        super(nome);
        this.nome = nome;
        this.coda = coda;
        this.menu = menu;
    }

    public String getNome() {
        return nome;
    }

    public void pagaConto() {
        System.out.println("Il " + nome + " paga il conto di " + totale + "€ e lascia il bar.");
    }

    @Override
    public void run() {
        System.out.println("Il " + nome + " entra nel bar");
        coda.push(this);  // Entra nella coda per essere servito

        // Attendi finché non vieni servito
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(100);  // Attendi prima di riprovare a essere servito
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
