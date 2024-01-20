package Bar;
import Merch.MerchShop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class ClientiBar implements Runnable {
    private final String NumeroCliente;
    private Bar bar;

    public ClientiBar(String NumeroCliente, Bar bar) {
        this.NumeroCliente = NumeroCliente;
        this.bar = bar;
    }

    @Override
    public void run() {
        try {
            if (this.bar == null) {
                this.bar = new Bar();
            }
            bar.waitForClient();
            bar.ServizioCliente(NumeroCliente);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}