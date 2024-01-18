class Tempo implements Runnable {

    private static int tempoLimite;
    public static int tempoAttuale=0;

    public Tempo(int tempoLimite) {
        this.tempoLimite = tempoLimite;
    }

    public static int getTempo() {
        return tempoAttuale;
    }

    @Override
    public void run() {

        while (tempoAttuale < tempoLimite) {
            // Implementazione per gestire il tempo della partita
            try {
                Thread.sleep(5000); // Attendi un secondo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tempoAttuale++;
        }
        // Logica per fine partita
    }
}