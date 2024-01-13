package GestioneBiglietteria;

import GestioneErrori.NominativoErrore;
import GestioneErrori.PostoErrore;
import GestioneErrori.SettoreErrore;
import GestionePrezzi.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Partita {
    private String codice;
    private LocalDateTime data;
    private String avversario;
    private String tipologia;
    private Tessera bigliettoCorrente;
    private Stadio stadio;
    private List<Biglietto> listaBiglietti;

    public Partita(String codice, LocalDateTime data, String avversario, String tipologia,Stadio stadio) {
        this.codice = codice;
        this.data = data;
        this.avversario = avversario;
        this.tipologia = tipologia;
        if(stadio!=null)
            this.stadio=stadio;
        else
            this.stadio=new Stadio("Allianz Stadium", 30);
        this.listaBiglietti=new ArrayList<>();
    }

    public String impostaPrezzoBiglietti(String nomeSettore,float prezzo){
        String res;
        res=stadio.impostaPrezzo(nomeSettore,prezzo);
        return res;
    }

    public int sceltaSettore(String nomeSettore) throws SettoreErrore {
        int posti_liberi=0;
        Settore se=stadio.sceltaSettore(nomeSettore);
        posti_liberi=se.getCapienza()-se.getListaBiglietti().size()-se.getListaAbbonamenti().size();
        if(posti_liberi>0){
            String codiceBiglietto=UUID.randomUUID().toString().substring(0,5);
            bigliettoCorrente=new Biglietto(codiceBiglietto,se,this);
        }
        return posti_liberi;
    }

    public String sceltaPosto(int fila,int numero) throws PostoErrore {
        Posto po=null;
        String res=null;
        po=stadio.sceltaPosto(fila,numero);
        if(po!=null){
            bigliettoCorrente.setPosto(po);
            res="Il posto scelto è stato selezionato correttamente.";
        }
        return res;
    }

    public void datiCliente(String nominativo, int eta){
        bigliettoCorrente.setNominativo(nominativo);
        bigliettoCorrente.setEta(eta);
        System.out.println("Dati cliente inseriti con successo");
    }

    public Biglietto confermaAcquisto(){
        Settore se=bigliettoCorrente.getSettore();

        bigliettoCorrente.setPrezzo(calcolaPrezzo());
        listaBiglietti.add((Biglietto)bigliettoCorrente);
        se.getListaBiglietti().add((Biglietto)bigliettoCorrente);

        Biglietto tmp=(Biglietto)bigliettoCorrente;

        bigliettoCorrente=null;
        return tmp;
    }

    public float calcolaPrezzo(){
        PrezzoStrategyFactory pf= PrezzoStrategyFactory.getInstance();
        PrezzoStrategyInterface pi= pf.getPrezzoStrategy();
        float prezzoFinale=pi.applicaTariffa(bigliettoCorrente);

        return prezzoFinale;
    }

    public Biglietto sostituzioneNominativo(String codiceBiglietto, String nuovoNom, int nuovaEta) throws NominativoErrore {
        if(!this.listaBiglietti.isEmpty()){
            for(Biglietto bi : this.listaBiglietti){
                if(bi.getCodice().equals(codiceBiglietto)){
                    if(bi.getEta()<14 && nuovaEta<14 || bi.getEta()>65 && nuovaEta>65 || bi.getEta()>14 && bi.getEta()<65 && nuovaEta>14 && nuovaEta<65){
                        bi.setNominativo(nuovoNom);
                        bi.setEta(nuovaEta);
                        bi.setSovrapprezzo(5);
                        return bi;
                    }
                    else
                        throw new NominativoErrore("Impossibile eseguire la sostituzione per 2 tariffe diverse.");
                }
                else
                    throw new NominativoErrore("Codice biglietto non valido.");
            }
        }
        else
            throw new NominativoErrore("Non sono ancora stati venduti biglietti per questa partita.");
        return null;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getAvversario() {
        return avversario;
    }

    public void setAvversario(String avversario) {
        this.avversario = avversario;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public Tessera getBigliettoCorrente() {
        return bigliettoCorrente;
    }

    public void setBigliettoCorrente(Tessera bigliettocorrente) {
        this.bigliettoCorrente = bigliettocorrente;
    }

    public Stadio getStadio() {
        return stadio;
    }

    public void setStadio(Stadio stadio) {
        this.stadio = stadio;
    }

    public List<Biglietto> getListaBiglietti() {
        return listaBiglietti;
    }

    public void setListaBiglietti(List<Biglietto> listaBiglietti) {
        this.listaBiglietti = listaBiglietti;
    }

    @Override
    public String toString() {
        return "Juventus vs " + avversario +", partita di: " + tipologia
                +" del "+ data.getDayOfMonth()+"/"+data.getMonthValue()+"/"+data.getYear()+" Orario: "+data.getHour()+":"+data.getMinute()+
                ". Codice Partita: "+codice;
    }

    public boolean equalsAvversario(String avv){
        return (this.avversario.equals(avv));
    }
}