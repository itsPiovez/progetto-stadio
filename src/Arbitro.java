public class Arbitro {
    private String nomeArbitro;
    private String[] nomeMaglia;
    private int[]numeroMaglia;
    private int numeroGiocatori;
    private Giocatore[] giocatori;

    public Arbitro(String nomeArbitro){
        this.nomeArbitro=nomeArbitro;
    }

    public void run(){
        System.out.println("L'arbitro "+nomeArbitro+" è pronto per la partita");
    }
}
