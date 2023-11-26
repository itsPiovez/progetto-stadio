public class Arbitro extends Thread {
    private String nome;
    public Arbitro(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
}
