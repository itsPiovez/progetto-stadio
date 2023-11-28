// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        //Create a new squadra
        Squadra squadra1 = new Squadra("Juventus", 11);
        //Create a new squadra
        Squadra squadra2 = new Squadra("Inter", 11);
        // finish the main
        System.out.println("La partita sta per iniziare");
        // start the squadra
        squadra1.start();
        // start the squadra
        squadra2.start();
        
    }
}
