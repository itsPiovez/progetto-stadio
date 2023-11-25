public class SpogliatoioArbitro extends Thread{
    private Arbitro arbitro;
    public SpogliatoioArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }

    @Override
    public void run() {

    }
}
