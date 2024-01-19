package TransferMarket;

import java.text.NumberFormat;
import java.util.*;

public class Transfermarket extends Thread{
    @Override
    public void run() {
        super.run();
    }

    public  Transfermarket() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\n - - - TRANSFERMARKET - - - ");

        // Inserisci il nome del giocatore (assicurati che l'input non sia un numero)
        System.out.print("\nInserisci il nome del giocatore: ");
        String nomeGiocatore = "";
        boolean inputValidoGiocatore = false;

        while (!inputValidoGiocatore) {
            nomeGiocatore = scanner.nextLine();
            if (nomeGiocatore.matches(".*\\d+.*")) {
                System.out.println("Inserisci un nome valido senza numeri.");
                System.out.print("Inserisci il nome del giocatore: ");
            } else {
                inputValidoGiocatore = true;
            }
        }

        // Inserisci il market value del giocatore (assicurati che l'input sia un intero)
        int marketValue = 0;
        boolean inputValido = false;

        while (!inputValido) {
            try {
                System.out.print("Inserisci il market value del giocatore (in milioni di €): ");
                marketValue = Integer.parseInt(scanner.nextLine()) * 1000000;
                inputValido = true;
            } catch (NumberFormatException e) {
                System.out.println("Inserisci un valore numerico valido.");
            }
        }

        // Inserisci il nome del club venditore (assicurati che l'input non sia un numero)
        System.out.print("Club disponibili: Real Madrid, Barcellona, Juventus, Milan, Inter, Ajax, Liverpool, Roma, Chelsea, Manchester United, Manchester City, Bayern, PSG");
        System.out.print("\nInserisci il nome del club venditore: ");
        String nomeClubVenditore = "";
        boolean inputValidoClubVenditore = false;

        while (!inputValidoClubVenditore) {
            nomeClubVenditore = scanner.nextLine();
            if (nomeClubVenditore.matches(".*\\d+.*")) {
                System.out.println("Inserisci un nome valido senza numeri.");
                System.out.print("Inserisci il nome del club venditore: ");
            } else {
                inputValidoClubVenditore = true;
            }
        }

        Giocatore giocatore = new Giocatore(nomeGiocatore, marketValue);
        Club clubVenditore = new Club(nomeClubVenditore);

        System.out.println("Market value del giocatore " + giocatore.getNome() + ": " +
                formatCurrency(giocatore.getMarketValue()));

        List<Club> clubAcquirenti = new ArrayList<>();
        clubAcquirenti.add(new Club("Real Madrid"));
        clubAcquirenti.add(new Club("Barcellona"));
        clubAcquirenti.add(new Club("Juventus"));
        clubAcquirenti.add(new Club("Milan"));
        clubAcquirenti.add(new Club("Inter"));
        clubAcquirenti.add(new Club("Ajax"));
        clubAcquirenti.add(new Club("Liverpool"));
        clubAcquirenti.add(new Club("Roma"));
        clubAcquirenti.add(new Club("Chelsea"));
        clubAcquirenti.add(new Club("Manchester United"));
        clubAcquirenti.add(new Club("Manchester City"));
        clubAcquirenti.add(new Club("Bayern"));
        clubAcquirenti.add(new Club("PSG"));

        AgentiCalcistici agentiCalcistici = new AgentiCalcistici();

        // Thread del venditore
        new Thread(() -> {
            List<Club> squadreAcquirentiRandom = new ArrayList<>(clubAcquirenti);
            Collections.shuffle(squadreAcquirentiRandom);

            int trattativeFallite = 0;
            for (Club clubAcquirente : squadreAcquirentiRandom) {
                int prezzoIniziale = new Random().nextInt(1000000) + 500000;
                int durataContratto = new Random().nextInt(5) + 1;  // Contratto di durata massima 5 anni
                int salarioGiocatore = new Random().nextInt(5000000) + 1000000;  // Salario tra 1 e 5 milioni di €
                int bonus = new Random().nextInt(1000000) + 500000;  // Bonus tra 500.000 e 1.500.000 €
                int clausolaRescissione = new Random().nextInt(20000000) + 5000000;  // Clausola tra 5 e 25 milioni di €

                agentiCalcistici.negoziaTrattativa(clubVenditore, clubAcquirente, giocatore, prezzoIniziale,
                        durataContratto, salarioGiocatore, bonus, clausolaRescissione);

                if (!agentiCalcistici.isTrattativaAccettata()) {
                    trattativeFallite++;
                } else {
                    break;
                }
            }

            if (trattativeFallite == clubAcquirenti.size()) {
                System.out.println("Nessuna trattativa riuscita.");
            }
        }).start();

        // Thread dell'acquirente
        new Thread(() -> {
            agentiCalcistici.completaTrattativa();
        }).start();

        scanner.close();
}

    private static String formatCurrency(int amount) {
        return NumberFormat.getCurrencyInstance(Locale.ITALY).format(amount);
    }
}
