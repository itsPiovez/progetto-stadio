package Ristorante;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;


// Interfaccia per gestire il callback di utente uscito
interface UtenteUscitoCallback {
    void utenteUscito();

    int getClientsExited();
}

// Implementazione di UtenteUscitoCallback
class UtenteUscitoHandler implements Ristorante.UtenteUscitoCallback {
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

class Coda<T> {
    private LinkedList<T> list;

    public Coda() {
        list = new LinkedList<T>();
    }

    public T pop() {
        if (list.isEmpty()) {
            return null;
        }
        return this.estrai();
    }

    private synchronized T estrai() {
        return list.removeFirst();
    }

    public synchronized void push(T element) {
        list.addLast(element);
    }

    public synchronized int size() {
        return list.size();
    }
}
class Menu {
    private Map<String, Double> primi;
    private Map<String, Double> secondi;
    private Map<String, Double> contorni;
    private Map<String, Double> bevande;
    private Map<String, Double> dolci;
    private Map<String, Integer> tempiDiPreparazione;
    private Map<String, Double> piatti;
    private Map<String, Double> menu;

    public Menu() {
        primi = new HashMap<>();
        secondi = new HashMap<>();
        contorni = new HashMap<>();
        bevande = new HashMap<>();
        dolci = new HashMap<>();
        piatti = new HashMap<>();
        tempiDiPreparazione = new HashMap<>();

        piatti.put("Pasta", 8.50);
        piatti.put("Pizza", 10.00);
        piatti.put("Insalata", 6.50);
        piatti.put("Bistecca", 15.00);
        piatti.put("Pesce", 12.00);
        piatti.put("Hamburger", 8.00);
        piatti.put("Patatine", 3.00);
        piatti.put("Bibita", 2.00);
        piatti.put("Vino", 10.00);
        piatti.put("Caffè", 1.50);
        piatti.put("Torta", 4.00);
        piatti.put("Gelato", 3.00);
        piatti.put("Frutta", 2.00);
        piatti.put("Acqua", 1.50);
        piatti.put("Tiramisù", 4.00);
        piatti.put("Crostata", 3.00);
        piatti.put("Crepes", 3.00);

        primi.put("Pasta", 8.50);
        primi.put("Pizza", 10.00);
        primi.put("Hamburger", 8.00);
        secondi.put("Bistecca", 15.00);
        secondi.put("Pesce", 12.00);
        contorni.put("Insalata", 6.50);
        contorni.put("Frutta", 2.00);
        contorni.put("Patatine", 3.00);
        bevande.put("Bibita", 2.00);
        bevande.put("Vino", 10.00);
        bevande.put("Acqua", 1.50);
        dolci.put("Caffè", 1.50);
        dolci.put("Torta", 4.00);
        dolci.put("Gelato", 3.00);
        dolci.put("Tiramisù", 4.00);
        dolci.put("Crostata", 3.00);
        dolci.put("Crepes", 3.00);

        tempiDiPreparazione.put("Pasta", 50000);  // Tempo di preparazione in millisecondi
        tempiDiPreparazione.put("Pizza", 40000);
        tempiDiPreparazione.put("Insalata", 10000);
        tempiDiPreparazione.put("Bistecca", 30000);
        tempiDiPreparazione.put("Pesce", 25000);
        tempiDiPreparazione.put("Hamburger", 10500);
        tempiDiPreparazione.put("Patatine", 10000);
        tempiDiPreparazione.put("Bibita", 500);
        tempiDiPreparazione.put("Vino", 500);
        tempiDiPreparazione.put("Caffè", 1500);
        tempiDiPreparazione.put("Torta", 1000);
        tempiDiPreparazione.put("Gelato", 1500);
        tempiDiPreparazione.put("Frutta", 1500);
        tempiDiPreparazione.put("Tiramisù", 1000);
        tempiDiPreparazione.put("Crostata", 1000);
        tempiDiPreparazione.put("Crepes", 10000);
        tempiDiPreparazione.put("Acqua", 500);
        // Aggiungi altri piatti e tempi di preparazione secondo necessità
    }

    public Double getPrezzo(String piatto) {
        return piatti.getOrDefault(piatto, 0.0);
    }

    public Integer getTempoDiPreparazione(String piatto) {
        return tempiDiPreparazione.getOrDefault(piatto, 0);
    }
}
class Tavolo extends Thread {
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

class Cliente implements Runnable {
    private String nome;
    private double totale=0;
    private List<Ristorante.Tavolo> tavoli;
    private Ristorante.Coda<Ristorante.Cliente> coda;
    private Menu menu;

    public Cliente(String nome, List<Ristorante.Tavolo> tavoli, Ristorante.Coda<Ristorante.Cliente> coda,Menu m) {
        this.nome = nome;
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

class Cameriere implements Runnable {
    private String nome;
    private Ristorante.Coda<Ristorante.Cliente> coda;
    private List<Ristorante.Tavolo> tavoli;
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
}

class Cuoco implements Runnable {
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
