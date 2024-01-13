package GestioneBiglietteria;

public class Abbonamento extends Tessera{
    private String CF;

    public Abbonamento(String nominativo,String codice,Settore settore,String CF) {
        super(codice,nominativo,settore,null);
        this.CF=CF;
    }

    public String getCF() {
        return CF;
    }

    public void setCF(String CF) {
        this.CF = CF;
    }

    @Override
    public String toString() {
        if(super.getPosto() == null)
            return "Stampa abbonamento in corso...\n" + "Codice Abbonamento: " + super.getCodice() + "\nNominativo: " + super.getNominativo() + ", età: " + super.getEta() +
                    "\nPrezzo: € " + super.getPrezzo() +", Settore: " + super.getSettore().getNome() +
                    "\nValido per: tutte le partite di Campionato";
        else
            return "Stampa abbonamento in corso...\n" + "Codice Abbonamento: " + super.getCodice() + "\nNominativo: " + super.getNominativo() + ", età: " + super.getEta() +
                    "\nPrezzo: € " + super.getPrezzo() +", Settore: " + super.getSettore().getNome()+" ,Posto:"+ super.getPosto() +
                    "\nValido per: tutte le partite di Campionato";
    }
}
