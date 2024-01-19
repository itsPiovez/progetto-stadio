package GestioneBiglietteriaNuova;

public class Tessera {
    private String codice;
    private String nominativo;
    private int eta;
    private float prezzo;
    private Posto posto;
    private Settore settore;
    private Partita partita;

    public Tessera(String codice, String nominativo, Settore settore, Partita partita) {
        this.codice = codice;
        this.nominativo = nominativo;
        this.settore = settore;
        this.partita = partita;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getNominativo() {
        return nominativo;
    }

    public void setNominativo(String nominativo) {
        this.nominativo = nominativo;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public Posto getPosto() {
        return posto;
    }

    public void setPosto(Posto posto) {
        this.posto = posto;
    }

    public Settore getSettore() {
        return settore;
    }

    public void setSettore(Settore settore) {
        this.settore = settore;
    }

    public Partita getPartita() {
        return partita;
    }

    public void setPartita(Partita partita) {
        this.partita = partita;
    }

}