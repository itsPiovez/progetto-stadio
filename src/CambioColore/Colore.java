package CambioColore;

import java.util.Random;

public class Colore {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static String getRandomColor() {
        Random random = new Random();
        int colorIndex = random.nextInt(8); // Scegli un numero casuale tra 0 e 7

        switch (colorIndex) {
            case 0:
                return ANSI_WHITE;
            case 1:
                return ANSI_RED;
            case 2:
                return ANSI_GREEN;
            case 3:
                return ANSI_YELLOW;
            case 4:
                return ANSI_BLUE;
            case 5:
                return ANSI_PURPLE;
            case 6:
                return ANSI_CYAN;
            case 7:
                return ANSI_WHITE;
            default:
                return ANSI_RESET; // Default a reset nel caso qualcosa vada storto
        }
    }

    public static String getRandomColor(String text) {
        return getRandomColor() + text + ANSI_RESET;
    }
    public static String colorize(String text, String color) {
        return color + text + ANSI_RESET;
    }
}
