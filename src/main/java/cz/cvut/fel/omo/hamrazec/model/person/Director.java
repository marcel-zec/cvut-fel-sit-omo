package cz.cvut.fel.omo.hamrazec.model.person;

import cz.cvut.fel.omo.hamrazec.model.VisitorDirector;
import cz.cvut.fel.omo.hamrazec.model.machine.ControllingRobot;
import cz.cvut.fel.omo.hamrazec.model.machine.LineMachine;
import cz.cvut.fel.omo.hamrazec.model.machine.LineRobot;
import cz.cvut.fel.omo.hamrazec.model.production.ProductLine;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionPlan;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionSeries;

public class Director extends Person implements VisitorDirector {
    public Director(String firstName, String lastName, int wage) {
        super(firstName, lastName, wage);
    }

    @Override
    public void visit(LineMachine machine) {

        System.out.println(machine.toString());
    }

    @Override
    public void visit(ControllingRobot controllingRobot) {
        System.out.println("Controlling robot state is: " + controllingRobot.getState().getClass().getSimpleName());
    }

    @Override
    public void visit(Worker worker) {
        System.out.println(worker.toString());
    }

    @Override
    public void visit(Repairman repairman) {

        System.out.println(repairman.toString());
    }

    @Override
    public void visit(LineRobot lineRobot) {

        System.out.println("Linerobot state is: " + lineRobot.getState().getClass().getSimpleName());
    }

    @Override
    public void visit(ProductLine line) {

        System.out.println(line.toString());
    }

    @Override
    public void visit(ProductionSeries series) {
        System.out.println("Series: " + series.toString());
    }

    @Override
    public void visit(ProductionPlan plan) {
        System.out.println(plan.toString());
    }

    @Override
    public void accept(VisitorDirector visitor) {
    }
}

