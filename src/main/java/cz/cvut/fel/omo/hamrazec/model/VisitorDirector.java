package cz.cvut.fel.omo.hamrazec.model;

import cz.cvut.fel.omo.hamrazec.model.machine.ControllingRobot;
import cz.cvut.fel.omo.hamrazec.model.machine.LineMachine;
import cz.cvut.fel.omo.hamrazec.model.machine.LineRobot;
import cz.cvut.fel.omo.hamrazec.model.person.Repairman;
import cz.cvut.fel.omo.hamrazec.model.person.Worker;
import cz.cvut.fel.omo.hamrazec.model.production.ProductLine;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionPlan;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionSeries;

public interface VisitorDirector{

    public void visit(LineMachine machine);
    public void visit(ControllingRobot controllingRobot);
    public void visit(Worker worker);
    public void visit(Repairman repairman);
    public void visit(LineRobot lineRobot);
    public void visit(ProductLine line);
    public void visit(ProductionSeries series);
    public void visit(ProductionPlan plan);

}
