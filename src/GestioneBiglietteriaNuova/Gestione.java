package GestioneBiglietteriaNuova;

import GestioneErrori.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Gestione {
    private static Gestione tifoticket;
    private Partita partitaCorrente;
    private Partita partitaScelta;
    private Map<String, Partita> listaPartite;
    private Stadio stadio;

    private Gestione() {
        stadio = new Stadio("Allianz Stadium", 40000);
        this.listaPartite = new HashMap<>();
    }

    public static synchronized Gestione getInstance() {
        if (tifoticket == null)
            tifoticket = new Gestione();
        else
            System.out.println("Istanza già creata");
        return tifoticket;
    }

    public boolean inserisciNuovaPartita(String codice, LocalDateTime data, String avversario, String tipologia) throws PartitaErrore {
        Partita p = listaPartite.get(codice);
        if (p == null) {
            if (!controllaSovrapposizione(data)) {
                if (tipologia != null && isTipologiaValida(tipologia)) {
                    if (tipologia.equals("Campionato"))
                        this.partitaCorrente = new Partita(codice, data, avversario, tipologia, stadio);
                    else
                        this.partitaCorrente = new Partita(codice, data, avversario, tipologia, null);
                    System.out.println("Partita inserita");
                } else {
                    throw new PartitaErrore("Errore nell'immissione della tipologia.");
                }
            } else {
                throw new PartitaErrore("Partita in sovrapposizione con un'altra.");
            }
        } else {
            throw new PartitaErrore("Codice partita già presente");
        }

        return true;
    }

    private boolean isTipologiaValida(String tipologia) {
        return tipologia.equals("Campionato") || tipologia.equals("Coppa Italia")
                || tipologia.equals("Champions League") || tipologia.equals("Amichevole")
                || tipologia.equals("Supercoppa Italiana");
    }


    public boolean controllaSovrapposizione(LocalDateTime data) {
        for (Map.Entry<String, Partita> entry : listaPartite.entrySet()) {
            if (entry.getValue().getData().isEqual(data))
                return true; // si sovrappone
        }
        return false;
    }

    public String impostaPrezzoBiglietto(String nomeSettore, float prezzoBiglietto) {
        String res;
        res = partitaCorrente.impostaPrezzoBiglietti(nomeSettore, prezzoBiglietto);
        return res;
    }

    public void confermaInserimento() {
        if (partitaCorrente != null) {
            this.listaPartite.put(partitaCorrente.getCodice(), partitaCorrente);
            partitaCorrente = null;
            System.out.println("Inserimento partita concluso con successo.");
        }
    }

    public Map<String, Partita> cercaPartita(String avversario) {
        Map<String, Partita> partiteTrovate = new HashMap<>();
        for (Map.Entry<String, Partita> entry : listaPartite.entrySet()) {
            if (entry.getValue().equalsAvversario(avversario))
                partiteTrovate.put(entry.getKey(), entry.getValue());
        }
        return partiteTrovate;
    }

    public void sceltaSettore(String nomeSettore) throws SettoreErrore {
        int posti_liberi = 0;
        posti_liberi = partitaScelta.sceltaSettore(nomeSettore);
        Stadio st = partitaScelta.getStadio();
        if (posti_liberi == 0)
            throw new SettoreErrore("Settore pieno. Scegli un altro settore.");
        else {
            System.out.println("Ci sono ancora " + posti_liberi + " posti disponibili in " + nomeSettore + ".");
            //if (nomeSettore.contains("Tribuna"))
              //  System.out.println("Elenco posti liberi in tribuna:\n" + st.elencoPostiDisponibili(nomeSettore).toString());
        }
    }

    public String sceltaPosto(int fila, int numero) throws PostoErrore {
        return partitaScelta.sceltaPosto(fila, numero);
    }

    public void datiCliente(String nominativo, int eta) {
        partitaScelta.datiCliente(nominativo, eta);
    }

    public void confermaAcquisto() {
        Biglietto bi = partitaScelta.confermaAcquisto();
        partitaScelta = null;
        System.out.println(bi);
    }

    public int settoreAbbonamento(String nomeSettore) throws SettoreErrore {
        Settore se = stadio.sceltaSettore(nomeSettore);
        int posti_liberi = se.getCapienza() - se.getListaAbbonamenti().size();
        if (nomeSettore.contains("Tribuna"))
            System.out.println("Elenco posti liberi in tribuna:\n" + stadio.elencoPostiDisponibili(nomeSettore).toString());
        return posti_liberi;
    }

    public void datiClienteAbb(String nominativo, String CF, int eta) throws datiClienteErrore {
        stadio.datiClienteAbb(nominativo, CF, eta);
    }

    public void postoAbbonamento(int fila, int numero) throws PostoErrore {
        Posto po = stadio.postoAbbonamento(fila, numero);
        if (po != null)
            System.out.println("Posto scelto correttamente");
    }

    public Abbonamento confermaAbbonamento() {
        return stadio.confermaAbbonamento();
    }

    public void getDatiVenditeTotali() {
        int counterTot = 0;
        float incassoTot = 0;
        for (Map.Entry<String, Partita> entry : listaPartite.entrySet()) {
            Partita pa = entry.getValue();
            int counter = 0;
            float incasso = 0;
            for (Biglietto bi : pa.getListaBiglietti()) {
                counter++;
                incasso += bi.getPrezzo();
            }
            counterTot += counter;
            incassoTot += incasso;
            System.out.println("Partita: Juventus vs. " + pa.getAvversario() + ", partita di: " + pa.getTipologia() +
                    " del " + pa.getData().getDayOfMonth() + "/" + pa.getData().getMonthValue() + "/" + pa.getData().getYear()
                    + "\nBiglietti venduti: " + counter + "/" + stadio.getCapienza() + ", Incasso: € " + incasso + "\n");
        }
        System.out.println("Totale biglietti venduti: " + counterTot + ", Incasso totale: € " + incassoTot);
    }

    public void getVenditePartita(String codicePartita) {
        Partita pa = listaPartite.get(codicePartita);
        for (Map.Entry<String, Settore> entry : pa.getStadio().getListaSettori().entrySet()) {
            float incasso = 0;
            for (Biglietto bi : entry.getValue().getListaBiglietti())
                incasso += bi.getPrezzo();
            System.out.println("Biglietti venduti in " + entry.getValue().getNome() + ": "
                    + entry.getValue().getListaBiglietti().size() + "/" + entry.getValue().getCapienza() + ", Incasso del settore: € " + incasso);
        }
    }

    public Biglietto sostituzioneNominativo(String codiceBiglietto, String codicePartita, String nuovoNom, int nuovaEta)
            throws NominativoErrore {
        Partita pa = listaPartite.get(codicePartita);
        Biglietto bi = pa.sostituzioneNominativo(codiceBiglietto, nuovoNom, nuovaEta);
        return bi;
    }

    public Partita getPartitaCorrente() {
        return partitaCorrente;
    }

    public void setPartitaCorrente(Partita partitaCorrente) {
        this.partitaCorrente = partitaCorrente;
    }

    public void setPartitaScelta(Partita partitaScelta) {
        this.partitaScelta = partitaScelta;
    }

    public Partita getPartitaScelta() {
        return partitaScelta;
    }

    public Map<String, Partita> getListaPartite() {
        return listaPartite;
    }

    public Partita getPartita(String codicePartita) {
        return this.listaPartite.get(codicePartita);
    }

    public Stadio getStadio() {
        return stadio;
    }

    public void setStadio(Stadio stadio) {
        this.stadio = stadio;
    }
}
