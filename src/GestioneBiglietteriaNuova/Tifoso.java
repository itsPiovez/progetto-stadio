package GestioneBiglietteriaNuova;

import java.util.List;
import java.util.Random;

public class Tifoso extends Persona {
    public String squadraTifata;
    private boolean haOggettoNonPermesso;
    private boolean siEFattoMale;

    public Tifoso(String nome, String cognome, int eta, char sesso, String squadraTifata) {
        super(nome, cognome, eta, sesso);
        this.squadraTifata = squadraTifata;
        this.haOggettoNonPermesso = false;
        this.siEFattoMale = false;
    }

    public void setSquadraTifata(String squadraTifata) {
        this.squadraTifata = squadraTifata;
    }

    public boolean haOggettoNonPermesso() {
        return haOggettoNonPermesso;
    }

    public boolean siEFattoMale() {
        return siEFattoMale;
    }

    public void effettuaControlloSteward() {
        // Simulazione di un tifoso con oggetto non permesso con probabilità del 5%
        haOggettoNonPermesso = new Random().nextInt(100) < 5;
    }

    public void effettuaSoccorsoMedici() {
        // Simulazione di un tifoso che si fa male con probabilità del 2%
        siEFattoMale = new Random().nextInt(100) < 2;
    }

    public static Tifoso generaTifoso(String squadraTifata) {
        Random random = new Random();

        String[] nomi = {"Mario", "Luigi", "Giovanni", "Anna", "Laura", "Francesca", "Antonio", "Maria", "Paolo", "Giulia",
                "Marco", "Roberta", "Davide", "Elena", "Simone", "Chiara", "Alessandro", "Silvia", "Riccardo", "Valentina"};

        String[] cognomi = {"Rossi", "Verdi", "Bianchi", "Mancini", "Ricci", "Moretti", "Conti", "Rizzo", "Ferrari", "Lombardi",
                "Galli", "Poli", "Fabbri", "Martini", "Vitali", "Barbieri", "Santini", "Rizzo", "Gentile", "Colombo"};

        char[] sessi = {'M', 'F'};

        String nome = nomi[random.nextInt(nomi.length)];
        String cognome = cognomi[random.nextInt(cognomi.length)];
        int eta = random.nextInt(106);
        char sesso = sessi[random.nextInt(sessi.length)];

        return new Tifoso(nome, cognome, eta, sesso, squadraTifata);
    }

    public static void stampaInfoTifosi(List<Tifoso> tifosi) {
        int numeroTifosi = tifosi.size();
        if (numeroTifosi == 0) {
            System.out.println("Nessun tifoso nella lista.");
            return;
        }

        int sommaEta = 0;
        for (Tifoso tifoso : tifosi) {
            sommaEta += tifoso.eta;
        }

        double etaMedia = (double) sommaEta / numeroTifosi;

        System.out.println("Numero di tifosi: " + numeroTifosi);
        System.out.println("Età media dei tifosi: " + etaMedia);
    }
}
