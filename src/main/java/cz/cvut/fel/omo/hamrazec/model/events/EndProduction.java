package main.java.cz.cvut.fel.omo.hamrazec.model.events;

import main.java.cz.cvut.fel.omo.hamrazec.model.machine.ControllingRobot;
import main.java.cz.cvut.fel.omo.hamrazec.model.production.ProductLine;

public class EndProduction extends Event {
    private ProductLine line;

    public ProductLine getLine() {

        return line;
    }

    public EndProduction(EventSender sender, ProductLine line) {
        this.sender = sender;
        this.line = line;
    }
}
