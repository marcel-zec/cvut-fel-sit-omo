package cz.cvut.fel.omo.hamrazec.model;

import cz.cvut.fel.omo.hamrazec.model.machine.ControllingRobot;
import cz.cvut.fel.omo.hamrazec.model.machine.LineMachine;
import cz.cvut.fel.omo.hamrazec.model.machine.LineRobot;

public interface VisitorInspector {

    void visit(LineMachine machine);
    void visit(LineRobot lineRobot);
    void visit(ControllingRobot controllingRobot);
}
