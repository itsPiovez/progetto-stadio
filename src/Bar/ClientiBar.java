package Bar;
import Merch.MerchShop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class ClientiBar extends Thread {
    private final String nome;
    private Bar bar;

    public ClientiBar(String nome, Bar bar) {
        this.nome = nome;
        this.bar = bar;
    }

    @Override
    public void run() {
        try {
           // bar.waitForClient(); // Aspetta finché ci sono clienti nel bar
            bar.ServizioCliente(nome);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}