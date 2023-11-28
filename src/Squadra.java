public class Squadra extends Thread{
    private String nomeSquadra;
    private String[] nomeMaglia;
    private int[]numeroMaglia;
    private int numeroGiocatori;
    private Giocatore[] giocatori;

    public Squadra(String nomeSquadra,int numeroGiocatori){
        this.nomeSquadra=nomeSquadra;
        this.numeroGiocatori=numeroGiocatori;
        giocatori=new Giocatore[numeroGiocatori];
    }

    // metodo per caricare il nome del giocatore nell'array
    public void setNomeMaglia(String[] nomeMaglia){
        this.nomeMaglia=nomeMaglia;
    }

    // metodo per caricare il numero di maglia del giocatore nell'array
    public void setNumeroMaglia(int[] numeroMaglia){
        this.numeroMaglia=numeroMaglia;
    }

    // metodo per caricare il nome del giocatore nell'array
    public void setGiocatori(Giocatore[] giocatori){
        this.giocatori=giocatori;
    }

    public void run(){
        System.out.println("La squadra "+nomeSquadra+" è pronta per la partita");
        for(int i=0;i<numeroGiocatori;i++){
            giocatori[i].start();
        }
    }
}
