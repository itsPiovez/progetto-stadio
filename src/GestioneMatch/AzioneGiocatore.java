package GestioneMatch;

import java.util.Random;
import java.util.Scanner;

class AzioneGiocatore{
    Tempo t;
    private boolean arrabbiato;
    private Giocatore giocatore;
    private boolean partitaInCorso;
    private Arbitro arbitro;
    private Scanner scanner;  // Aggiunto Scanner come attributo

    public AzioneGiocatore(Giocatore giocatore, Arbitro arbitro, Tempo t) {
        this.arbitro = arbitro;
        this.giocatore = giocatore;
        this.scanner = new Scanner(System.in);  // Creazione dello Scanner all'interno del costruttore
        this.t=t;
    }

    synchronized public void eseguiAzioneCasuale() throws InterruptedException {
        Thread.sleep(100);

        Random random = new Random();
        int valoreCasuale = random.nextInt(100) + 1; // Genera un numero tra 1 e 100--> genera un'azione casuale
        if (valoreCasuale <= 25) {
            eseguiPassaggio();
        } else if (valoreCasuale <= 50) {
            eseguiDifesa();
        } else if (valoreCasuale <= 75) {
            eseguiTiro();
        } else {
            System.out.println(giocatore.getNome() + " della squadra " + giocatore.getNomeSquadra() + " ha fatto fallo al minuto: " + Tempo.getTempo());// Il giocatore ha fatto fallo

            int probabilitaFallo = 30;// Riduci la probabilità di fare fallo, ad esempio, a 10%
            if (random.nextInt(100) + 1 <= probabilitaFallo) {
                // Chiamata al metodo dell'arbitro per gestire il fallo

                // Inizializza una variabile random, se il valore è 1 il fallo non è grave e il giocatore non viene sanzionato, se esce 2 il fallo è grave e il giocatore viene sanzionato col cartellino
                int valoreCasualeFallo = random.nextInt(2) + 1;
                if (valoreCasualeFallo == 1) {
                    System.out.println("Il fallo non è grave, il giocatore " + giocatore.getNome() + " non viene sanzionato.");
                } else {
                    System.out.println("Il fallo è grave, il giocatore " + giocatore.getNome() + " viene sanzionato." + "al minuto: " + Tempo.getTempo());
                    int valoreCartellino = random.nextInt(2) + 1;
                    if (valoreCartellino == 1) {
                        System.out.println("Il giocatore" + giocatore.getNome() + " riceve un cartellino giallo." + "al minuto: " + Tempo.getTempo());
                        arbitro.assegnaCartellinoGiallo(giocatore);
                        if (giocatore.getCartellini() == 2) {
                            System.out.println("Il giocatore" + giocatore.getNome() + " riceve un cartellino rosso." + "al minuto: " + Tempo.getTempo());
                            System.out.println("Il giocatore" + giocatore.getNome() + " se ne va dal campo.");
                            arbitro.assegnaCartellinoRosso(giocatore);
                            Thread.currentThread().interrupt(); // Interrompe il thread del giocatore
                        }
                    }
                    if (valoreCartellino == 2) {
                        System.out.println("Il giocatore" + giocatore.getNome() + " riceve un cartellino rosso." + "al minuto: " + Tempo.getTempo());
                        System.out.println("Il giocatore" + giocatore.getNome() + " se ne va dal campo.");
                        arbitro.assegnaCartellinoRosso(giocatore);
                        Thread.currentThread().interrupt(); // Interrompe il thread del giocatore
                    }
                }
            }
        }
    }

    private void eseguiPassaggio() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println(giocatore.getNome() + " della squadra " + giocatore.getNomeSquadra() + " sta eseguendo un passaggio." + "al minuto: " + Tempo.getTempo());
        int probabilitaSuccesso = 80; // Modifica questo valore in base alla probabilità desiderata
        Random random = new Random();
        int valoreCasuale = random.nextInt(100) + 1;//--> genera un numero casuale tra 1 e 100
        if (valoreCasuale <= probabilitaSuccesso) {
            System.out.println("Passaggio riuscito!");// Il passaggio è un successo
            Thread.sleep(3000);
        } else {
            System.out.println("Il passaggio non va a buon fine. La palla è stata intercettata.");// Il passaggio non va a buon fine (perdita palla)
            Thread.sleep(3000);
        }
        //Infortunio();
    }

    private void eseguiDifesa() throws InterruptedException {
        Thread.sleep(3000); // Attendi 10 secondi per simulare l'esecuzione dell'azione
        System.out.println(giocatore.getNome() + " sta eseguendo un tackle " + " al minuto: " + Tempo.getTempo());
        //Infortunio();
    }

    private void eseguiTiro() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println(giocatore.getNome() + " della squadra " + giocatore.getNomeSquadra() + " sta eseguendo un tiro " + "al minuto: " + Tempo.getTempo());
        int probabilitaSuccesso = 90; // Modifica questo valore in base alla probabilità desiderata
        Random random = new Random();
        int valoreCasuale = random.nextInt(100) + 1;
        if (valoreCasuale <= probabilitaSuccesso) {
            System.out.println("GOOOOOOOL!" + "al minuto: " + Tempo.getTempo());//successo del tiro, il giocatore segna un goal
            System.out.println("I tifosi sono in delirio!");
            arbitro.assegnaGoal(giocatore, giocatore.getSquadra());
            System.out.println(giocatore.getNome()+" sta esultando come un pazzo, è veramente impazzito!");
            System.out.println("la partita è in pausa e tra poco ci sarà il calcio d'inizio");
            t.impostaPausa();
            t.togliPausa();

        } else {
            System.out.println("Il tiro non va a buon fine. Il pallone è stato intercettato.");// Il tiro non va a buon fine (perdita palla)
        }
        //Infortunio();
    }

    private void Infortunio() {
        // se l'infortunioè lieve il giocatore può continuare a giocare
        Random random = new Random();
        //valore casuale da 1 a 10
        int valoreCasuale = random.nextInt(5) + 1;
        if (valoreCasuale == 1) {
            System.out.println("Il giocatore " + giocatore.getNome() +" ha subito un infortunio, i medici effettuano un controllo");
            System.out.println("L'infortunio è lieve, il giocatore " + giocatore.getNome() + " può continuare a giocare");
        }if (valoreCasuale==5) {
            // se l'infortunio è grave il giocatore non può continuare a giocare
            System.out.println("L'infortunio è grave, il giocatore " + giocatore.getNome() + " non può continuare a giocare");
            Thread.currentThread().interrupt(); // Interrompe il thread del giocatore
        }
    }
}
    /*private void pausaEChiediContinuare() {
        partitaInCorso = false; // Ferma la partita
        // Fai una pausa e chiedi all'utente se vuole continuare la partita
        System.out.println("La partita si ferma. Vuoi continuare?");
        System.out.println("1. Sì");
        System.out.println("2. No");

        int sceltaContinua = scanner.nextInt();
        scanner.nextLine();  // Consuma il newline
        if (sceltaContinua == 1) {
            // L'utente ha scelto di continuare, puoi riprendere la partita
            System.out.println("La partita riprende.");
            partitaInCorso = true;
        }
        if (sceltaContinua == 2) {
            // L'utente ha scelto di non continuare, puoi terminare il gioco o fare altre operazioni
            System.out.println("La partita è terminata.");
            partitaInCorso = false
            */