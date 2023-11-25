public class Tifoso extends Thread{
    private String nome;
    public Tifoso(String nome){
        this.nome=nome;
    }
    public String getNome(){
        return nome;
    }
}
