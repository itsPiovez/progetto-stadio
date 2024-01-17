package Merch;

import java.util.ArrayList;
import java.util.List;

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

        // Visualizza la merce disponibile
        merchShop.displayAvailableMerch();

        // Simula l'acquisto di un cliente
        Merch purchasedItem = merchShop.sellMerch("Cappello", null, "Nero");

        if (purchasedItem != null) {
            System.out.println("Cliente ha acquistato: " + purchasedItem);
        } else {
            System.out.println("Prodotto non disponibile.");
        }

        // Visualizza la merce disponibile dopo l'acquisto
        merchShop.displayAvailableMerch();
    }
}
