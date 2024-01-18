package Merch;

public class ClientiMerch extends Thread {
    private final int NumeroCliente;
    private final MerchShop merchShop;

    public ClientiMerch(int NumeroCliente, MerchShop merchShop) {
        this.NumeroCliente = NumeroCliente;
        this.merchShop = merchShop;
    }

    @Override
    public void run() {
        try {
            merchShop.AspettaClient();
            merchShop.ServizioCliente(NumeroCliente);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}