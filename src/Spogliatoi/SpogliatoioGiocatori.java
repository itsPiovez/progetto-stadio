package Spogliatoi;

import java.util.ArrayList;
import Giocatori.Giocatore;
public class SpogliatoioGiocatori {
    //passano una lista di giocatori che dovrebbe essere 2 ( giocatori in casa e giocatori fuori casa)
    private ArrayList<Giocatore> giocatori ;
    private char TipoGiocatori; // se sono giocatori in casa o fuori casa
    public SpogliatoioGiocatori(ArrayList<Giocatore> giocatori, char TipoGiocatori){
        this.giocatori=giocatori; // inizializzo la lista di giocatori
        this.TipoGiocatori=TipoGiocatori; // inizializzo il tipo di giocatori
    }
    public ArrayList<Giocatore> getGiocatori(){
        return giocatori;
    }
    public char getTipoGiocatori(){
        return TipoGiocatori;
    }

}
