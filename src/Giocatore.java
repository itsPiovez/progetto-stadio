public class Giocatore extends Thread{
    private String nome;
    private int numeroMaglia; //numero di maglia dei giocatori
    private char squadra;  //C se sono in casa F se sono fuori casa
    public Giocatore(String nome, int numeroMaglia,char squadra){
        this.nome=nome;
        this.numeroMaglia=numeroMaglia;
        this.squadra=squadra;
    }
    public String getNome(){
        return nome;
    }

    @Override
    public void run() {

    }
}
