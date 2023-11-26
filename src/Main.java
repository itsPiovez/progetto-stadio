// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {
    public static void main(String[] args) {
    }
}

/*

matrice per i collegamenti e faccio in modo che ci sia un tempo per arrivare ad ogni luogo
devo modificare i nomi di ogni thread ( es Ristorante ) con setName("Ristorante1") e per riprendermelo basta che uso la funzione getName()

import java.util.Arrays;
public class MatriceRicerca {
    public static void main(String[] args) {
        int[][] matrice = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        int oggetto1 = 2;
        int oggetto2 = 8;

        int[] posizioneOggetto1 = cercaOggetto(matrice, oggetto1);
        int[] posizioneOggetto2 = cercaOggetto(matrice, oggetto2);

        System.out.println("Posizione dell'oggetto 1: " + Arrays.toString(posizioneOggetto1));
        System.out.println("Posizione dell'oggetto 2: " + Arrays.toString(posizioneOggetto2));
    }

    private static int[] cercaOggetto(int[][] matrice, int target) {
        for (int i = 0; i < matrice.length; i++) {
            int colonna = Arrays.binarySearch(matrice[i], target);
            if (colonna >= 0) {
                return new int[]{i, colonna};
            }
        }
        return new int[]{-1, -1};
    }
}


----------------------------------------------------------------------------------------------------
AZIONI TIFOSI DURANTE MATCH implementando la possibilità di andare al bagno o andare al bar

import java.util.Random;

class Stadio {
    private static final int NUMERO_TIFOSI = 100;
    private static final int NUMERO_AZIONI = 7; // Includiamo ora anche "Andare in bagno" e "Andare al bar"

    public static void main(String[] args) {
        AzioneTifoso[] tifosi = new AzioneTifoso[NUMERO_TIFOSI];

        for (int i = 0; i < NUMERO_TIFOSI; i++) {
            tifosi[i] = new AzioneTifoso("Tifoso" + i);
            tifosi[i].start();
        }
    }
}

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

    private void eseguiAzioneCasuale() {
        int azioneCasuale = random.nextInt(NUMERO_AZIONI);

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
            // Aggiungi altri casi a seconda delle azioni possibili
        }
    }

    private void andareInBagno() {
        System.out.println(nomeTifoso + " sta andando in bagno.");
        // Simula il tempo trascorso in bagno
        try {
            Thread.sleep(random.nextInt(3000) + 1000); // Attendi tra 1 e 4 secondi
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(nomeTifoso + " è tornato dal bar.");
    }

    private void attendiIntervalloCasuale() {
        try {
            Thread.sleep(random.nextInt(2000) + 5000); // Attendi tra 5 e 7 secondi
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



*/