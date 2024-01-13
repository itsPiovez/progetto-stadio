package GestioneBiglietteria;

import GestioneErrori.PostoErrore;
import GestioneErrori.datiClienteErrore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Settore {
    public String nome;
    private int capienza;
    private float prezzoBiglietto;
    private List<Biglietto> listaBiglietti;
    private List<Abbonamento>listaAbbonamenti;
    private Tessera abbonamentoCorrente;

    public Settore(String nome, int capienza) {
        this.nome = nome;
        this.capienza = capienza;
        this.listaBiglietti=new ArrayList<>();
        this.listaAbbonamenti=new ArrayList<>();
    }

    public abstract void calcolaPrezzoAbb(Settore se);

    public void datiClienteAbb(String nominativo,String CF,int eta) throws datiClienteErrore{
        for(Abbonamento a: listaAbbonamenti){
            if(a.getCF().equals(CF))
                throw new datiClienteErrore("A questo codice fiscale è già associato un abbonamento valido.");
        }
        if(CF.length()!=16)
            throw new datiClienteErrore ("Lunghezza codice fiscale errata.");
        String codice=UUID.randomUUID().toString().substring(0,5);
        abbonamentoCorrente=new Abbonamento(nominativo,codice,this,CF);

        abbonamentoCorrente.setEta(eta);
        this.calcolaPrezzoAbb(this);
    }

    public Posto postoAbbonamento(int fila, int numero) throws PostoErrore {
        Tribuna tr=(Tribuna)this;
        Posto po=tr.scegliPostoAbbonamento(fila,numero);
        abbonamentoCorrente.setPosto(po);
        return po;
    }

    public Abbonamento confermaAbbonamento(){
        this.listaAbbonamenti.add((Abbonamento)abbonamentoCorrente);
        Abbonamento tmp=(Abbonamento)abbonamentoCorrente;
        abbonamentoCorrente=null;
        return tmp;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    public float getPrezzoBiglietto() {
        return prezzoBiglietto;
    }

    public void setPrezzoBiglietto(float prezzoBiglietto) {
        this.prezzoBiglietto = prezzoBiglietto;
    }

    public List<Biglietto> getListaBiglietti() {
        return listaBiglietti;
    }

    public void setListaBiglietti(List<Biglietto> listaBiglietti) {
        this.listaBiglietti = listaBiglietti;
    }

    public List<Abbonamento> getListaAbbonamenti() {
        return listaAbbonamenti;
    }

    public void setListaAbbonamenti(List<Abbonamento> listaAbbonamenti) {
        this.listaAbbonamenti = listaAbbonamenti;
    }

    public Tessera getAbbonamentoCorrente() {
        return abbonamentoCorrente;
    }

    public void setAbbonamentoCorrente(Tessera abbonamentoCorrente) {
        this.abbonamentoCorrente = abbonamentoCorrente;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Settore)) return false;
        Settore settore = (Settore) o;
        return capienza == settore.capienza && Float.compare(settore.prezzoBiglietto, prezzoBiglietto) == 0 && nome.equals(settore.nome);
    }

}