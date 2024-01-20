package TransferMarket;

public class Giocatore {
    private String nome;
    private int marketValue;

    public Giocatore(String nome, int marketValue) {
        this.nome = nome;
        this.marketValue = marketValue;
    }

    public String getNome() {
        return nome;
    }

    public int getMarketValue() {
        return marketValue;
    }
}