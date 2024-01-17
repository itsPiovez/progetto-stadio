package Personale;
import Spogliatoi.SpogliatoioArbitro;
public class Assistenti extends Thread{
    private String nome;
    public Assistenti(String nome){
        this.nome=nome;
    }
    public String getNome(){
        return nome;
    }
}
