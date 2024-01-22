package GestioneMatch;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

class Squadra {
    private List<Giocatore> arrayGiocatori;
    private String nome;
    private int numeroGiocatoriTot;
    private int punteggio;
    private int ncartgialli;
    private int ncartrossi;

    public Squadra(String nome, int numeroGiocatoriTot) {
        this.nome = nome;
        this.numeroGiocatoriTot = numeroGiocatoriTot;
        this.arrayGiocatori = new ArrayList<>(); // Inizializza l'array dei giocatori
        this.punteggio = 0;  // Inizializza il punteggio a zero

    }

    public void sostituisciGiocatore(Giocatore uscente, Giocatore entrante) {
        int index = trovaIndiceGiocatore(uscente);
        if (index != -1) {
            arrayGiocatori.set(index, entrante); // Sostituisce il giocatore uscente con quello entrante
            System.out.println("Sostituzione effettuata: " + uscente.getNome() + " esce, " + entrante.getNome() + " entra");
        } else {
            System.out.println("Giocatore uscente non trovato nella squadra");
        }
    }

    private int trovaIndiceGiocatore(Giocatore giocatore) {
        for (int i = 0; i < arrayGiocatori.size(); i++) {
            if (arrayGiocatori.get(i).getId() == giocatore.getId()) {
                return i; // Restituisce l'indice del giocatore da sostituire
            }
        }
        return -1; // Giocatore non trovato
    }

    public void aggiungiGiocatore(Giocatore giocatore) {
        if (arrayGiocatori.size() < numeroGiocatoriTot) {
            arrayGiocatori.add(giocatore);
        } else {
            System.out.println("Numero massimo di giocatori raggiunto");
        }
    }

    public int getNumeroGiocatori() {
        return arrayGiocatori.size();
    }

    public Giocatore getGiocatore(int i) {
        return arrayGiocatori.get(i);
    }

    public String getNome() {
        return nome;
    }

    public Giocatore[] getArrayGiocatori() {
        return arrayGiocatori.toArray(new Giocatore[0]);
    }
    public void incrementaPunteggio() {
        this.punteggio++;
    }
    public void incrementaGialli() {
        this.ncartgialli++;
    }
    public void incrementaRossi() {
        this.ncartrossi++;
    }
    public int getNcartgialli(){
        return ncartgialli;
    }
    public int getNcartrossi(){
        return ncartrossi;
    }
    public int getPunteggio() {
        return punteggio;
    }

    //metodo addormenta squadra
    public void addormentaSquadra(){
        for(Giocatore g:arrayGiocatori){
            g.addormenta();
        }
    }
    //metodo sveglia squadra
    public void svegliaSquadra(){
        for(Giocatore g:arrayGiocatori){
            g.sveglia();
        }
    }




}
