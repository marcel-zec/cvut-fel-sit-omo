package cz.cvut.fel.omo.hamrazec.services;

import cz.cvut.fel.omo.hamrazec.model.production.*;
import cz.cvut.fel.omo.hamrazec.services.builders.*;

public class SeriesFactory {

    public ProductionSeries getSeriesA(int amount, int priority){
        return new ProductionSeries(amount, priority, new BuilderProductA(), new FactoryProductA());
    }

    public ProductionSeries getSeriesB(int amount, int priority){
        return new ProductionSeries(amount, priority, new BuilderProductB(), new FactoryProductB());
    }

    public ProductionSeries getSeriesC(int amount, int priority){
        return new ProductionSeries(amount, priority, new BuilderProductC(), new FactoryProductC());
    }
}
