package GestioneMatch;

public class StatsPartita {
    Squadra sq1;
    Squadra sq2;
    public StatsPartita(Squadra sq1, Squadra sq2){
        this.sq1=sq1;
        this.sq2=sq2;
    }
    public void stampaStats(Squadra sq1, Squadra sq2){
        String goals=sq1.getNome()+" "+sq1.getPunteggio()+"  -  "+sq2.getPunteggio()+" "+sq2.getNome();
        System.out.println(goals);
        String cartellinig = "cartellini gialli: "+sq1.getNcartgialli()+"    -   "+sq2.getNcartgialli();
        System.out.println(cartellinig);
        String cartellinir= "cartellini rossi: "+sq1.getNcartrossi()+"   -    "+sq2.getNcartrossi();
        System.out.println(cartellinir);

    }
}
