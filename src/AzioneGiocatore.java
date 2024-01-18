import java.util.Random;
import java.util.Scanner;

class AzioneGiocatore extends Thread {
    private Giocatore giocatore;
    private boolean partitaInCorso;
    private Arbitro arbitro;
    private Scanner scanner;  // Aggiunto Scanner come attributo

    public AzioneGiocatore(Giocatore giocatore, boolean partitaInCorso, Arbitro arbitro) {
        this.giocatore = giocatore;
        this.partitaInCorso = partitaInCorso;
        this.arbitro = arbitro;
        this.scanner = new Scanner(System.in);  // Creazione dello Scanner all'interno del costruttore
    }

    @Override
    public void run() {
        while(partitaInCorso = true) {
            try {
                eseguiAzioneCasuale();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    synchronized private void eseguiAzioneCasuale() throws InterruptedException {
        partitaInCorso = false;
        Thread.sleep(10000);
        // Implementazione di un'azione casuale del giocatore
        Random random = new Random();
        int valoreCasuale = random.nextInt(100) + 1; // Genera un numero tra 1 e 100
        if (valoreCasuale <= 25) {
            eseguiPassaggio();
        } else if (valoreCasuale <= 50) {
            eseguiDifesa();
        } else if (valoreCasuale <= 75) {
            eseguiTiro();
        } else {
            // Il giocatore ha fatto fallo
            System.out.println(giocatore.getNome() + " della squadra " + giocatore.getNomeSquadra() + " ha fatto fallo.");

            // Riduci la probabilità di fare fallo, ad esempio, a 10%
            int probabilitaFallo = 10;
            if (random.nextInt(100) + 1 <= probabilitaFallo) {
                // Chiamata al metodo dell'arbitro per gestire il fallo

                // Inizializza una variabile random, se il valore è 1 il fallo non è grave e il giocatore non viene sanzionato, se esce 2 il fallo è grave e il giocatore viene sanzionato col cartellino
                int valoreCasualeFallo = random.nextInt(2) + 1;
                if (valoreCasualeFallo == 1) {
                    System.out.println("Il fallo non è grave, il giocatore " + giocatore.getNome() + " non viene sanzionato.");
                } else {
                    System.out.println("Il fallo è grave, il giocatore " + giocatore.getNome() + " viene sanzionato.");
                    int valoreCartellino = random.nextInt(2) + 1;
                    if(valoreCartellino == 1) {
                        System.out.println("Il giocatore" + giocatore.getNome() +" riceve un cartellino giallo.");
                        arbitro.assegnaCartellinoGiallo(giocatore);
                        if(giocatore.getCartellini() == 2){
                            System.out.println("Il giocatore" + giocatore.getNome() +" riceve un cartellino rosso.");
                            System.out.println("Il giocatore" + giocatore.getNome() +" se ne va dal campo.");
                            arbitro.assegnaCartellinoRosso(giocatore);
                            Thread.currentThread().interrupt(); // Interrompe il thread del giocatore
                        }
                    }if(valoreCartellino == 2) {
                        System.out.println("Il giocatore" + giocatore.getNome() +" riceve un cartellino rosso.");
                        System.out.println("Il giocatore" + giocatore.getNome() +" se ne va dal campo.");
                        arbitro.assegnaCartellinoRosso(giocatore);
                        Thread.currentThread().interrupt(); // Interrompe il thread del giocatore
                    }
                }
            }
        }
    }

    private void eseguiPassaggio() throws InterruptedException {
        partitaInCorso = false;
        Thread.sleep(10000);
        System.out.println(giocatore.getNome() + " della squadra " + giocatore.getNomeSquadra() + " sta eseguendo un passaggio.");

        // Genera un numero casuale tra 1 e 100
        int probabilitaSuccesso = 80; // Modifica questo valore in base alla probabilità desiderata
        Random random = new Random();
        int valoreCasuale = random.nextInt(100) + 1;

        if (valoreCasuale <= probabilitaSuccesso) {
            // Il passaggio è un successo
            System.out.println("Passaggio riuscito!");
            Thread.sleep(10000);
            // Aggiungi qui la logica specifica per un passaggio riuscito
        } else {
            // Il passaggio non va a buon fine (perdita palla)
            System.out.println("Il passaggio non va a buon fine. La palla è stata intercettata.");
            Thread.sleep(10000);
            // Aggiungi qui la logica specifica per la perdita della palla
        }
        partitaInCorso = true;
    }


    private void eseguiDifesa() throws InterruptedException {
        partitaInCorso = false;
        Thread.sleep(10000); // Attendi 10 secondi per simulare l'esecuzione dell'azione
        System.out.println(giocatore.getNome() + " sta eseguendo un tackle.");
        partitaInCorso = true;
    }

    private void eseguiTiro() throws InterruptedException {

        partitaInCorso = false;
        Thread.sleep(10000);
        System.out.println(giocatore.getNome() + " della squadra " + giocatore.getNomeSquadra() + " sta eseguendo un tiro.");
        // Genera un numero casuale tra 1 e 100
        int probabilitaSuccesso = 30; // Modifica questo valore in base alla probabilità desiderata
        Random random = new Random();
        int valoreCasuale = random.nextInt(100) + 1;
        if (valoreCasuale <= probabilitaSuccesso) {
            // Il tiro è un successo (produzione gol)

            System.out.println("GOOOOOOOL!");
            System.out.println("I tifosi sono in delirio!");

            arbitro.assegnaGoal(giocatore, giocatore.getSquadra());
            Thread.sleep(10000);
        } else {
            // Il tiro non va a buon fine (perdita palla)
            System.out.println("Il tiro non va a buon fine. Il pallone è stato intercettato.");
        }
        partitaInCorso = true;
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
            partitaInCorso = false;
        }*/