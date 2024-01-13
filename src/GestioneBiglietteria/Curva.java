package GestioneBiglietteria;

public class Curva extends Settore{

    public Curva(String nome, int capienza){
        super(nome,capienza);
    }

    @Override
    public void calcolaPrezzoAbb(Settore se){
        Tessera t=se.getAbbonamentoCorrente();
        float prezzo=0;

        if(t.getSettore().getNome().equals("Curva Sud")){
            if(t.getEta()<14)
                prezzo=100;
            else if(t.getEta()>=14 && t.getEta()<65)
                prezzo= 170;
            else if(t.getEta()>65)
                prezzo=120;
            t.setPrezzo(prezzo);
        }
        else{
            if(t.getEta()<14)
                prezzo=85;
            else if(t.getEta()>=14 && t.getEta()<65)
                prezzo= 150;
            else if(t.getEta()>65)
                prezzo=100;
            t.setPrezzo(prezzo);
        }

    }
}