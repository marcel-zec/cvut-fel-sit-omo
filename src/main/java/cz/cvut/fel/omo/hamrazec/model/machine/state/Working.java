package cz.cvut.fel.omo.hamrazec.model.machine.state;

import cz.cvut.fel.omo.hamrazec.model.events.Alert;
import cz.cvut.fel.omo.hamrazec.model.machine.Machine;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionLine;

import java.util.Random;

public class Working extends State {

    public Working(Machine context) {
        super(context);
    }

    @Override
    public boolean canWork() {
        if (new Random().nextInt(50) == 3) {
            context.setState(new Broken(context));
            context.getEventList().receive(new Alert(context.getProductionLine().getPriority(), context));
            return false;
        } else {
            return true;
        }
    }
}
