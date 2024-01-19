package GestioneBiglietteriaNuova;

import java.util.ArrayList;
import java.util.List;

public class Curva extends Settore {
    private List<Posto> listaPosti;


    public Curva(String nome, int capienza) {
        super(nome, capienza);
        listaPosti=new ArrayList();
        loadPosti();
    }
    public Posto sceltaPosto(int fila, int numero){
        Posto po=null;
        for(Posto p: listaPosti){
            if(p.getFila()==fila && p.getNumero()==numero){
                po=p;
            }
        }
        if(po != null){
            return po;
        }
        else {
            System.out.println("Impossibile scegliere il posto selezionato. Riprova con un altro posto.");
        }
        return null;
    }

    public List<Posto> elencoPostiDisponibili(){
        List<Posto> postiDisponibili= this.listaPosti;
        for(Biglietto b : this.getListaBiglietti()){
            Posto tmp=b.getPosto();
            postiDisponibili.remove(tmp);
        }
        return postiDisponibili;
    }

    @Override
    public void calcolaPrezzoAbb(Settore se) {
        Tessera t = se.getAbbonamentoCorrente();
        float prezzo = 0;

        if (t.getSettore().getNome().equals("Curva Sud")) {
            if (t.getEta() < 14)
                prezzo = 100;
            else if (t.getEta() >= 14 && t.getEta() < 65)
                prezzo = 170;
            else if (t.getEta() > 65)
                prezzo = 120;
            t.setPrezzo(prezzo);
        } else {
            if (t.getEta() < 14)
                prezzo = 85;
            else if (t.getEta() >= 14 && t.getEta() < 65)
                prezzo = 150;
            else if (t.getEta() > 65)
                prezzo = 100;
            t.setPrezzo(prezzo);
        }
    }

    public void loadPosti() {
        int numeroFile = 10;
        int postiPerFile = 500;

        for (int i = 1; i <= numeroFile; i++) {
            for (int j = 1; j <= postiPerFile; j++) {
                Posto posto = new Posto(i, j);
                this.listaPosti.add(posto);
            }
        }
    }
}