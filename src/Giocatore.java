    public class Giocatore extends Thread{
    private int numeroMaglia;
    private String nomeSquadra;
    private String[] nomeMaglia;

    public Giocatore(int numeroMaglia,String nomeSquadra,String[] nomeMaglia){
        this.numeroMaglia=numeroMaglia;
        this.nomeSquadra=nomeSquadra;
    }

    // metodo per caricare il nome del giocatore nell'array
    public void setNomeMaglia(String[] nomeMaglia){
        this.nomeMaglia=nomeMaglia;
    }

    public void run(){
        System.out.println("Il giocatore "+nomeMaglia+" della squadra "+nomeSquadra+" con numero di maglia "+numeroMaglia+" è pronto per la partita");
    }


}
