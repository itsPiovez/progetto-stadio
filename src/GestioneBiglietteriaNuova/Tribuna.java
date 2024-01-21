package GestioneBiglietteriaNuova;

import java.util.ArrayList;
import java.util.List;

public class Tribuna extends Settore {
    private List<Posto> listaPosti;

    public Tribuna (String nome, int capienza){
        super(nome,capienza);
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
    public void calcolaPrezzoAbb(Settore se){
        Tessera t= se.getAbbonamentoCorrente();
        float prezzo=0;

        if(t.getSettore().getNome().equals("Tribuna Est")){
            if(t.getEta()<14)
                prezzo=200;
            else if(t.getEta()>=14 && t.getEta()<65)
                prezzo= 270;
            else if(t.getEta()>65)
                prezzo=230;
            t.setPrezzo(prezzo);
        }
        else{
            if(t.getEta()<14)
                prezzo=220;
            else if(t.getEta()>=14 && t.getEta()<65)
                prezzo= 320;
            else if(t.getEta()>65)
                prezzo=270;
            t.setPrezzo(prezzo);
        }
    }

    public Posto scegliPostoAbbonamento(int fila, int numero){
        Posto po=null;
        for(Posto p: listaPosti){
            if(p.getFila()==fila && p.getNumero()==numero)
                po=p;
        }
        if(po!= null) {
            return po;
        }
        else {
            System.out.println("Impossibile scegliere il posto selezionato. Riprova con un altro posto.");
        }
        return null;
    }

    public List<Posto> getListaPosti() {
        return listaPosti;
    }

    public void setListaPosti(ArrayList<Posto> listaPosti) {
        this.listaPosti = listaPosti;
    }

    @Override
    public String toString() {
        return "Tribuna{" +
                "nome='" + nome + '\'' +
                ", listaPosti=" + listaPosti +
                '}';
    }

    public void loadPosti() {
        int numeroFile = 30;
        int postiPerFile = 500;

        for (int i = 1; i <= numeroFile; i++) {
            for (int j = 1; j <= postiPerFile; j++) {
                Posto posto = new Posto(i, j);
                this.listaPosti.add(posto);
            }
        }
    }
}