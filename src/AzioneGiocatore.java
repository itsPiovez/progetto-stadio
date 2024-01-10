class AzioneGiocatore implements Runnable {
    private Giocatore giocatore;
    private boolean partitaInCorso;

    public AzioneGiocatore(Giocatore giocatore, boolean partitaInCorso) {
        this.giocatore = giocatore;
        this.partitaInCorso = partitaInCorso;
    }

    @Override
    public void run() {
        while (partitaInCorso) {
            eseguiAzioneCasuale();
            try {
                Thread.sleep(1000); // Attendi un secondo tra un'azione e l'altra
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void eseguiAzioneCasuale() {
        // Implementazione di un'azione casuale del giocatore
        // Puoi inserire qui la logica per l'azione casuale del giocatore durante la partita
        System.out.println(giocatore.getNome() + " sta eseguendo un'azione casuale.");
    }
}