package Giocatori;
import Spogliatoi.SpogliatoioGiocatori;
public class Giocatore {
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
    public int getNumeroMaglia(){
        return numeroMaglia;
    }
    public char getSquadra(){
        return squadra;
    }
    public void setNome(String nome){
        this.nome=nome;
    }
    public void setNumeroMaglia(int numeroMaglia){
        this.numeroMaglia=numeroMaglia;
    }
    public void setSquadra(char squadra){
        this.squadra=squadra;
    }
}

