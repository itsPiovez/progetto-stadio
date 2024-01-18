package Bar;
public class ClienteBar implements Runnable {
    private String nome;
    private Bar bar;

    public ClienteBar(String nome, Bar bar) {
        this.nome = nome;
        this.bar = bar;
    }
    public Bar getBar() {
        return bar;
    }

    @Override
    public void run() {
        // I clienti scelgono casualmente dall'intero menu
        Coda<String> menu = bar.getMenu();
        String ordine = menu.pop();

        // Ogni cliente esegue l'ordine e il consumo in un thread separato
        bar.servireCliente(nome, ordine);
    }
}