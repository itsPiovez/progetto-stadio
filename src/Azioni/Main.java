package Azioni;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import Merch.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        MerchShop merchShop = new MerchShop();
        merchShop.Apertura();
        Ristorante.RistoranteCreazione ristoranteCreazione = new Ristorante.RistoranteCreazione();
        Bagni.BagniCreazione bagniCreazione = new Bagni.BagniCreazione();
        List<Tifoso> tot = CreaTifo.CreaTifoso(100);
        if (tot != null && !tot.isEmpty()) {
            // voglio che i tifosi che entrano in questo ciclo vengono sbalzati nella classe azionetifoso
            for (Tifoso tifoso : tot) {
                AzioneTifoso azioneTifoso = new AzioneTifoso(tifoso.GetNome());
                new Thread(azioneTifoso).start();
            }
        }
    }
}

