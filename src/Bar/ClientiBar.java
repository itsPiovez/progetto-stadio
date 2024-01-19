package Bar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class ClientiBar implements Runnable {
    private final int NumeroCliente;
    private final Bar bar;

    public ClientiBar(int NumeroCliente, Bar bar) {
        this.NumeroCliente = NumeroCliente;
        this.bar = bar;
    }

    @Override
    public void run() {
        try {
            bar.AspettaClient();
            bar.ServizioCliente(NumeroCliente);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}