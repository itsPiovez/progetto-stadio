package GestionePrezzi;

public class PrezzoStrategyFactory {
    private static PrezzoStrategyFactory factory;

    private PrezzoStrategyFactory(){};

    public static PrezzoStrategyFactory getInstance(){
        if(factory == null)
            factory = new PrezzoStrategyFactory();
        return factory;
    }

    public PrezzoStrategyInterface getPrezzoStrategy(){
        return new PrezzoStrategy();
    }
}