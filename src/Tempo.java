public class Tempo extends Thread{
    private int tempo;
    public Tempo(int tempo){
        this.tempo=tempo;
    }
    public void run(){
        try{
            Thread.sleep(tempo);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
