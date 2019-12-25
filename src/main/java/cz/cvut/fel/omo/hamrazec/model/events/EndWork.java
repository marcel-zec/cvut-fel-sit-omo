package cz.cvut.fel.omo.hamrazec.model.events;

import cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import cz.cvut.fel.omo.hamrazec.model.production.ProductLine;

public class EndWork extends Event{
    private ProductLine line;


    public EndWork(FactoryWorker sender, ProductLine line) {
        this.sender = sender;
        this.line = line;
        this.tact = sender.getTact();

    }


    public ProductLine getLine() {

        return line;
    }
}
