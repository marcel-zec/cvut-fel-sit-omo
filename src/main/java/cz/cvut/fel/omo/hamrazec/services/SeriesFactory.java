package main.java.cz.cvut.fel.omo.hamrazec.services;

import main.java.cz.cvut.fel.omo.hamrazec.model.production.*;
import main.java.cz.cvut.fel.omo.hamrazec.services.builders.*;

public class SeriesFactory {

    public ProductionSeries getSeriesA(int amount){
        return new ProductionSeries(amount, new BuilderProductA(), new FactoryProductA());
    }

    public ProductionSeries getSeriesB(int amount){
        return new ProductionSeries(amount, new BuilderProductB(), new FactoryProductB());

    }

    public ProductionSeries getSeriesC(int amount){
        return new ProductionSeries(amount, new BuilderProductC(), new FactoryProductC());
    }
}
