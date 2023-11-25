public class Posto {
    private Tifoso a;
    private Boolean occupato;
    public Posto(Tifoso a,Boolean occupato){
        this.a=a;
        this.occupato=occupato;
    }
    public Boolean Getoccupato(){
        return occupato;
    }

    public Tifoso getTifoso(){
        return a;
    }
}
