package Azioni;

import GestioneBiglietteriaNuova.Gestione;

import java.util.ArrayList;
import java.util.List;
public class CreaTifo{
    public static List<Tifoso> ModificaTifoso(List<GestioneBiglietteriaNuova.Tifoso> tifosi){
        List<Tifoso> tifosi2 = new ArrayList<>();
        if(tifosi.size() == 0){
            for (int i = 1; i < 10000; i++) {
                tifosi2.add(new Tifoso("Tifoso " + i));
            }
            return tifosi2;
        }
        int i=0;
        for (GestioneBiglietteriaNuova.Tifoso tifoso : tifosi) {
            i++;
            tifosi2.add(new Tifoso(tifoso.GetNome()));
        }
        return tifosi2;
    }
}
