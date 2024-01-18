package Azioni;
import Bagni.*;
import Ristorante.*;
import Merch.*;
import Bar.*;
import Ristorante.Main;

import java.util.Random;

class AzioneTifoso extends Thread {
    private String nomeTifoso;
    private Random random = new Random();

    public AzioneTifoso(String nomeTifoso) {
        this.nomeTifoso = nomeTifoso;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            eseguiAzioneCasuale();
            attendiIntervalloCasuale();
        }
    }
    public static void main(String[] args) {
        new AzioneTifoso("Tifoso 1").start();
        new AzioneTifoso("Tifoso 2").start();
        new AzioneTifoso("Tifoso 3").start();
    }

    private static int generaNumeroConProbabilitaPersonalizzate(double[] probabilita) {
        Random random = new Random();
        double scelta = random.nextDouble();
        double accumulato = 0;

        // Itera attraverso le probabilità per decidere il numero da generare
        for (int i = 0; i < probabilita.length; i++) {
            accumulato += probabilita[i];
            if (scelta <= accumulato) {
                return i; // Restituisce il numero corrispondente alla probabilità raggiunta
            }
        }

        // In caso di problemi (es. somma delle probabilità non 1), restituisce -1 o gestisci l'errore in altro modo
        return -1;
    }

    private void eseguiAzioneCasuale() {
        int azioneCasuale = generaNumeroConProbabilitaPersonalizzate(new double[]{0.2, 0.15, 0.1, 0.1, 0.1, 0.1, 0.1, 0.05, 0.05});

        switch (azioneCasuale) {
            case 0:
                System.out.println(nomeTifoso + " sta cantando.");
                break;
            case 1:
                System.out.println(nomeTifoso + " sta sventolando la bandiera.");
                break;
            case 2:
                System.out.println(nomeTifoso + " sta applaudendo.");
                break;
            case 3:
                System.out.println(nomeTifoso + " sta lanciando coriandoli.");
                break;
            case 4:
                System.out.println(nomeTifoso + " sta fischiando.");
                break;
            case 5:
                andareInBagno();
                break;
            case 6:
                andareAlBar();
                break;
            case 7:
                andareRistorante();
                break;
            case 8:
                andareAlMerch();
                break;
            default:
                System.out.println(nomeTifoso + " sta guardando la partita.");
                break;
            // Aggiungi altri casi a seconda delle azioni possibili
        }
    }
    private void andareAlMerch() {
        System.out.println(nomeTifoso + " sta andando al merch.");
        // Simula il tempo trascorso per andare al merch
        try {
            Thread.sleep(random.nextInt(3000) + 5000); // Attendi tra 1 e 4 secondi
            // collego la classe merch
            MerchShop merchShop = MerchShop.getInstance();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(nomeTifoso + " è tornato dal merch.");
    }
    private void andareRistorante() {
        System.out.println(nomeTifoso + " sta andando al ristorante.");
        // Simula il tempo trascorso per andare al ristorante
        try {
            Thread.sleep(random.nextInt(3000) + 5000); // Attendi tra 1 e 4 secondi
            // collego la classe ristorante
            Ristorante.Cliente c = new Ristorante.Cliente("uomo",Main.tavoli,Main.coda,Main.menu);
            Ristorante.Main.coda.push(c);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(nomeTifoso + " è tornato dal ristorante.");
    }

    private void andareInBagno() {
        System.out.println(nomeTifoso + " sta andando in bagno.");
        // Simula il tempo trascorso in bagno
        try {
            Thread.sleep(random.nextInt(3000) + 5000); // Attendi tra 1 e 4 secondi
            // collego la classe bagno
            Persona p = new Persona("uomo",1);
            Bagni.MainBagni.c.push(p);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(nomeTifoso + " è tornato dal bagno.");
    }

    private void andareAlBar() {
        System.out.println(nomeTifoso + " sta andando al bar.");
        // Simula il tempo trascorso al bar
        try {
            Thread.sleep(random.nextInt(3000) + 1000); // Attendi tra 1 e 4 secondi
            Bar bar = Bar.getInstance();
            ClienteBar cliente = new ClienteBar(nomeTifoso, bar);
            new Thread(cliente).start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(nomeTifoso + " è tornato dal bar.");
    }

    private void attendiIntervalloCasuale() {
        try {
            Thread.sleep(random.nextInt(2000) + 10000); // Attendi tra 5 e 7 secondi
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
