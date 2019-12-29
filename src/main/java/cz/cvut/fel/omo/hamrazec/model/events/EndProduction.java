package cz.cvut.fel.omo.hamrazec.model.events;

import cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionLine;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionSeries;

public class EndProduction extends Event {
    private ProductionLine line;
    private ProductionSeries series;


    public ProductionLine getLine() {

        return line;
    }


    public EndProduction(FactoryWorker sender, ProductionLine line, ProductionSeries series) {

        this.sender = sender;
        this.line = line;
        this.series = series;
        this.tact = sender.getTact();
    }
}
