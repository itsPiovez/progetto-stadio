class Arbitro {
    private String nome;

    public Arbitro(String nome) {
        this.nome = nome;
    }

    public synchronized void assegnaCartellino(Giocatore giocatore) {
        giocatore.incrementaCartellini(); // Incrementa il numero di cartellini del giocatore
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

    public void assegnaGoal(Giocatore giocatore) {
        Squadra squadraDelGiocatore = giocatore.getSquadra();  // Ottenere la squadra del giocatore
        squadraDelGiocatore.incrementaPunteggio();  // Aggiornare il punteggio della squadra
        System.out.println("Goal! " + giocatore.getNome() + " ha segnato per la squadra " + squadraDelGiocatore.getNome());
    }

}
