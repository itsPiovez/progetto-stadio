public class Bar
{
    private boolean stapulendo=false;
    private boolean staaprendo=false;
    public synchronized void stapulendo(){
        stapulendo=true;
        staaprendo=false;
        System.out.println("Il barista sta pulendo il bar e sta preparando i panini");
    }
    public synchronized void staaprendo(){
        staaprendo=true;
        stapulendo=false;
        System.out.println("Il barista ha aperto il bar");
    }
    public void serve(String Tifoso,String item){
        if(staaprendo){
            System.out.println("Il barista vende un "+item+" a "+Tifoso);
        }else {
            System.out.println("Il barista sta pulendo il bar e sta preparando i panini");
        }
    }
    public void run(){
        try{
            if(staaprendo){
                String[]items={"panino","birra","acqua","bibite"};
                String item=items[(int)(Math.random()*items.length)];
                //Bar.serve(Tifoso,item);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
