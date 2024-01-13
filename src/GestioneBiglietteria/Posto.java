package GestioneBiglietteria;

public class Posto {
    private int fila;
    private int numero;

    public Posto(int fila, int numero) {
        this.fila = fila;
        this.numero = numero;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Fila: " + fila + " Numero: " + numero;

    }
}
