package Annunci;

public class CreaAnnunci {
    public CreaAnnunci(){
        Annunci annunci = new Annunci(GetAnnuncio());
        annunci.start();
    }

    public String[] GetAnnuncio(){
        String[] annuncio = new String[7];

        annuncio[0] ="\u001B[1m"+"\u001B[40m"+" Forza Juve "+"\u001B[0m"+"\n"+"Juve, storia di un grande amore\n" +"Bianco che abbraccia il nero\n" + "Coro che si alza davvero, per te" ;
        annuncio[1] = "\u001B[1m"+"\u001B[40m"+"\u001B[34m"+" Forza Inter "+"\u001B[0m"+"\n"+"E' una gioia infinita"+"\n"+"che dura una vita"+"\n"+" Pazza Inter amala!";
        annuncio[2] = "\u001B[1m"+"\u001B[40m"+"\u001B[31m"+" Forza Milan "+"\u001B[0m"  +"\n"+"Milan, Milan, solo con te\n" + "Milan, Milan, sempre per te\n" +"Questa maglia rossonera\n" +"Che ci fa sognar";
        annuncio[3] = "Ciao Mamma sono in tv";
        annuncio[4] = "\u001B[1m"+"\u001B[44m"+" Forza Napoli "+"\u001B[0m"+"\n"+"Forza napoli napoli napoli oh...oh"+"\n"+"Napoli... napoli... napoli"+"\n"+"La mia napoli";
        annuncio[5] = "\u001B[1m"+"\u001B[43m"+"\u001B[31m"+" Forza Roma "+"\u001B[0m"+ "\n"+"Roma Roma bella\n" +"t'ho dipinta io\n" +"gialla come er sole\n" +"rossa come er core mio";
        annuncio[6] = "\u001B[1m"+"\u001B[44m"+" Forza Lazio "+"\u001B[0m"+"\n"+"Lazio tu non sarai mai sola\n" +"Vola un'aquila nel cielo\n" +"Più in alto sempre volerà\n" +"Insieme a te aquilotto noi voliamo via";

        return annuncio;

    }
    public static final String RESET = "\u001B[0m";
    public static final String[] COLORI_ARCOBALENO = {
            "\u001B[31m",   // Rosso
            "\u001B[33m",   // Giallo
            "\u001B[32m",   // Verde
            "\u001B[36m",   // Ciano
            "\u001B[34m",   // Blu
            "\u001B[35m",   // Magenta
    };
    public static String stampaArcobaleno(String testo) {
        for (int i = 0; i < testo.length(); i++) {
            char carattere = testo.charAt(i);
            int indiceColore = i % COLORI_ARCOBALENO.length;
            String colore = COLORI_ARCOBALENO[indiceColore];

            System.out.print(colore + carattere + RESET);
        }
        return testo;
    }

}
