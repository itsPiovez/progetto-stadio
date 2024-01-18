package Azioni;
import Bagni.*;
import Ristorante.*;
import Merch.*;
import Bar.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AzioneTifoso extends Thread {
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
    /*se voglio provare questa classe tolgo le virgolette*/

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
        //int azioneCasuale = generaNumeroConProbabilitaPersonalizzate(new double[]{0, 0, 0, 0, 0, 0, 0, 1, 0});  prova ristorante

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
    private void andareRistorante() {
        System.out.println(nomeTifoso + " sta andando al ristorante.");
        // Simula il tempo trascorso per andare al ristorante
        try {
            Thread.sleep(random.nextInt(4000) + 6000); // Attendi tra 1 e 4 secondi
            // collego la classe ristorante
            Ristorante.Cliente c = new Ristorante.Cliente("uomo",RistoranteCreazione.tavoli,RistoranteCreazione.coda,RistoranteCreazione.menu);
            Ristorante.RistoranteCreazione.coda.push(c);
            c.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(nomeTifoso + " è tornato dal ristorante.");
    }

    private void andareInBagno() {
        System.out.println(nomeTifoso + " sta andando in bagno.");
        Random random = new Random();
        // Simula il tempo trascorso in bagno
        try {
            Thread.sleep(random.nextInt(3000) + 5000); // Attendi tra 1 e 4 secondi
            // collego la classe bagno
            String sesso = random.nextBoolean() ? "uomo" : "donna";
            Persona p = new Persona(sesso, (int) this.getId());
            Bagni.Bagno.c.push(p);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(nomeTifoso + " è tornato dal bagno.");
    }

    private void andareAlBar() {
        System.out.println(nomeTifoso + " sta andando al bar.");

        try {
            Thread.sleep((new Random().nextInt(3000) + 1000));
            ClienteBar cliente = new ClienteBar(nomeTifoso,Bar.getInstance());
            new Thread(cliente).start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(nomeTifoso + " è tornato dal bar.");
    }

    private void andareAlMerch() {//manca da finire
        System.out.println(nomeTifoso + " sta andando al merch.");
        // Simula il tempo trascorso per andare al merch
        try {
            Thread.sleep(random.nextInt(3000) + 5000); // Attendi tra 1 e 4 secondi
            // collego la classe merch
            MerchShop merchShop = new MerchShop();
            merchShop.Apertura();
            //Clienti c = new Clienti(merchShop);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(nomeTifoso + " è tornato dal merch.");
    }

    private void attendiIntervalloCasuale() {
        try {
            Thread.sleep(random.nextInt(2000) + 10000); // Attendi tra 5 e 7 secondi
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
