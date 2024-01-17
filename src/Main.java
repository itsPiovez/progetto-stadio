import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Arbitro arbitro = new Arbitro("Nome Arbitro");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci il nome della squadra 1: ");
        String nomeSquadra1 = scanner.nextLine();
        Squadra squadra1 = new Squadra(nomeSquadra1, 11);

        // Aggiungi giocatori alla squadra 1
        for (int i = 0; i < 3; i++) {
            System.out.print("Inserisci il nome del giocatore " + (i + 1) + " della squadra " + nomeSquadra1 + ": ");
            String nomeGiocatore = scanner.nextLine();
            Giocatore giocatore = new Giocatore(i + 1, nomeGiocatore, nomeSquadra1, squadra1);
            squadra1.aggiungiGiocatore(giocatore);
        }

        System.out.print("Inserisci il nome della squadra 2: ");
        String nomeSquadra2 = scanner.nextLine();
        Squadra squadra2 = new Squadra(nomeSquadra2, 11);

        // Aggiungi giocatori alla squadra 2
        for (int i = 0; i < 3; i++) {
            System.out.print("Inserisci il nome del giocatore " + (i + 1) + " della squadra " + nomeSquadra2 + ": ");
            String nomeGiocatore = scanner.nextLine();
            Giocatore giocatore = new Giocatore(i + 1, nomeGiocatore, nomeSquadra2, squadra2);
            squadra2.aggiungiGiocatore(giocatore);
        }

        // Creazione e avvio dei thread per i giocatori delle squadre
        for(Giocatore giocatore : squadra1.getArrayGiocatori()) {
            Thread threadGiocatore = new Thread(new AzioneGiocatore(giocatore, true, arbitro));
            threadGiocatore.start();
        }

        for (Giocatore giocatore : squadra2.getArrayGiocatori()) {
            Thread threadGiocatore = new Thread(new AzioneGiocatore(giocatore, true, arbitro));
            threadGiocatore.start();
        }
        // Fai vedere il punteggio di tutte e due le squadre
        System.out.println("Punteggio " + squadra1.getNome() + ": " + squadra2.getNome());
        System.out.println("Punteggio " + squadra1.getPunteggio() + ": " + squadra2.getPunteggio());

    }
}
