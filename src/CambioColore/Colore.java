package CambioColore;

import java.util.Random;

public class Colore {
    public static final String reset = "\u001B[0m";
    public static final String black = "\u001B[30m";
    public static final String red = "\u001B[31m";
    public static final String green = "\u001B[32m";
    public static final String yellow = "\u001B[33m";
    public static final String blue = "\u001B[34m";
    public static final String purple = "\u001B[35m";
    public static final String cyan = "\u001B[36m";
    public static final String white = "\u001B[37m";

    public static String getRandomColor() {
        Random random = new Random();
        int colorIndex = random.nextInt(8); // Scegli un numero casuale tra 0 e 7

        switch (colorIndex) {
            case 0:
                return white;
            case 1:
                return red;
            case 2:
                return green;
            case 3:
                return yellow;
            case 4:
                return blue;
            case 5:
                return purple;
            case 6:
                return cyan;
            case 7:
                return white;
            default:
                return reset; // Default a reset nel caso qualcosa vada storto
        }
    }

    public static String getRandomColor(String text) {
        return getRandomColor() + text + reset;
    }
    public static String colorize(String text, String color) {
        return color + text + reset;
    }
}
