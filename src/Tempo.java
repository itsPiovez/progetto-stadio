class Tempo implements Runnable {
    private int tempo;

    public Tempo(int tempo) {
        this.tempo = tempo;
    }

    @Override
    public void run() {
        while (tempo > 0) {
            // Implementazione per gestire il tempo della partita
            try {
                Thread.sleep(1000); // Attendi un secondo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tempo--;
        }
        // Logica per fine partita
    }
}