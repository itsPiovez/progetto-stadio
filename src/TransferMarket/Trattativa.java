package TransferMarket;

import java.text.NumberFormat;
import java.util.*;

public class Trattativa {
    private Club clubVenditore;
    private Club clubAcquirente;
    private Giocatore giocatore;
    private int prezzoTrattativa;
    private boolean successo;

    // Nuovi attributi per i dettagli del contratto
    private int durataContratto;  // in anni
    private int salarioGiocatore; // in €
    private int bonus;
    private int clausolaRescissione;

    public Trattativa(Club clubVenditore, Club clubAcquirente, Giocatore giocatore, int prezzoTrattativa,
                      int durataContratto, int salarioGiocatore, int bonus, int clausolaRescissione, boolean successo) {
        this.clubVenditore = clubVenditore;
        this.clubAcquirente = clubAcquirente;
        this.giocatore = giocatore;
        this.prezzoTrattativa = prezzoTrattativa;
        this.durataContratto = durataContratto;
        this.salarioGiocatore = salarioGiocatore;
        this.bonus = bonus;
        this.clausolaRescissione = clausolaRescissione;
        this.successo = successo;
    }

    public Club getClubVenditore() {
        return clubVenditore;
    }

    public Club getClubAcquirente() {
        return clubAcquirente;
    }

    public Giocatore getGiocatore() {
        return giocatore;
    }

    public int getPrezzoTrattativa() {
        return prezzoTrattativa;
    }

    public boolean isSuccesso() {
        return successo;
    }

    public int getDurataContratto() {
        return durataContratto;
    }

    public int getSalarioGiocatore() {
        return salarioGiocatore;
    }

    public int getBonus() {
        return bonus;
    }

    public int getClausolaRescissione() {
        return clausolaRescissione;
    }

    // Metodo per stampare i dettagli del contratto
    public void stampaDettagliContratto() {
        System.out.println("\nDettagli del contratto per " + giocatore.getNome() +
                " tra " + clubAcquirente.getNome() + " e " + clubVenditore.getNome());
        System.out.println("• Durata contratto: " + durataContratto + " anni");
        System.out.println("• Salario del giocatore: " + formatCurrency(salarioGiocatore) + " all'anno");
        System.out.println("• Bonus: " + formatCurrency(bonus));
        System.out.println("• Clausola di rescissione: " + formatCurrency(clausolaRescissione));
    }

    private String formatCurrency(int amount) {
        return NumberFormat.getCurrencyInstance(Locale.ITALY).format(amount);
    }
}