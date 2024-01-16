import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Arbitro arbitro = new Arbitro("Nome Arbitro");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci il nome della squadra 1: ");
        String nomeSquadra1 = scanner.nextLine();
        Squadra squadra1 = new Squadra(nomeSquadra1, 11);

        // Aggiungi giocatori alla squadra 1
        for (int i = 0; i < 11; i++) {
            System.out.print("Inserisci il nome del giocatore " + (i + 1) + " della squadra " + nomeSquadra1 + ": ");
            String nomeGiocatore = scanner.nextLine();
            Giocatore giocatore = new Giocatore(i + 1, nomeGiocatore, nomeSquadra1, squadra1);
            squadra1.aggiungiGiocatore(giocatore);
        }

        System.out.print("Inserisci il nome della squadra 2: ");
        String nomeSquadra2 = scanner.nextLine();
        Squadra squadra2 = new Squadra(nomeSquadra2, 11);

        // Aggiungi giocatori alla squadra 2
        for (int i = 0; i < 11; i++) {
            System.out.print("Inserisci il nome del giocatore " + (i + 1) + " della squadra " + nomeSquadra2 + ": ");
            String nomeGiocatore = scanner.nextLine();
            Giocatore giocatore = new Giocatore(i + 1, nomeGiocatore, nomeSquadra2, squadra2);
            squadra2.aggiungiGiocatore(giocatore);
        }

        Tempo tempoPartita = new Tempo(1); // 90 minuti di gioco
        Thread threadTempo = new Thread(tempoPartita);
        threadTempo.start();

        // Creazione e avvio dei thread per i giocatori delle squadre
        for(Giocatore giocatore : squadra1.getArrayGiocatori()) {
            Thread threadGiocatore = new Thread(new AzioneGiocatore(giocatore, true, arbitro));
            threadGiocatore.start();
        }

        for (Giocatore giocatore : squadra2.getArrayGiocatori()) {
            Thread threadGiocatore = new Thread(new AzioneGiocatore(giocatore, true, arbitro));
            threadGiocatore.start();
        }

        // Interazione con l'arbitro e gestione delle azioni durante la partita

        boolean partitaInCorso = true;
        if (partitaInCorso) {
            partitaInCorso = false;
            System.out.println("Seleziona un'azione per l'arbitro:");
            System.out.println("1. Assegna un cartellino");
            System.out.println("2. Effettua una sostituzione");
            System.out.println("3. Esci");

            int scelta = scanner.nextInt();
            scanner.nextLine(); // Consuma il newline

            switch (scelta) {
                case 1:
                    System.out.println("Seleziona la squadra a cui appartiene il giocatore:");
                    System.out.println("1. " + squadra1.getNome());
                    System.out.println("2. " + squadra2.getNome());
                    int sceltaSquadra = scanner.nextInt();
                    scanner.nextLine(); // Consuma il newline

                    Squadra squadraSelezionata = (sceltaSquadra == 1) ? squadra1 : squadra2;

                    System.out.println("Scegli il giocatore a cui assegnare il cartellino:");
                    for (int i = 0; i < squadraSelezionata.getNumeroGiocatori(); i++) {
                        Giocatore giocatore = squadraSelezionata.getGiocatore(i);
                        System.out.println((i + 1) + ". " + giocatore.getNome());
                    }

                    int sceltaGiocatore = scanner.nextInt();
                    scanner.nextLine(); // Consuma il newline
                    Giocatore giocatoreSelezionato = squadraSelezionata.getGiocatore(sceltaGiocatore - 1);
                    arbitro.assegnaCartellino(giocatoreSelezionato);
                    partitaInCorso = true;

                    break;
                case 2:
                    System.out.println("Seleziona la squadra da cui vuoi fare la sostituzione:");
                    System.out.println("1. " + squadra1.getNome());
                    System.out.println("2. " + squadra2.getNome());
                    int squadraSostituzione = scanner.nextInt();
                    scanner.nextLine(); // Consuma il newline
                    Squadra squadraDaCuiSostituire = (squadraSostituzione == 1) ? squadra1 : squadra2;

                    System.out.println("Scegli il giocatore da far uscire:");
                    for (int i = 0; i < squadraDaCuiSostituire.getNumeroGiocatori(); i++) {
                        Giocatore giocatore = squadraDaCuiSostituire.getGiocatore(i);
                        System.out.println((i + 1) + ". " + giocatore.getNome());
                    }

                    int giocatoreDaUscire = scanner.nextInt();
                    scanner.nextLine(); // Consuma il newline

                    System.out.println("Seleziona la squadra in cui vuoi far entrare il nuovo giocatore:");
                    System.out.println("1. " + squadra1.getNome());
                    System.out.println("2. " + squadra2.getNome());
                    int squadraEntrata = scanner.nextInt();
                    scanner.nextLine(); // Consuma il newline

                    Squadra squadraInCuiSostituire = (squadraEntrata == 1) ? squadra1 : squadra2;

                    System.out.println("Scegli il nuovo giocatore:");
                    for (int i = 0; i < squadraInCuiSostituire.getNumeroGiocatori(); i++) {
                        Giocatore giocatore = squadraInCuiSostituire.getGiocatore(i);
                        System.out.println((i + 1) + ". " + giocatore.getNome());
                    }

                    int nuovoGiocatore = scanner.nextInt();
                    scanner.nextLine(); // Consuma il newline

                    Giocatore giocatoreUscita = squadraDaCuiSostituire.getGiocatore(giocatoreDaUscire - 1);
                    Giocatore giocatoreEntrata = squadraInCuiSostituire.getGiocatore(nuovoGiocatore - 1);

                    arbitro.effettuaSostituzione(giocatoreUscita, giocatoreEntrata);
                    partitaInCorso = true;
                    break;
                case 3:
                    partitaInCorso = false;
                    break;
                default:
                    System.out.println("Scelta non valida, riprova.");
                    break;

            }
            // Altre operazioni e logiche di fine partita

            scanner.close(); // Chiudi lo scanner alla fine dell'utilizzo
        }
    }
}
