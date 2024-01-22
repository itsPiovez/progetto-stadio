package GestioneMatch;

import java.util.ArrayList;
import java.util.List;

class Giocatore extends Thread {

    private Boolean dormi;

    Tempo t;
    private int id;
    private String nomeMaglia;
    private String nomeSquadra;
    private int cartellini;
    private Squadra squadra;
    private Palla palla;  // Aggiungi un campo Palla
    public AzioneGiocatore azione;



    public Giocatore(int id, String nomeMaglia, String nomeSquadra, Squadra squadra, Palla palla,Arbitro arbitro, Tempo t) {
        this.id = id;
        this.nomeMaglia = nomeMaglia;
        this.nomeSquadra = nomeSquadra;
        this.cartellini = 0;
        this.squadra = squadra;
        this.palla = palla;  // Inizializza il campo palla
        this.t=t;
        this.azione=new AzioneGiocatore(this,arbitro,t);
        this.dormi=false;

    }

    public void run() {
        try {
            while (true) {
                // Attendere finché pausaGiocatori è true
                while (dormi==true) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                // Esegui l'azione solo se il giocatore è in possesso della palla
                palla.acquisisciPalla(this.getNomeMaglia() + " ha la palla al minuto: " + Tempo.getTempo());

                // Simuliamo un'azione del giocatore
                // System.out.println(this.getNomeMaglia() + " sta eseguendo un'azione.");
                try {
                    azione.eseguiAzioneCasuale();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                // Rilascia la palla dopo aver eseguito l'azione
                palla.rilasciaPalla(this.getNomeMaglia() + " rilascia la palla al minuto: " + Tempo.getTempo());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (Exception e) {
            // Gestisci le eccezioni qui, se necessario
            e.printStackTrace();
        }
    }


    // Metodi getter rimossi per brevità

    public String getNomeMaglia() {
        return nomeMaglia;
    }
    public void addormenta(){
        this.dormi=true;
    }
    public void sveglia(){
        this.dormi=false;
    }
    public String getNome() {
        return nomeMaglia;
    }
    public void incrementaCartellini() {
        cartellini++;
    }
    public int getCartellini() {
        return cartellini;
    }
    public String getNomeSquadra() {
        return nomeSquadra;
    }

    public Squadra getSquadra() {
        return this.squadra; // Restituisce l'oggetto Squadra associato al giocatore
    }
}
