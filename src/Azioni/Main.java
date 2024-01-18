package Azioni;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //MerchShop merchShop = new MerchShop();
        //merchShop.Apertura();
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
/*











        //MERCH
        MerchShop merchShop = new MerchShop();
        // Aggiungi alcuni prodotti al negozio
        merchShop.addMerch(new Merch("Maglietta", "M", "Rosso", 25.99));
        merchShop.addMerch(new Merch("Cappello", null, "Nero", 15.99));
        merchShop.addMerch(new Merch("Pupazzo", null, "Blu", 12.99));
        merchShop.addMerch(new Merch("Maglietta", "S", "Bianco", 25.99));
        merchShop.addMerch(new Merch("Cappello", null, "Blu", 15.99));
        merchShop.addMerch(new Merch("Pupazzo", null, "Rosso", 12.99));



        // Visualizza la merce disponibile
        merchShop.displayAvailableMerch();

        // Simula l'acquisto di un cliente
        //funzione random con il risultato il merch da acquistare
        // Simula l'acquisto di un cliente
        Random random = new Random();
        int merchCasuale = random.nextInt(merchShop.availableMerch.size());
        Merch purchasedItem = merchShop.availableMerch.get(merchCasuale);
        if (purchasedItem != null) {
            System.out.println("Cliente ha acquistato: " + purchasedItem);
        }

        // Visualizza la merce disponibile dopo l'acquisto
        merchShop.displayAvailableMerch();
        */

    }
}
