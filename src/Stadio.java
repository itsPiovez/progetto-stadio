import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Stadio extends Thread{
    private Tribuna a;
    private Tribuna b;
    private Tribuna c;
    private Tribuna d;
    public Stadio(Tribuna a, Tribuna b, Tribuna c, Tribuna d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public void run() {
        a.start();
        b.start();
        c.start();
        d.start();
        /*
        List<Tribuna> tribune = List.of(a, b, c, d);

        SwingUtilities.invokeLater(() -> {
            new CreazioneStadio(tribune);
        });*/
    }
}
