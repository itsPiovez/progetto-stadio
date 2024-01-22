package GestioneMatch;


class Tempo extends Thread {
    private volatile Boolean pausaGiocatori;
    private StatsPartita statsPartita;
    private Squadra sq1;
    private Squadra sq2;
    public static int tempoLimite;
    public static int finePrimoTempo = 45;

    public static volatile int tempoAttuale = 0;

    public Tempo(int tempoLimite, Squadra sq1, Squadra sq2, StatsPartita statsPartita) {
        this.sq1=sq1;
        this.sq2=sq2;
        this.tempoLimite = tempoLimite;
        this.statsPartita=statsPartita;
        this.pausaGiocatori=false;
    }

    public static synchronized int getTempo() {
        return tempoAttuale;
    }


    public void run() {
        try {
            while (tempoAttuale < tempoLimite) {
                Thread.sleep(1000);
                tempoAttuale++;
                if (tempoAttuale == finePrimoTempo) {
                    System.out.println("Fine primo tempo");
                    pausaGiocatori=true;
                    sq1.addormentaSquadra();
                    sq2.addormentaSquadra();
                    Thread.sleep(10000);
                    pausaGiocatori=false;
                    sq1.svegliaSquadra();
                    sq2.svegliaSquadra();
                    System.out.println("Inizio secondo tempo");
                }

            }
            System.out.println("Fine partita");
            statsPartita.stampaStats(sq1,sq2);
            System.exit(0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public Boolean getPausaGiocatori(){
        return pausaGiocatori;
    }
    public void impostaPausa(){
        pausaGiocatori=true;
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void togliPausa(){
        pausaGiocatori=false;
        System.out.println("calcio d'inizio!");
    }
    public void addormentaSquadra(Squadra sq){
        sq.addormentaSquadra();
    }
    public void svegliaSquadra(Squadra sq){
        sq.svegliaSquadra();
    }

}

