package MainGenarale;
import Azioni.*;
import Merch.*;
import Bar.*;
import Ristorante.*;
import Bagni.*;
import TransferMarket.*;
import java.util.List;
import CambioColore.Colore;
import Annunci.*;
import java.util.Scanner;
import GestioneBiglietteriaNuova.*;
public class Main {
    public static void main(String[] args) {
        int i=0;
        System.out.println("In questo menu puoi scegliere la modalita di visualizzazione delle azioni dei tifosi");
        System.out.println("1) Visualizza le azioni della biglietteria");
        System.out.println("2) Visualizza le azioni dei tifosi nello stadio");
        System.out.println("3) Visualizza le azioni del match di calcio");
        System.out.println("4) Esci");
        Scanner scanner = new Scanner(System.in);
        i = scanner.nextInt();
        switch (i){
            case 1:
                GestioneBiglietteriaNuova.Main.main(args);
                break;
            case 2:
                Azioni.Main.main(args);
                break;
            case 3:
                //MatchCalcio.Main.main(args);
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Inserisci un numero valido");
                break;
        }

    }
}
