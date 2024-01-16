import java.util.ArrayList;
import java.util.List;

class Giocatore {
    private int id;
    private String nomeMaglia;
    private String nomeSquadra;
    private int cartellini;
    private Squadra squadra;  // Aggiungi un campo Squadra

    public Giocatore(int id, String nomeMaglia, String nomeSquadra, Squadra squadra) {
        this.id = id;
        this.nomeMaglia = nomeMaglia;
        this.nomeSquadra = nomeSquadra;
        this.cartellini = 0;
        this.squadra = squadra;  // Inizializza il campo squadra

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

    public Squadra getSquadra() {
        return this.squadra; // Restituisce l'oggetto Squadra associato al giocatore
    }
}