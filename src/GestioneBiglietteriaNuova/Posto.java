package GestioneBiglietteriaNuova;

public class Posto {
    private int fila;
    private int numero;

    public Posto(int fila, int numero) {
        if (fila <= 0 || numero <= 0) {
            throw new IllegalArgumentException("La fila e il numero del posto devono essere valori positivi.");
        }

        this.fila = fila;
        this.numero = numero;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        if (fila <= 0) {
            throw new IllegalArgumentException("La fila deve essere un valore positivo.");
        }

        this.fila = fila;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        if (numero <= 0) {
            throw new IllegalArgumentException("Il numero del posto deve essere un valore positivo.");
        }

        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Fila: " + fila + " Numero: " + numero;
    }
}
