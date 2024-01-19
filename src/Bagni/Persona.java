package Bagni;

import java.util.Random;

public class Persona extends Thread {
    String tipo; // donna o uomo
    private int numero;
    public Persona( String tipo, int numero) {
        this.numero = numero;
        this.tipo = tipo;
    }
    Random r = new Random(); // durata uso bagno
    private Toilet t;
    private Bagno b;
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

    @Override
    public void run() {
        String TID = tipo +" "+ numero;
        t.usa_bagno(TID, r);
        try {
            Thread.sleep(r.nextInt(5) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}