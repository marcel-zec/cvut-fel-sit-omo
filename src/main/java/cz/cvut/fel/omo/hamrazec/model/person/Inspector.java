package cz.cvut.fel.omo.hamrazec.model.person;

import cz.cvut.fel.omo.hamrazec.model.Visitor;
import cz.cvut.fel.omo.hamrazec.model.machine.ControllingRobot;
import cz.cvut.fel.omo.hamrazec.model.machine.LineMachine;
import cz.cvut.fel.omo.hamrazec.model.machine.LineRobot;
import cz.cvut.fel.omo.hamrazec.model.production.ProductLine;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionPlan;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionSeries;

public class Inspector extends Person implements Visitor {
    public Inspector(String firstName, String lastName, int wage) {
        super(firstName, lastName, wage);
    }


    @Override
    public void accept(Visitor visitor) {
    }

    @Override
    public void visit(Object type) {

    }
}
