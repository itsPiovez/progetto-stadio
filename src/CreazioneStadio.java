import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
 // non guardate questa classe, se riusciamo la implementiamo alla fine
public class CreazioneStadio extends JFrame {

    private List<Tribuna> tribune;

    public CreazioneStadio(List<Tribuna> tribune) {
        this.tribune = tribune;

        setTitle("Mappa dello Stadio");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Aggiungi il pannello della mappa
        StadioPanel stadioPanel = new StadioPanel();
        stadioPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int colonna = e.getX() / StadioPanel.CELL_SIZE;
                int riga = e.getY() / StadioPanel.CELL_SIZE;

                for (Tribuna tribuna : tribune) {
                    tribuna.updateStatoMappa(riga, colonna);
                }

                stadioPanel.repaint();
            }
        });

        add(stadioPanel);
        setVisible(true);
    }

    class StadioPanel extends JPanel {
        static final int CELL_SIZE = 30;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int offset = 0;

            for (Tribuna tribuna : tribune) {
                for (int i = 0; i < tribuna.getMappa().length; i++) {
                    for (int j = 0; j < tribuna.getMappa()[i].length; j++) {
                        if (tribuna.getPostiOccupati()[i][j]) {
                            g.setColor(new Color(255, 100, 100)); // Rosso più chiaro per i posti occupati
                        } else {
                            g.setColor(new Color(100, 255, 100)); // Verde più chiaro per i posti liberi
                        }

                        int x = j * CELL_SIZE + offset;
                        int y = i * CELL_SIZE;

                        g.fillOval(x, y, CELL_SIZE, CELL_SIZE);
                        g.setColor(Color.BLACK);
                        g.drawOval(x, y, CELL_SIZE, CELL_SIZE);
                    }
                }

                offset += tribuna.getMappa()[0].length * CELL_SIZE + 10; // Aggiungi uno spazio tra le tribune
            }

            // Aggiungi una linea nera orizzontale per separare le tribune
            g.setColor(Color.BLACK);
            g.fillRect(0, getHeight() / 2 - 2, getWidth(), 4);
        }
    }

    public static void main(String[] args) {
        Tribuna tribunaA = new Tribuna(4, 5, 'A');
        Tribuna tribunaB = new Tribuna(3, 6, 'B');

        List<Tribuna> tribune = List.of(tribunaA, tribunaB);

        SwingUtilities.invokeLater(() -> {
            new CreazioneStadio(tribune);
        });
    }
}
