package cz.cvut.fel.omo.hamrazec.model.events;

import cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionLine;

public class EndWork extends Event{
    private ProductionLine line;


    public EndWork(FactoryWorker sender, ProductionLine line) {
        this.sender = sender;
        this.line = line;
        this.tact = sender.getTact();

    }


    public ProductionLine getLine() {

        return line;
    }
}
