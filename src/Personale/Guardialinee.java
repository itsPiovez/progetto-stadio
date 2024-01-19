package Personale;
import Spogliatoi.SpogliatoioArbitro;
public class Guardialinee extends Thread{
    private String nome;
    public Guardialinee(String nome){
        this.nome=nome;
    }
    public String getNome(){
        return nome;
    }
}
