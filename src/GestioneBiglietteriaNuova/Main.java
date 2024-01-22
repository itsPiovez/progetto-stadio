package GestioneBiglietteriaNuova;

import GestioneErrori.*;
import GestioneLoginRegister.LoginRegister;
import GestioneLoginRegister.User;
import GestioneParcheggio.Parcheggio;
import GestioneSicurezza.Medici;
import GestioneSicurezza.Steward;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Main {
    public static void stampaMenuAdmin(){
        System.out.println("\n*** MENU ADMIN ***");
        System.out.println("1. Inserimento nuova Partita");
        System.out.println("2. Visualizza informazioni partite");
        System.out.println("3. Visualizza informazioni vendite per una specifica partita");
        System.out.println("4. Visualizza informazioni vendite biglietti complessive");
        System.out.println("5. Stampa stato del parcheggio e numero di posti occupati");
        System.out.println("6. Percentuale di occupazione del parcheggio");
        System.out.println("7. Logout");
        System.out.println("0. USCITA");
        System.out.println("Scegli l'operazione: ");
    }
    public static void stampaMenuUtente(){
        System.out.println("\n*** MENU UTENTE ***");
        System.out.println("1. Visualizza informazioni partite");
        System.out.println("2. Vendita Biglietto");
        System.out.println("3. Sostituzione nominativo su Biglietto");
        System.out.println("4. Vendita Abbonamento");
        System.out.println("5. Parcheggia auto");
        System.out.println("6. Logout");
        System.out.println("0. USCITA");
        System.out.println("Scegli l'operazione: ");
    }

    public static boolean generaTifosi(){
        int scelta = 0;
        boolean controllo = false;
        int numeroTifosiDaGenerare = 40000;
        List<Tifoso> tifosiGenerati = new ArrayList<>();
        Steward s = new Steward(tifosiGenerati, numeroTifosiDaGenerare);
        Medici m = new Medici(tifosiGenerati, numeroTifosiDaGenerare);
        s.start();
        m.start();

        for (int i = 0; i < numeroTifosiDaGenerare; i++) {
            // Genera casualmente 0 o 1 per decidere tra Juve e squadra ospite
            scelta = new Random().nextInt(2);

            // Se scelta è 0, il tifoso tiferà per la Juve, altrimenti per la squadra ospite
            String squadraTifoso = (scelta == 0) ? "Juve" : "Squadra Ospite";

            // Genera il tifoso con la squadra assegnata casualmente
            Tifoso nuovoTifoso = Tifoso.generaTifoso(squadraTifoso);

            // Simula le azioni degli steward e dei medici
            nuovoTifoso.effettuaControlloSteward();
            nuovoTifoso.effettuaSoccorsoMedici();

            // Aggiungi il tifoso alla lista
            tifosiGenerati.add(nuovoTifoso);
        }


        // Stampa informazioni sui tifosi generati
        System.out.println("\nTifosi generati:");
        for (Tifoso tifoso : tifosiGenerati) {
            System.out.println("Nome: " + tifoso.getNome() +
                    ", Cognome: " + tifoso.getCognome() +
                    ", Età: " + tifoso.getEta() +
                    ", Sesso: " + tifoso.getSesso() +
                    ", Squadra Tifata: " + tifoso.squadraTifata +
                    ", Oggetto non permesso: " + tifoso.haOggettoNonPermesso() +
                    ", Si è fatto male: " + tifoso.siEFattoMale());
        }
        s.terminate();
        m.terminate();
        return controllo;
    }

    public static void main(String args[]){
        Gestione gestione = Gestione.getInstance();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int scelta=0;
        boolean controllo = true;

        LoginRegister loginRegister = new LoginRegister();
        Parcheggio parcheggio = new Parcheggio(20); // Sostituisci 20 con il numero di posti nel parcheggio

        do {
            loginRegister.displayMenu();

            do {
                try {
                    System.out.println("\n\tBIGLIETTERIA ALLIANZ STADIUM");
                    User currentUser = loginRegister.getCurrentUser();
                    if (currentUser != null) {
                        if (currentUser.getRole().equals("admin")) {
                            stampaMenuAdmin();
                        } else {
                            stampaMenuUtente();
                        }
                    }

                    while(true){
                        try {

                            scelta = Integer.parseInt(br.readLine());
                            if (currentUser.getRole().equals("admin")) {
                                while (scelta < 0 || scelta > 7) {
                                    System.err.println("ERRORE: Inserire un numero tra 0 e 7.");
                                    System.out.println("\nScegli l'operazione: ");
                                    scelta = Integer.parseInt(br.readLine());
                                }
                                break;
                            } else {
                                while (scelta < 0 || scelta > 6) {
                                    System.err.println("ERRORE: Inserire un numero tra 0 e 6.");
                                    System.out.println("\nScegli l'operazione: ");
                                    scelta = Integer.parseInt(br.readLine());
                                }
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.err.println("ERRORE: Inserire un numero valido.");
                            System.out.println("\nScegli l'operazione: ");
                        }
                    }

                    if (currentUser != null && currentUser.getRole().equals("admin")){
                        switch (scelta) {
                            case 0:
                                System.out.println("\nGrazie! Arrivederci!");
                                loginRegister.logout();
                                controllo = generaTifosi();
                                break;
                            case 1:
                                System.out.println("\nInserisci il codice della partita: ");
                                String codice = br.readLine();

                                System.out.println("\nInserisci la data della partita (formato YYYY-MM-DD): ");
                                String dataInput = br.readLine();
                                LocalDate dataPartita = LocalDate.parse(dataInput);

                                System.out.println("Inserisci l'ora della partita (formato HH:mm): ");
                                String oraInput = br.readLine();
                                LocalTime oraPartita = LocalTime.parse(oraInput);

                                LocalDateTime dataOraPartita = LocalDateTime.of(dataPartita, oraPartita);

                                System.out.println("\nInserisci il nome della squadra avversaria: ");
                                String avversario = br.readLine();

                                System.out.println("Inserisci la tipologia della partita: ");
                                String tipologiaPartita = br.readLine();

                                if (!gestione.inserisciNuovaPartita(codice, dataOraPartita, avversario, tipologiaPartita)) {
                                    System.out.println("Inserimento partita annullato.");
                                    break;
                                } else {
                                    Map<String, Settore> listaSettori = gestione.getStadio().getListaSettori();

                                    for (Map.Entry<String, Settore> entry : listaSettori.entrySet()) {
                                        System.out.println("Inserisci il prezzo base del biglietto per la " + entry.getKey() + ": ");
                                        float prezzo = Float.parseFloat(br.readLine());
                                        String res = gestione.impostaPrezzoBiglietto(entry.getKey(), prezzo);
                                        System.out.println(res);
                                    }

                                    System.out.println("\nPremi Invio per confermare l'inserimento dei dati.");
                                    if (br.readLine().isEmpty()) {
                                        gestione.confermaInserimento();
                                    } else {
                                        System.out.println("Inserimento partita annullato.");
                                    }
                                }
                                break;
                            case 2:
                            case 3:
                                System.out.println("\nPartite attualmente disponibili: ");
                                Map<String, Partita> listaPartite = gestione.getListaPartite();

                                if (listaPartite.isEmpty()) {
                                    System.out.println("ERRORE: Nessuna partita presente in memoria");
                                    break;
                                } else {
                                    for (Map.Entry<String, Partita> entry : listaPartite.entrySet()) {
                                        System.out.println("Codice Partita: " + entry.getKey() + ", Partita: " + entry.getValue() + "\n");
                                    }
                                }

                                if (scelta == 2) {
                                    break;
                                }

                                System.out.println("Scegli la partita inserendo il codice: ");
                                codice = br.readLine();

                                if (listaPartite.containsKey(codice)) {
                                    System.out.println("\nRecupero informazioni sulle vendite in corso...");
                                    gestione.getVenditePartita(codice);
                                } else {
                                    System.out.println("ERRORE: Il codice inserito è errato.");
                                    break;
                                }
                                break;
                            case 4:
                                System.out.println("\nRecupero informazioni sulle vendite in corso...");
                                gestione.getDatiVenditeTotali();
                                break;
                            case 5:
                                parcheggio.stampaStato();
                                System.out.println("Numero totale di posti occupati: " + parcheggio.conteggioPostiOccupati());
                                break;
                            case 6:
                                System.out.println("Percentuale di occupazione: " + parcheggio.percentualeOccupazione() + "%");
                            case 7:
                                System.out.println("\nLogout!\n");
                                loginRegister.logout();
                                break;
                        }
                    }
                    else {
                        switch (scelta) {
                            case 0:
                                System.out.println("\nGrazie! Arrivederci!");
                                loginRegister.logout();
                                controllo = generaTifosi();
                                break;
                            case 1:
                                System.out.println("\nPartite attualmente disponibili: ");
                                if(gestione.getListaPartite().isEmpty()){
                                    System.out.println("ERRORE: Nessuna partita presente in memoria");
                                    break;
                                }
                                else{
                                    for(Map.Entry<String,Partita> entry : gestione.getListaPartite().entrySet())
                                        System.out.println("Codice Partita: "+entry.getKey()+", Partita: "+entry.getValue()+"\n");
                                }
                                break;
                            case 2:
                                System.out.println("\nInserisci il nome della squadra avversaria: ");
                                String avversario=br.readLine();
                                Map<String,Partita> pa= gestione.cercaPartita(avversario);
                                if(pa.isEmpty()){
                                    System.err.println("Nessuna partita trovata. Riprova.");
                                    break;
                                }
                                else{
                                    System.out.println("\nElenco partite trovate: ");
                                    for(Map.Entry<String,Partita>entry : pa.entrySet())
                                        System.out.println("Codice partita: "+entry.getKey()+", Partita: "+entry.getValue()+"\n");
                                    System.out.println("Scegli la partita inserendo il codice: ");
                                    String codice = br.readLine();
                                    gestione.setPartitaScelta(pa.get(codice));
                                    if(pa.get(codice)==null){
                                        System.out.println("Il codice inserito non appartiene a nessuna partita. Riprova");
                                        break;
                                    }
                                    System.out.println("\nPartita selezionata: "+pa.get(codice));
                                    System.out.println("\nInserisci il settore per cui vuoi vendere un biglietto: ");
                                    String settore=br.readLine();
                                    try {
                                        gestione.sceltaSettore(settore);
                                    }
                                    catch(ConcurrentModificationException cme){
                                        System.out.println(gestione.getPartitaScelta().getStadio().elencoPostiDisponibili(settore).toString());
                                    } catch (SettoreErrore ex) {
                                        System.err.println("ERRORE: "+ex.getMessage());
                                        break;
                                    }
                                    if (settore.contains("Tribuna")) {
                                        while (true) {
                                            try {
                                                System.out.println("\nInserisci la fila (1-30): ");
                                                int fila = Integer.parseInt(br.readLine());

                                                if (fila < 1 || fila > 30) {
                                                    System.out.println("Il numero di fila deve essere compreso tra 1 e 30. Riprova.");
                                                    continue;
                                                }

                                                System.out.println("Inserisci il numero del posto scelto (1-500): ");
                                                int numero = Integer.parseInt(br.readLine());

                                                if (numero < 1 || numero > 500) {
                                                    System.out.println("Il numero del posto deve essere compreso tra 1 e 500. Riprova.");
                                                    continue;
                                                }

                                                String res = gestione.sceltaPosto(fila, numero);
                                                if (res != null) {
                                                    System.out.println(res);
                                                    break;
                                                }
                                            } catch (NumberFormatException ex) {
                                                System.err.println("ERRORE: Inserire un numero valido.");
                                            } catch (PostoErrore ex) {
                                                System.err.println("ERRORE: " + ex.getMessage());
                                                System.out.println("Vuoi reinserire i dati? (Sì/No): ");
                                                String risposta = br.readLine().toLowerCase();

                                                if (!risposta.equals("sì") && !risposta.equals("si")) {
                                                    System.out.println("Vendita Biglietto annullata.");
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    if (settore.contains("Curva")) {
                                        while (true) {
                                            try {
                                                System.out.println("\nInserisci la fila (1-10): ");
                                                int fila = Integer.parseInt(br.readLine());

                                                if (fila < 1 || fila > 10) {
                                                    System.out.println("Il numero di fila deve essere compreso tra 1 e 10. Riprova.");
                                                    continue;
                                                }

                                                System.out.println("Inserisci il numero del posto scelto (1-500): ");
                                                int numero = Integer.parseInt(br.readLine());

                                                if (numero < 1 || numero > 500) {
                                                    System.out.println("Il numero del posto deve essere compreso tra 1 e 500. Riprova.");
                                                    continue;
                                                }

                                                String res = gestione.sceltaPosto(fila, numero);
                                                if (res != null) {
                                                    System.out.println(res);
                                                    break;
                                                }
                                            } catch (NumberFormatException ex) {
                                                System.err.println("ERRORE: Inserire un numero valido.");
                                            } catch (PostoErrore ex) {
                                                System.err.println("ERRORE: " + ex.getMessage());
                                                System.out.println("Vuoi reinserire i dati? (Sì/No): ");
                                                String risposta = br.readLine().toLowerCase();

                                                if (!risposta.equals("sì") && !risposta.equals("si")) {
                                                    System.out.println("Vendita Biglietto annullata.");
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    System.out.println("\nInserisci nome e cognome del cliente: ");
                                    String nominativo=br.readLine();
                                    System.out.println("Inserisci l'età del cliente: ");
                                    int eta=Integer.parseInt(br.readLine());
                                    gestione.datiCliente(nominativo, eta);
                                    System.out.println("\nInvio per confermare l'inserimento dei dati.");
                                    if(br.readLine().length()==0)
                                        gestione.confermaAcquisto();
                                    else
                                        System.out.println("Vendita Biglietto annullata.");
                                    break;
                                }
                            case 3:
                                System.out.println("\nInserisci il codice del biglietto: ");
                                String codiceBiglietto = br.readLine();
                                System.out.println("\nInserisci il codice della partita: ");
                                String codicePartita = br.readLine();

                                if (!gestione.getListaPartite().containsKey(codicePartita)) {
                                    System.out.println("\nIl codice inserito non corrisponde a nessuna partita.");
                                    break;
                                }

                                System.out.println("\nPartita trovata");

                                System.out.println("\nInserisci il nuovo nominativo da inserire sul biglietto: ");
                                String nuovoNominativo = br.readLine();
                                System.out.println("\nInserisci l'età da sostituire sul biglietto: ");
                                int nuovaEta = Integer.parseInt(br.readLine());

                                System.out.println("\nInvio per confermare l'inserimento dei dati.");
                                if (br.readLine().isEmpty()) {
                                    Biglietto bigliettoSostituito = gestione.sostituzioneNominativo(codiceBiglietto, codicePartita, nuovoNominativo, nuovaEta);

                                    if (bigliettoSostituito == null) {
                                        break;
                                    } else {
                                        System.out.println(bigliettoSostituito);
                                    }
                                } else {
                                    System.out.println("Sostituzione nominativo Biglietto annullata.");
                                    break;
                                }
                                break;
                            case 4:
                                System.out.println("\nInserisci il settore per cui vuoi vendere un abbonamento annuale: ");
                                String settoreAbbonamento = br.readLine();

                                try {
                                    int postiLiberi = gestione.settoreAbbonamento(settoreAbbonamento);

                                    if (postiLiberi == 0) {
                                        System.out.println("Non ci sono posti liberi in questo settore. Scegli un altro settore.");
                                        break;
                                    } else {
                                        System.out.println("Ci sono ancora " + postiLiberi + " posti liberi in " + settoreAbbonamento);
                                    }
                                } catch (SettoreErrore ex) {
                                    System.err.println(ex.getMessage());
                                    break;
                                }

                                System.out.println("\nInserisci nome e cognome del cliente: ");
                                String nominativoAbbonamento = br.readLine();

                                System.out.println("\nInserisci il codice fiscale del cliente: ");
                                String codiceFiscale = br.readLine();

                                System.out.println("\nInserisci l'età del cliente: ");
                                int etaAbbonamento = Integer.parseInt(br.readLine());

                                try {
                                    gestione.datiClienteAbb(nominativoAbbonamento, codiceFiscale, etaAbbonamento);
                                } catch (datiClienteErrore ex) {
                                    System.err.println("ERRORE: " + ex.getMessage());
                                    break;
                                }

                                if (settoreAbbonamento.contains("Tribuna")) {
                                    System.out.println("Inserisci la fila: ");
                                    int filaAbbonamento = Integer.parseInt(br.readLine());

                                    System.out.println("Inserisci il numero del posto scelto: ");
                                    int numeroAbbonamento = Integer.parseInt(br.readLine());

                                    try {
                                        gestione.postoAbbonamento(filaAbbonamento, numeroAbbonamento);
                                    } catch (PostoErrore ex) {
                                        System.err.println("ERRORE: " + ex.getMessage());
                                        break;
                                    }
                                }
                                System.out.println("\nInvio per confermare l'inserimento dei dati.");
                                if (br.readLine().isEmpty()) {
                                    System.out.println(gestione.confermaAbbonamento());
                                } else {
                                    System.out.println("Vendita Abbonamento annullata.");
                                    break;
                                }
                                break;
                            case 5:
                                boolean parcheggioRiuscito = parcheggio.parcheggia();
                                if (parcheggioRiuscito) {
                                    System.out.println("Auto parcheggiata con successo.");
                                } else {
                                    System.out.println("Il parcheggio è pieno. Impossibile parcheggiare.");
                                }
                                break;
                            case 6:

                                System.out.println("\nLogout!\n");
                                loginRegister.logout();
                                break;
                        }
                    }
                } catch (IOException ex){
                    System.err.println(ex.getMessage());
                } catch (DateTimeParseException dtpe) {
                    System.err.println("Formato data/ora incorretto. Formati corretti:(AAAA-MM-GG) / (HH:mm)");
                } catch (NumberFormatException nfe) {
                    System.err.println("ERRORE: E' richiesto l'inserimento di un numero");
                } catch (PartitaErrore | NominativoErrore ex) {
                    System.err.println("ERRORE: " + ex.getMessage());
                }
            } while (scelta != 0 && scelta != 6 && scelta != 7);
        }while (controllo);
    }
}