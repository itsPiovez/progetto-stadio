public class Aribitro extends Thread{
    private String nomeArbitro;
    private String[] nomeMaglia;
    private int[]numeroMaglia;
    private int numeroGiocatori;
    private Giocatore[] giocatori;

    public Aribitro(String nomeArbitro){
        this.nomeArbitro=nomeArbitro;
    }

    public void run(){
        System.out.println("L'arbitro "+nomeArbitro+" è pronto per la partita");
    }
}
