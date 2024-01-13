package GestioneBiglietteria;

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

    public void loadPosti(){
        Posto p1 = new Posto(1,1);
        Posto p2 = new Posto(1,2);
        Posto p3 = new Posto(1,3);
        Posto p4 = new Posto(1,4);
        Posto p5 = new Posto(1,5);
        Posto p6 = new Posto(1,6);
        Posto p7 = new Posto(1,7);
        Posto p8 = new Posto(1,8);
        Posto p9 = new Posto(1,9);
        Posto p10 = new Posto(1,10);
        this.listaPosti.add(p1);
        this.listaPosti.add(p2);
        this.listaPosti.add(p3);
        this.listaPosti.add(p4);
        this.listaPosti.add(p5);
        this.listaPosti.add(p6);
        this.listaPosti.add(p7);
        this.listaPosti.add(p8);
        this.listaPosti.add(p9);
        this.listaPosti.add(p10);
    }
}