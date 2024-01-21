package Azioni;

import java.util.ArrayList;
import java.util.List;

public class CreaTifo{
    public static List<Tifoso> CreaTifoso(int n){
        List<Tifoso> tifosi = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            tifosi.add(new Tifoso("Tifoso " + i));
        }
        return tifosi;
    }
}
