package Bar;

import java.util.List;

public class ClienteBar implements Runnable {
    private String nome;
    private Bar bar;

    public ClienteBar(String nome, Bar bar) {
        this.nome = nome;
        this.bar = bar;
    }

    @Override
    public void run() {
        // I clienti scelgono casualmente dall'intero menu
        List<String> menu = bar.getMenu();
        int randomIndex = (int) (Math.random() * menu.size());
        String ordine = menu.get(randomIndex);

        // Ogni cliente esegue l'ordine e il consumo in un thread separato
        bar.servireCliente(nome, ordine);
    }
}

