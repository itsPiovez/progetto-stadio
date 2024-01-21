package Bagni;

import java.util.Random;

public class Persona extends Thread {
    String tipo; // donna o uomo
    String nome;
    private Toilet t;

    Random r = new Random(); // durata uso bagno
    private Bagno b;

    public Persona(String tipo, String nome,Toilet t) {
        super(nome); // Inizializza il thread con il nome della persona
        this.nome = nome;
        this.tipo = tipo;
        this.t = t;
    }

    public String getTipo() {
        return tipo;
    }

    public void setToilet(Toilet t) {
        this.t = t;
    }

    public Toilet getToilet() {
        return t;
    }

    public void setBagno(Bagno b) {
        this.b = b;
    }

    public void usa_bagno() {
        String TID = nome;
        if(t!=null){
            t.usa_bagno(TID, r, this); // Aggiungi la persona come argomento
        }
        try {
            Thread.sleep(r.nextInt(5) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        // Assicurati che la persona abbia una toilet associata
        if (t == null) {
            System.err.println("La persona non ha una toilet associata.");
            return;
        }
        //usa_bagno();
    }

}
