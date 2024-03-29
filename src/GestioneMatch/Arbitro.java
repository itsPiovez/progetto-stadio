package GestioneMatch;

class Arbitro {
    private String nome;
    private Squadra sq1;
    private Squadra sq2;
    public Arbitro(String nome, Squadra sq1, Squadra sq2, StatsPartita stats) {
        this.nome = nome;
        this.sq1=sq1;
        this.sq2=sq2;

    }

    public synchronized void assegnaCartellinoGiallo(Giocatore giocatore) {
        giocatore.incrementaCartellini(); // Incrementa il numero di cartellini del giocatore
        Squadra sq = giocatore.getSquadra();
        sq.incrementaGialli();
        System.out.println("Cartellino assegnato a " + giocatore.getNome() + " della squadra " + giocatore.getNomeSquadra());
        // Altre operazioni relative all'assegnazione del cartellino
    }
    public synchronized void assegnaCartellinoRosso(Giocatore giocatore) {
        giocatore.incrementaCartellini();
        giocatore.incrementaCartellini();
        Squadra sq = giocatore.getSquadra();
        sq.incrementaRossi();
        System.out.println("Cartellino assegnato a " + giocatore.getNome() + " della squadra " + giocatore.getNomeSquadra());
        // Altre operazioni relative all'assegnazione del cartellino
    }

    public synchronized void effettuaSostituzione(Giocatore uscente, Giocatore entrante) {
        Squadra squadra = trovareSquadra(uscente);
        if (squadra != null) {
            squadra.sostituisciGiocatore(uscente, entrante); // Effettua la sostituzione nella squadra
            System.out.println("Sostituzione effettuata: " + uscente.getNome() + " esce, " + entrante.getNome() + " entra");
            // Altre operazioni relative alla sostituzione
        }
    }

    private Squadra trovareSquadra(Giocatore giocatore) {
        // Implementazione per trovare la squadra a cui appartiene il giocatore
        // Può essere fatta ricerca nell'array delle squadre per trovare quella corrispondente
        // Restituisce la squadra del giocatore, null se non trova la squadra
        return null;
    }

    public void assegnaGoal(Giocatore giocatore, Squadra squadra) {
        squadra.incrementaPunteggio();
        System.out.println("Goal! " + giocatore.getNome() + " ha segnato per la squadra " + squadra.getNome());
    }
}