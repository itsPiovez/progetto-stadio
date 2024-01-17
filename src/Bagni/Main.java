package Bagni;

import java.util.ArrayList;
import java.util.List;

import java.util.Random;
public class Main {
    public static void main(String[] args) {

        List<Toilet> donne = new ArrayList<>();
        List<Toilet> uomini = new ArrayList<>();
        int id = 0;
        //creo 3 bagni per uomini e 3 per donne
        for (int i = 0; i < 3; i++) {
            donne.add(new Toilet("donna"));
            uomini.add(new Toilet("uomo"));
        }
        Coda<Persona> c = new Coda<Persona>(); //creo coda per i bagni
        for(int i=0;i<15;i++){
            Persona u = new Persona("uomo",i);
            Persona d = new Persona("donna",i);
            c.push(u);
            c.push(d);
        }

        int tempo=650;
        //creo i bagni
        Bagno b = new Bagno(donne, uomini, id, c); //creo bagno
        b.start();
        while(true) {
            if (b.haRotto()) {
                System.out.println("I bagni sono stati puliti ");
                break;
            } else {
                try {
                    Thread.sleep(tempo*c.lenght());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}