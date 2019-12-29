package cz.cvut.fel.omo.hamrazec.model.events;

import cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import cz.cvut.fel.omo.hamrazec.model.production.ProductLine;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionSeries;

public class StartProduction extends Event{
    private ProductLine line;
    private ProductionSeries series;


    public StartProduction(FactoryWorker sender, ProductLine line, ProductionSeries series) {
        this.sender = sender;
        this.line = line;
        this.series = series;
        this.tact = sender.getTact();
    }


    public ProductLine getLine() {
        return line;
    }


    public ProductionSeries getSeries() {
        return series;
    }
}
