package GestioneBiglietteriaNuova;

public class Biglietto extends Tessera {
    private int sovrapprezzo;

    public Biglietto(String codice, Settore settore, Partita partita) {
        super(codice, null, settore, partita);
        sovrapprezzo = 0;
    }

    public int getSovrapprezzo() {
        return sovrapprezzo;
    }

    public void setSovrapprezzo(int sovrapprezzo) {
        this.sovrapprezzo = sovrapprezzo;
    }

    @Override
    public String toString() {
        float prezzoFinale = super.getPrezzo() + sovrapprezzo;
        StringBuilder result = new StringBuilder("Stampa biglietto in corso...\n");
        result.append("Codice biglietto: ").append(super.getCodice());
        result.append("\nNominativo: ").append(super.getNominativo()).append(", età: ").append(super.getEta());
        result.append("\nValido per la partita: ").append(super.getPartita()).append("\n");
        result.append("Prezzo: € ").append(super.getPrezzo()).append(" + Tariffa standard cambio nominativo: €5.0 = €").append(prezzoFinale).append("\n");
        result.append("Settore: ").append(super.getSettore().getNome());

        if (super.getPosto() != null) {
            result.append(", Posto: ").append(super.getPosto());
        }

        return result.toString();
    }
}
