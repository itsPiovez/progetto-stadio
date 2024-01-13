package GestioneBiglietteria;

import GestioneErrori.PostoErrore;
import GestioneErrori.SettoreErrore;
import GestioneErrori.datiClienteErrore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Stadio {
    private String nome;
    private int capienza;
    private Map<String, Settore> listaSettori;
    private Settore settoreScelto;
    private Partita partita;

    public Stadio(String nome, int capienza) {
        this.nome = nome;
        this.capienza = capienza;
        this.listaSettori=new HashMap<>();
        loadSettori();
    }

    public String impostaPrezzo(String nomeSettore, float prezzoBiglietto){
        Settore se= listaSettori.get(nomeSettore);
        se.setPrezzoBiglietto(prezzoBiglietto);
        return "Prezzo biglietti inserito";
    }

    public Settore sceltaSettore(String nomeSettore) throws SettoreErrore {
        if(listaSettori.containsKey(nomeSettore)){
            settoreScelto=listaSettori.get(nomeSettore);
            return settoreScelto;
        }
        else
            throw new SettoreErrore("Settore non trovato.");
    }

    public List<Posto> elencoPostiDisponibili(String nomeSettore){
        Tribuna tr= (Tribuna)listaSettori.get(nomeSettore);
        List <Posto> listaDisponibili=tr.elencoPostiDisponibili();
        for(Abbonamento a : settoreScelto.getListaAbbonamenti()){
            for(Posto p : listaDisponibili){
                Posto tmp=a.getPosto();
                if(p.getFila()==tmp.getFila() && p.getNumero() == tmp.getNumero())
                    listaDisponibili.remove(p);
            }
        }
        return listaDisponibili;
    }

    public Posto sceltaPosto(int fila, int numero) throws PostoErrore {
        Tribuna tr=(Tribuna)settoreScelto;
        return tr.sceltaPosto(fila,numero);
    }

    public void datiClienteAbb(String nominativo,String CF,int eta) throws datiClienteErrore{
        settoreScelto.datiClienteAbb(nominativo, CF, eta);
    }

    public Posto postoAbbonamento(int fila, int numero) throws PostoErrore {
        return settoreScelto.postoAbbonamento(fila, numero);
    }

    public Abbonamento confermaAbbonamento(){
        Settore tmp=settoreScelto;
        settoreScelto=null;
        return tmp.confermaAbbonamento();
    }

    public void loadSettori(){
        Settore c1=new Curva("Curva Sud",5);
        Settore c2=new Curva("Curva Nord",5);
        Settore t1=new Tribuna("Tribuna Est",10);
        Settore t2=new Tribuna("Tribuna Ovest",10);

        HashMap<String, Settore> settori=new HashMap();
        settori.put("Curva Sud",c1);
        settori.put("Curva Nord",c2);
        settori.put("Tribuna Est",t1);
        settori.put("Tribuna Ovest",t2);
        this.setListaSettori(settori);
    }


    public Map<String, Settore> getListaSettori() {
        return listaSettori;
    }

    public void setListaSettori(HashMap<String, Settore> listaSettori) {
        this.listaSettori = listaSettori;
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

    public Settore getSettoreScelto() {
        return settoreScelto;
    }

    public void setSettoreScelto(Settore settoreScelto) {
        this.settoreScelto = settoreScelto;
    }

    public Partita getPartita() {
        return partita;
    }

    public void setPartita(Partita partita) {
        this.partita = partita;
    }


}