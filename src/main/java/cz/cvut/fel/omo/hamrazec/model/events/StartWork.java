package cz.cvut.fel.omo.hamrazec.model.events;

import cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import cz.cvut.fel.omo.hamrazec.model.production.ProductLine;

public class StartWork extends Event {
    private ProductLine line;


    public StartWork(FactoryWorker sender, ProductLine line) {
        this.sender = sender;
        this.line = line;
        this.tact = sender.getTact();

    }


    public ProductLine getLine() {

        return line;
    }
}
