package GestioneMatch;
import MainGenarale.*;
public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Creazione della squadra

        Squadra squadra1 = new Squadra("Milan", 2);
        Squadra squadra2 = new Squadra("Juventus", 2);
        StatsPartita partita = new StatsPartita(squadra1,squadra2);
        Arbitro arbitro =new Arbitro("Gino",squadra1,squadra2,partita);

        // Creazione della palla
        Palla palla = new Palla();
        System.out.println("Ecco il calcio d'inizio, presentato da Perluigi Pardo e Lele Adani");
        System.out.println("Adani: Lo si vede, Pierluigi. La tua passione per il calcio è contagiosa.");
        System.out.println("Adani: Pierluigi, che partita ci aspetta?");
        System.out.println("Pardo: Una partita molto bella, Lele. " + squadra1.getNome() + " e " + squadra2.getNome() + " sono due squadre forti ed equilibrate. Sarà una partita molto aperta.");
        System.out.println("Calcio d'inizio. Inizia la partita tra " + squadra1.getNome() + " e " + squadra2.getNome());
        System.out.println(" ");

        Tempo tempo = new Tempo(90,squadra1,squadra2,partita);
        tempo.start();

        // Creazione dei giocatori
        Giocatore giocatore1 = new Giocatore(1, "Rafael Leao", squadra1.getNome(), squadra1, palla,arbitro,tempo);
        Giocatore giocatore2 = new Giocatore(2, "Zlatan Ibrahimovic", squadra1.getNome(), squadra1, palla,arbitro,tempo);
        squadra1.aggiungiGiocatore(giocatore1);
        squadra1.aggiungiGiocatore(giocatore2);

        Giocatore giocatore3 = new Giocatore(3, "Dusan Vlahovic", squadra2.getNome(), squadra2, palla,arbitro,tempo);
        Giocatore giocatore4 = new Giocatore(4, "Andrea Pirlo", squadra2.getNome(), squadra2, palla,arbitro,tempo);
        squadra2.aggiungiGiocatore(giocatore3);
        squadra2.aggiungiGiocatore(giocatore4);

        // Avvio dei giocatori
        giocatore1.start();
        giocatore2.start();
        giocatore3.start();
        giocatore4.start();

        /*while(tempo.tempoAttuale<tempo.tempoLimite){
            if(tempo.tempoAttuale==tempo.finePrimoTempo){
                System.out.println("Fine primo tempo");
                squadra1.addormentaSquadra();
                squadra2.addormentaSquadra();
                giocatore1.join();
                giocatore2.join();
                giocatore3.join();
                giocatore4.join();
                Thread.sleep(10000);
                squadra1.svegliaSquadra();
                squadra2.svegliaSquadra();
                System.out.println("Inizio secondo tempo");
            }
        }
            Thread.sleep(1000);
        }
        System.out.println("Fine partita");
         */

    }
}

