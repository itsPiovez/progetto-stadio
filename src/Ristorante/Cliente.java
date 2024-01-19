package Ristorante;

import Azioni.Tifoso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cliente extends Tifoso implements Runnable {
    private String nome;
    private double totale=0;
    private List<Tavolo> tavoli;
    private Ristorante.Coda<Ristorante.Cliente> coda;
    private Menu menu;

    public Cliente(String nome, List<Ristorante.Tavolo> tavoli, Ristorante.Coda<Ristorante.Cliente> coda,Menu m) {
        super(nome);
        this.tavoli = tavoli;
        this.coda = coda;
        this.menu=m;
    }

    public String getNome() {
        return nome;
    }

    public void pagaConto() {
        System.out.println("Il " + nome + " paga il conto di " + totale + "€ e lascia il ristorante");
    }

    @Override
    public void run() {
        System.out.println("Il " + nome + " entra nel ristorante");
        coda.push(this);  // Entra nella coda per essere accomodato
        Ristorante.Tavolo tavolo = null;

        // Attendi finché non vieni accomodato a un tavolo
        while (tavolo == null) {
            tavolo = cercaTavolo();
            if (tavolo == null) {
                try {
                    Thread.sleep(100);  // Attendi prima di riprovare a cercare un tavolo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Ora il cliente è stato accomodato e può ordinare
        System.out.println("        Cameriere accomoda il " + nome + " al tavolo " + tavolo.getNumero());
        int numeroPiatti = (int) (Math.random() * 4) + 1;  // Numero di piatti da ordinare
        String bevandaScelta = ScegliBevanda();
        double prezzoBevanda = menu.getPrezzo(bevandaScelta);
        totale=totale+prezzoBevanda;
        int tempoDiPreparazioneBevanda = menu.getTempoDiPreparazione(bevandaScelta);
        System.out.println("Il " + nome + " ordina " + bevandaScelta + " al tavolo " + tavolo.getNumero() + " (prezzo: " + prezzoBevanda + "€)");
        for (int i = 0; i < numeroPiatti; i++) {
            String piattoScelto = null;
            switch (i){
                case 0: piattoScelto =ScegliPrimo(); break;
                case 1:  piattoScelto =ScegliSecondo(); break;
                case 2: piattoScelto =ScegliContorno(); break;
                case 3: piattoScelto =ScegliDolce(); break;
            }
            double prezzo = menu.getPrezzo(piattoScelto);
            totale=totale+prezzo;
            int tempoDiPreparazione = menu.getTempoDiPreparazione(piattoScelto);
            System.out.println("Il " + nome + " ordina " + piattoScelto + " al tavolo " + tavolo.getNumero() + " (prezzo: " + prezzo + "€)");
            // Simulazione dell'ordinazione e preparazione del cibo
            tavolo.ordinaCibo(this, tempoDiPreparazione);
        }

        // Il cliente ha finito di mangiare, paga e lascia il ristorante
        tavolo.liberaTavolo(this);
    }

    private Ristorante.Tavolo cercaTavolo() {
        List<Ristorante.Tavolo> availableTables = new ArrayList<>();
        for (Ristorante.Tavolo tavolo : tavoli) {
            if (tavolo.getCoda().size() < tavolo.getPosti()) {
                availableTables.add(tavolo);
            }
        }
        if (availableTables.isEmpty()) {
            return null;
        } else {
            int randomIndex = new Random().nextInt(availableTables.size());
            return availableTables.get(randomIndex);
        }
    }
    private String ScegliPrimo() {
        // Simulazione della scelta di un primo a caso dal menu
        String[] piatti = {"Pasta", "Pizza","Hamburger"};
        return piatti[(int) (Math.random() * piatti.length)];
    }
    private String ScegliSecondo() {
        // Simulazione della scelta di un secondo a caso dal menu
        String[] piatti = {"Bistecca", "Pesce"};
        return piatti[(int) (Math.random() * piatti.length)];
    }
    private String ScegliContorno() {
        // Simulazione della scelta di un contorno a caso dal menu
        String[] piatti = {"Insalata", "Frutta","Patatine"};
        return piatti[(int) (Math.random() * piatti.length)];
    }
    private String ScegliBevanda() {
        // Simulazione della scelta di una bevanda a caso dal menu
        String[] piatti = {"Bibita", "Vino","Acqua"};
        return piatti[(int) (Math.random() * piatti.length)];
    }
    private String ScegliDolce() {
        // Simulazione della scelta di un dolce a caso dal menu
        String[] piatti = {"Torta", "Gelato","Tiramisù","Crostata","Crepes","Caffè"};
        return piatti[(int) (Math.random() * piatti.length)];
    }
}



