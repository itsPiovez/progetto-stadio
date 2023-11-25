import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class Tribuna extends Thread {
    private int quantitàA;
    private int quantitàL;
    private Posto posti[][];
    private int tifosiInTribuna = 0;
    private boolean[][] postiOccupati;
    private char[][] mappa;


    public Tribuna(int quantitàA, int quantitàL, char i) {
        this.quantitàA = quantitàA;
        this.quantitàL = quantitàL;
        this.setName("Tribuna" + i);
        this.posti = new Posto[quantitàA][quantitàL];
        this.postiOccupati = new boolean[quantitàA][quantitàL];
        this.mappa = new char[quantitàA][quantitàL];
    }

    @Override
    public void run() {
        // Assegna posti ai tifosi
        for (int row = 0; row < quantitàA; row++) {
            for (int col = 0; col < quantitàL; col++) {
                if (tifosiInTribuna < quantitàA * quantitàL) {
                    Tifoso nuovoTifoso = new Tifoso("Tifoso" + tifosiInTribuna);
                    posti[row][col] = new Posto(nuovoTifoso, true);
                    postiOccupati[row][col] = true;
                    mappa[row][col] = '1';
                    tifosiInTribuna++;
                    System.out.println(getName() + ": " + nuovoTifoso.getNome() + " occupa il posto [" + row + "][" + col + "]");
                }
            }
        }
    }

    public boolean[][] getPostiOccupati() {
        return postiOccupati;
    }

    public char[][] getMappa() {
        return mappa;
    }

    public String getNome() {
        return getName();
    }

    public void updateStatoMappa(int riga, int colonna) {
        if (postiOccupati[riga][colonna]) {
            postiOccupati[riga][colonna] = false;
            mappa[riga][colonna] = '0';
        } else {
            postiOccupati[riga][colonna] = true;
            mappa[riga][colonna] = '1';
        }
    }

}



