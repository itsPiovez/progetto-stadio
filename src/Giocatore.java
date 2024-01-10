import java.util.ArrayList;
import java.util.List;

class Giocatore {
    private int id;
    private String nomeMaglia;
    private String nomeSquadra;
    private int cartellini;

    public Giocatore(int id, String nomeMaglia, String nomeSquadra) {
        this.id = id;
        this.nomeMaglia = nomeMaglia;
        this.nomeSquadra = nomeSquadra;
        this.cartellini = 0;
    }

    public int getId() {
        return id;
    }

    public String getNomeMaglia() {
        return nomeMaglia;
    }

    public String getNome() {
        return nomeMaglia;
    }

    public int incrementaCartellini() {
        return ++cartellini;
    }

    public int getCartellini() {
        return cartellini;
    }
    public String getNomeSquadra() {
        return nomeSquadra;
    }
}