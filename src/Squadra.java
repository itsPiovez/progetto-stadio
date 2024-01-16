import java.util.ArrayList;
import java.util.List;

class Squadra {
    private List<Giocatore> arrayGiocatori;
    private String nome;
    private int numeroGiocatoriTot;
    private int punteggio;

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

    // Altri metodi per gestire la squadra
}
