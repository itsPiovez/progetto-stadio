package Merch;

class Clienti extends Thread {
    private final int NumeroCliente;
    private final MerchShop merchShop;

    public Clienti(int NumeroCliente, MerchShop merchShop) {
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