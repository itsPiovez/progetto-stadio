package GestioneBiglietteriaNuova;

public class Abbonamento extends Tessera {
    private String CF;

    public Abbonamento(String nominativo, String codice, Settore settore, String CF) {
        super(codice, nominativo, settore, null);
        this.CF = CF;
    }

    public String getCF() {
        return CF;
    }

    public void setCF(String CF) {
        this.CF = CF;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Stampa abbonamento in corso...\n");
        result.append("Codice Abbonamento: ").append(super.getCodice());
        result.append("\nNominativo: ").append(super.getNominativo()).append(", età: ").append(super.getEta());
        result.append("\nPrezzo: € ").append(super.getPrezzo()).append(", Settore: ").append(super.getSettore().getNome());

        String postoInfo = getInfoPosto();
        if (postoInfo != null) {
            result.append(" ,Posto: ").append(postoInfo);
        }

        result.append("\nValido per: tutte le partite di Campionato");
        return result.toString();
    }

    private String getInfoPosto() {
        return (super.getPosto() == null) ? null : String.valueOf(super.getPosto());
    }
}
