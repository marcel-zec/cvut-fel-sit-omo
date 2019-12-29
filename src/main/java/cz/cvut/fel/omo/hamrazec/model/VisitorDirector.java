package cz.cvut.fel.omo.hamrazec.model;

import cz.cvut.fel.omo.hamrazec.controller.ProductionOperator;
import cz.cvut.fel.omo.hamrazec.model.machine.ControllingRobot;
import cz.cvut.fel.omo.hamrazec.model.machine.LineMachine;
import cz.cvut.fel.omo.hamrazec.model.machine.LineRobot;
import cz.cvut.fel.omo.hamrazec.model.person.Repairman;
import cz.cvut.fel.omo.hamrazec.model.person.Worker;
import cz.cvut.fel.omo.hamrazec.model.production.ProductLine;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionPlan;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionSeries;

public interface VisitorDirector{

    void visit(LineMachine machine);
    void visit(ControllingRobot controllingRobot);
    void visit(Worker worker);
    void visit(Repairman repairman);
    void visit(LineRobot lineRobot);
    void visit(ProductLine line);
    void visit(ProductionSeries series);
    void visit(ProductionPlan plan);
    void visit(ProductionOperator productionOperator);
}
