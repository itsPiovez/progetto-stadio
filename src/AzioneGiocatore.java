import java.util.Random;

class AzioneGiocatore implements Runnable {
    private Giocatore giocatore;
    private boolean partitaInCorso;
    private Arbitro arbitro;  // Aggiungi un attributo Arbitro
    private Tempo tempoPartita;  // Aggiungi un attributo Tempo

    public AzioneGiocatore(Giocatore giocatore, boolean partitaInCorso, Arbitro arbitro,Tempo tempoPartita) {
        this.giocatore = giocatore;
        this.partitaInCorso = partitaInCorso;
        this.arbitro= arbitro;
        this.tempoPartita=tempoPartita;

    }

    @Override
    public void run() {
        while (partitaInCorso) {
            eseguiAzioneCasuale();
            try {
                Thread.sleep(5000); // Attendi un secondo tra un'azione e l'altra

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    synchronized private void eseguiAzioneCasuale() {
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
            System.out.println(giocatore.getNome() + " ha fatto fallo.");

            // Riduci la probabilità di fare fallo, ad esempio, a 10%
            int probabilitaFallo = 10;
            if (random.nextInt(100) + 1 <= probabilitaFallo) {
                // Chiamata al metodo dell'arbitro per gestire il fallo
                arbitro.assegnaCartellino(giocatore);
            } else {
                // Azione alternativa o lascia vuoto se non vuoi fare nulla in caso di mancato fallo
            }
        }
    }



    private void eseguiPassaggio() {
        System.out.println(giocatore.getNome() + " sta eseguendo un passaggio.");

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
    }


    private void eseguiDifesa() {
        System.out.println(giocatore.getNome() + " sta eseguendo un tackle.");
        // Aggiungi la logica specifica per una difesa
    }

    private void eseguiTiro() {
        System.out.println(giocatore.getNome() + " sta eseguendo un tiro.");

        // Genera un numero casuale tra 1 e 100
        int probabilitaSuccesso = 30; // Modifica questo valore in base alla probabilità desiderata
        Random random = new Random();
        int valoreCasuale = random.nextInt(100) + 1;

        if (valoreCasuale <= probabilitaSuccesso) {
            // Il tiro è un successo (produzione gol)
            System.out.println("GOOOOOOOL!");
            // Aggiungi qui la logica specifica per un gol
        } else {
            // Il tiro non va a buon fine (perdita palla)
            System.out.println("Il tiro non va a buon fine. Il pallone è stato intercettato.");
            // Aggiungi qui la logica specifica per la perdita della palla
        }
    }


}