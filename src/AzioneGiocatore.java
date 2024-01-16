import java.util.Random;
import java.util.Scanner;

class AzioneGiocatore implements Runnable {
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
            eseguiAzioneCasuale();
            try {
                Thread.sleep(10000); // Attendi un secondo tra un'azione e l'altra
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    synchronized private void eseguiAzioneCasuale() {
        partitaInCorso = false;
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
                arbitro.assegnaCartellino(giocatore);

                // Chiedi all'utente se ammonire o meno il giocatore
                System.out.println("Cosa vuoi fare?");
                System.out.println("1. Ammonisci il giocatore");
                System.out.println("2. Lascia perdere");

                int sceltaAmmonizione = scanner.nextInt();
                scanner.nextLine();  // Consuma il newline

                if (sceltaAmmonizione == 1) {
                    arbitro.assegnaCartellino(giocatore);
                } else {
                   partitaInCorso= true;
                }
            } else {
                // Azione alternativa o lascia vuoto se non vuoi fare nulla in caso di mancato fallo
            }
        }
    }

    private void eseguiPassaggio() {
        partitaInCorso = false;
        System.out.println(giocatore.getNome() + " della squadra " + giocatore.getNomeSquadra() + " sta eseguendo un passaggio.");

        // Genera un numero casuale tra 1 e 100
        int probabilitaSuccesso = 80; // Modifica questo valore in base alla probabilità desiderata
        Random random = new Random();
        int valoreCasuale = random.nextInt(100) + 1;

        if (valoreCasuale <= probabilitaSuccesso) {
            // Il passaggio è un successo
            System.out.println("Passaggio riuscito!");
            // Aggiungi qui la logica specifica per un passaggio riuscito
        } else {
            // Il passaggio non va a buon fine (perdita palla)
            System.out.println("Il passaggio non va a buon fine. La palla è stata intercettata.");
            // Aggiungi qui la logica specifica per la perdita della palla
        }
        partitaInCorso = true;
    }


    private void eseguiDifesa() {
        partitaInCorso = false;
        System.out.println(giocatore.getNome() + " sta eseguendo un tackle.");
        // Aggiungi la logica specifica per una difesa
        partitaInCorso = true;
    }

    private void eseguiTiro() {
        partitaInCorso = false;
        System.out.println(giocatore.getNome() + " della squadra " + giocatore.getNomeSquadra() + " sta eseguendo un tiro.");

        // Genera un numero casuale tra 1 e 100
        int probabilitaSuccesso = 30; // Modifica questo valore in base alla probabilità desiderata
        Random random = new Random();
        int valoreCasuale = random.nextInt(100) + 1;

        if (valoreCasuale <= probabilitaSuccesso) {
            // Il tiro è un successo (produzione gol)
            System.out.println("GOOOOOOOL!");
            arbitro.assegnaGoal(giocatore);
            // Fai una pausa e chiedi se continuare
            pausaEChiediContinuare();
        } else {
            // Il tiro non va a buon fine (perdita palla)
            System.out.println("Il tiro non va a buon fine. Il pallone è stato intercettato.");
        }
        partitaInCorso = true;
    }
    private void pausaEChiediContinuare() {
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
        }
    }
}