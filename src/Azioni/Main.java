package Azioni;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import Bar.Bar;
import Merch.*;

import java.util.List;

import static Bagni.Bagno.c;

public class Main {
    private Bar bar;
    public Bar getBar() {
        return bar;
    }

    public static void main(String[] args) {
        MerchShop merchShop = new MerchShop();
        merchShop.Apertura();
        Ristorante.RistoranteCreazione ristoranteCreazione = new Ristorante.RistoranteCreazione();
        Bagni.BagniCreazione bagniCreazione = new Bagni.BagniCreazione();
        Bar bar = new Bar(Bar.menu);
        bar.apri();
        List<Tifoso> tot = CreaTifo.CreaTifoso(100);
        if (tot != null && !tot.isEmpty()) {
            // voglio che i tifosi che entrano in questo ciclo vengono sbalzati nella classe azionetifoso
            for (Tifoso tifoso : tot) {
                AzioneTifoso azioneTifoso = new AzioneTifoso(tifoso.GetNome());
                new Thread(azioneTifoso).start();
            }
        }
        /*Coda<String> coda = new Coda<String>();
        Bar bar = new Bar(coda);

        // Thread del barista
        new Thread(() -> {
            bar.apri();

            for (int i = 1; i <= 10; i++) {
                new Thread(new ClienteBar("Cliente" + i, bar)).start();
                try {
                    Thread.sleep(1000); // Aggiungi un ritardo tra i clienti
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Attendi che tutti i clienti abbiano terminato prima di chiudere il bar
            try {
                synchronized (bar.getMonitor()) {
                    while (!coda.isEmpty()) {
                        bar.getMonitor().wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Chiudi il bar
            bar.chiudi();
        }).start();*/


        /*MerchShop merchShop = new MerchShop();
        merchShop.Apertura();

        List<Thread> Cliente= new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < NumeroClienti; i++) {
            int NumeroCliente = random.nextInt(NumeroClienti) + 1;
            Thread ClienteThread = new Clienti(NumeroCliente, merchShop);
            Cliente.add(ClienteThread);
            ClienteThread.start();
        }

        for (Thread customerThread : Cliente) {
            try {
                customerThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }

        merchShop.ChiudiMerchShop();*/
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
