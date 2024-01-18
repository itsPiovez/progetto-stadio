package Ristorante;

import java.util.HashMap;
import java.util.Map;

public class Menu {
    private Map<String, Double> primi;
    private Map<String, Double> secondi;
    private Map<String, Double> contorni;
    private Map<String, Double> bevande;
    private Map<String, Double> dolci;
    private Map<String, Integer> tempiDiPreparazione;
    private Map<String, Double> piatti;
    private Map<String, Double> menu;

    public Menu() {
        primi = new HashMap<>();
        secondi = new HashMap<>();
        contorni = new HashMap<>();
        bevande = new HashMap<>();
        dolci = new HashMap<>();
        piatti = new HashMap<>();
        tempiDiPreparazione = new HashMap<>();

        piatti.put("Pasta", 8.50);
        piatti.put("Pizza", 10.00);
        piatti.put("Insalata", 6.50);
        piatti.put("Bistecca", 15.00);
        piatti.put("Pesce", 12.00);
        piatti.put("Hamburger", 8.00);
        piatti.put("Patatine", 3.00);
        piatti.put("Bibita", 2.00);
        piatti.put("Vino", 10.00);
        piatti.put("Caffè", 1.50);
        piatti.put("Torta", 4.00);
        piatti.put("Gelato", 3.00);
        piatti.put("Frutta", 2.00);
        piatti.put("Acqua", 1.50);
        piatti.put("Tiramisù", 4.00);
        piatti.put("Crostata", 3.00);
        piatti.put("Crepes", 3.00);

        primi.put("Pasta", 8.50);
        primi.put("Pizza", 10.00);
        primi.put("Hamburger", 8.00);
        secondi.put("Bistecca", 15.00);
        secondi.put("Pesce", 12.00);
        contorni.put("Insalata", 6.50);
        contorni.put("Frutta", 2.00);
        contorni.put("Patatine", 3.00);
        bevande.put("Bibita", 2.00);
        bevande.put("Vino", 10.00);
        bevande.put("Acqua", 1.50);
        dolci.put("Caffè", 1.50);
        dolci.put("Torta", 4.00);
        dolci.put("Gelato", 3.00);
        dolci.put("Tiramisù", 4.00);
        dolci.put("Crostata", 3.00);
        dolci.put("Crepes", 3.00);

        tempiDiPreparazione.put("Pasta", 50000);  // Tempo di preparazione in millisecondi
        tempiDiPreparazione.put("Pizza", 40000);
        tempiDiPreparazione.put("Insalata", 10000);
        tempiDiPreparazione.put("Bistecca", 30000);
        tempiDiPreparazione.put("Pesce", 25000);
        tempiDiPreparazione.put("Hamburger", 10500);
        tempiDiPreparazione.put("Patatine", 10000);
        tempiDiPreparazione.put("Bibita", 500);
        tempiDiPreparazione.put("Vino", 500);
        tempiDiPreparazione.put("Caffè", 1500);
        tempiDiPreparazione.put("Torta", 1000);
        tempiDiPreparazione.put("Gelato", 1500);
        tempiDiPreparazione.put("Frutta", 1500);
        tempiDiPreparazione.put("Tiramisù", 1000);
        tempiDiPreparazione.put("Crostata", 1000);
        tempiDiPreparazione.put("Crepes", 10000);
        tempiDiPreparazione.put("Acqua", 500);
        // Aggiungi altri piatti e tempi di preparazione secondo necessità
    }
    public Map<String, Double> getPiatti() {
        return piatti;
    }


    public Double getPrezzo(String piatto) {
        return piatti.getOrDefault(piatto, 0.0);
    }

    public Integer getTempoDiPreparazione(String piatto) {
        return tempiDiPreparazione.getOrDefault(piatto, 0);
    }
}

