package GestioneBiglietteriaNuova;
public class Persona {
    String nome;
    String cognome;
    int eta;
    char sesso;
    public Persona(String nome, String cognome, int eta, char sesso) {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.sesso = sesso;
    }
    public String getNome(){
        return nome;
    }
    public String getCognome(){
        return cognome;
    }
    public int getEta(){
        return eta;
    }
    public char getSesso(){
        return sesso;
    }
}

