package Merch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MerchShop {
    private List<Merch> availableMerch;

    public MerchShop() {
        this.availableMerch = new ArrayList<>();
    }

    public void addMerch(Merch merch) {
        availableMerch.add(merch);
    }

    public void displayAvailableMerch() {
        System.out.println("Available Merchandise:");
        for (Merch merch : availableMerch) {
            System.out.println(merch);
        }
    }
    private static MerchShop instance = null;

    public static MerchShop getInstance() {
        if (instance == null) {
            instance = new MerchShop();
        }
        return instance;
    }

    public Merch sellMerch(String type, String size, String color) {
        for (Merch merch : availableMerch)
        {
            if (merch.getType().equalsIgnoreCase(type) && (merch.getSize() == null || merch.getSize().equalsIgnoreCase(size)) && merch.getColor().equalsIgnoreCase(color))
            {
                availableMerch.remove(merch);
                return merch;
            }
        }
        return null; // Il prodotto richiesto non è disponibile
    }

    public static void main(String[] args)
    {
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
    }
}
