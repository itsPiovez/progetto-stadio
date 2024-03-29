package Annunci;

import java.util.Random;

import static Annunci.CreaAnnunci.stampaArcobaleno;
import java.util.Scanner;
public class Annunci extends Thread {
    private String[] messaggi;
    private Random random = new Random();

    public Annunci(String[] messaggi) {
        this.messaggi =  CreaAnnunci. GetAnnuncio();
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 10) {
            scriviAnnuncioCasuale();
            attesaCasuale();
            i++;
        }
    }

    public void scriviAnnuncioCasuale() {
        System.out.println("");
        String messaggio = messaggi[random.nextInt(messaggi.length)];
        System.out.println("\u001B[30m"+"-----------------------------------"+"\u001B[0m");
        System.out.println("Annuncio: " + messaggio);
        System.out.println("\u001B[30m"+"-----------------------------------"+"\u001B[0m");
        System.out.println("");
    }

    private void attesaCasuale() {
        try {
            int tempoAttesa = random.nextInt(5000) + 1000; // Attesa tra 1 e 5 secondi
            Thread.sleep(tempoAttesa);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

