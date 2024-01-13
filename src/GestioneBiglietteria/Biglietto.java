package GestioneBiglietteria;

public class Biglietto extends Tessera{
    private int sovrapprezzo;

    public Biglietto(String codice,Settore settore,Partita partita){
        super(codice,null,settore,partita);
        sovrapprezzo=0;
    }

    public int getSovrapprezzo() {
        return sovrapprezzo;
    }

    public void setSovrapprezzo(int sovrapprezzo) {
        this.sovrapprezzo = sovrapprezzo;
    }

    @Override
    public String toString() {
        float prezzoFinale=super.getPrezzo()+sovrapprezzo;

        if(super.getPosto() == null && sovrapprezzo == 0)
            return "Stampa biglietto in corso...\n" + "Codice biglietto: " + super.getCodice() + "\nNominativo: " + super.getNominativo() +
                    ", età: " + super.getEta() +"\nValido per la partita: "+super.getPartita()+ "\n"+ "Prezzo: € " + super.getPrezzo() + "\n"
                    + "Settore: " + super.getSettore().getNome() + ", Posto:  ";

        else if(super.getPosto() != null && sovrapprezzo == 0)
            return "Stampa biglietto in corso...\n" + "Codice biglietto: " + super.getCodice() + "\nNominativo: " + super.getNominativo() +
                    ", età: " + super.getEta() +"\nValido per la partita: "+super.getPartita()+ "\n"+ "Prezzo: € " + super.getPrezzo() + "\n"
                    + "Settore: " + super.getSettore().getNome() + ", Posto: "+super.getPosto();

        else if(super.getPosto() == null && sovrapprezzo != 0)
            return "Stampa biglietto in corso...\n" + "Codice biglietto: " + super.getCodice() + "\nNominativo: " + super.getNominativo() +
                    ", età: " + super.getEta() +"\nValido per la partita: "+super.getPartita()+ "\n"+ "Prezzo: € " + super.getPrezzo() +
                    " + Tariffa standard cambio nominativo: €5.0 = €"+prezzoFinale+"\n"
                    + "Settore: " + super.getSettore().getNome() + ", Posto:  ";

        else
            return "Stampa biglietto in corso...\n" + "Codice biglietto: " + super.getCodice() + "\nNominativo: " + super.getNominativo() +
                    ", età: " + super.getEta() +"\nValido per la partita: "+super.getPartita()+ "\n"+ "Prezzo: € " + super.getPrezzo() +
                    " + Tariffa standard cambio nominativo: €5.0 = €"+prezzoFinale+"\n"
                    + "Settore: " + super.getSettore().getNome() + ", Posto: "+super.getPosto();
    }



}
