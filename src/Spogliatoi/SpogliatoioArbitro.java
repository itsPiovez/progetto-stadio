package Spogliatoi;
import Personale.Arbitro;
import Personale.Assistenti;
import Personale.Guardialinee;

import java.util.ArrayList;

public class  SpogliatoioArbitro extends Thread{
    private Arbitro arbitro;
    private ArrayList<Assistenti>assistenti;
    private ArrayList<Guardialinee>guardialinee;
    public SpogliatoioArbitro(Arbitro arbitro, ArrayList<Assistenti>assistenti, ArrayList<Guardialinee>guardialinee){
        this.guardialinee=guardialinee;
        this.arbitro = arbitro;
        this.assistenti=assistenti;
    }


    public Arbitro getArbitro() {
        return arbitro;
    }
    public ArrayList<Assistenti> getAssistenti(){
        return assistenti;
    }
    public ArrayList<Guardialinee> getGuardialinee(){
        return guardialinee;
    }

    @Override
    public void run() {
        //Arrivano gli arbitri, gli assistenti e le guardialinee allo spogliatoio
        //li metto in sleep di tot tempo perchè arrivano prima dell'inizio della partita
        try {
            Thread.sleep(100000); // Sleep per 100 secondi
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        arbitro.start();
        assistenti.forEach(Assistenti::start);
        guardialinee.forEach(Guardialinee::start);

    }
}

