package GestionePrezzi;

import GestioneBiglietteriaNuova.Partita;
import GestioneBiglietteriaNuova.Tessera;
public class PrezzoStrategy implements PrezzoStrategyInterface{

     @Override
     public float applicaTariffa(Tessera t) {
          Partita pa=t.getPartita();
          String tipologia= pa.getTipologia();
          int eta=t.getEta();
          float base=t.getSettore().getPrezzoBiglietto();
          float prezzo=0;

          switch(tipologia){
               case "Campionato":
                    if(eta<14)
                         prezzo= base - ((base *20)/100);
                    else if(eta>65)
                         prezzo= base - ((base *30)/100);
                    else
                         prezzo=base;
                    break;
               case "Coppa Italia", "Supercoppa Italiana":
                    if(eta<14)
                         prezzo= base - ((base *10)/100);
                    else if(eta>65)
                         prezzo= base - ((base *15)/100);
                    else
                         prezzo=base;
                    break;
               case "Champions League":
                    if(eta<14)
                         prezzo= base - ((base *5)/100);
                    else if(eta>65)
                         prezzo= base - ((base *10)/100);
                    else
                         prezzo=base;
                    break;
               case "Amichevole":
                    if(eta<14)
                         prezzo= base - ((base *50)/100);
                    else if(eta>65)
                         prezzo= base - ((base *55)/100);
                    else
                         prezzo=base;
                    break;
          }
          return prezzo;
     }

}