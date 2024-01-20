package Merch;

public class ClientiMerch extends Thread {
    private final String NumeroCliente;
    private MerchShop merchShop;

    public ClientiMerch(String NumeroCliente, MerchShop merchShop) {
        this.NumeroCliente = NumeroCliente;
        this.merchShop = merchShop;
    }

    @Override
    public void run() {
        try {
            if (this.merchShop == null) {
                this.merchShop = new MerchShop();
            }
            merchShop.waitForClient();
            merchShop.ServizioCliente(NumeroCliente);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}