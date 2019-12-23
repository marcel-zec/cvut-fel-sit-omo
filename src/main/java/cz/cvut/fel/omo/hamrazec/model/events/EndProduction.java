package main.java.cz.cvut.fel.omo.hamrazec.model.events;

import main.java.cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import main.java.cz.cvut.fel.omo.hamrazec.model.machine.ControllingRobot;
import main.java.cz.cvut.fel.omo.hamrazec.model.production.ProductLine;
import main.java.cz.cvut.fel.omo.hamrazec.model.production.ProductionSeries;

public class EndProduction extends Event {
    private ProductLine line;
    private ProductionSeries series;


    public ProductLine getLine() {

        return line;
    }


    public EndProduction(FactoryWorker sender, ProductLine line, ProductionSeries series) {

        this.sender = sender;
        this.line = line;
        this.series = series;
        this.tact = sender.getTact();
    }
}