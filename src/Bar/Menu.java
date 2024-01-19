package Bar;
import java.util.List;
import java.util.ArrayList;
public class Menu {
    private List<String> piatti;
    private List<Double> prezzi;

    public Menu() {
        piatti = new ArrayList<>();
        prezzi = new ArrayList<>();

        piatti.add("Panino");
        prezzi.add(5.00);

        piatti.add("Birra");
        prezzi.add(3.00);

        piatti.add("Patatine");
        prezzi.add(2.50);

        piatti.add("Coca Cola");
        prezzi.add(1.50);

        piatti.add("Acqua");
        prezzi.add(1.00);
    }

    public double getPrezzo(String piatto) {
        int index = piatti.indexOf(piatto);
        if (index != -1) {
            return prezzi.get(index);
        } else {
            return 0.0; // Piatto non trovato nel menu
        }
    }
}
