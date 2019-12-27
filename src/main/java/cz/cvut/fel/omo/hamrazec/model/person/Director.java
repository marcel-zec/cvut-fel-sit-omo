package cz.cvut.fel.omo.hamrazec.model.person;

import cz.cvut.fel.omo.hamrazec.model.Visitor;
import cz.cvut.fel.omo.hamrazec.model.machine.ControllingRobot;
import cz.cvut.fel.omo.hamrazec.model.machine.LineMachine;
import cz.cvut.fel.omo.hamrazec.model.machine.LineRobot;
import cz.cvut.fel.omo.hamrazec.model.production.ProductLine;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionPlan;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionSeries;

public class Director extends Person implements Visitor {
    public Director(String firstName, String lastName, int wage) {
        super(firstName, lastName, wage);
    }


    @Override
    public void accept(Visitor visitor) {
    }


    public void visit(LineMachine machine) {

        System.out.println("Machine state is: " + machine.getState().toString());
    }


    public void visit(ControllingRobot controllingRobot) {
        System.out.println("Controlling robot state is: " + controllingRobot.getState().toString());
    }

    public void visit(Worker worker) {
        System.out.println("Worker info is: " + worker.toString());
    }


    public void visit(Repairman repairman) {

        System.out.println("Repairman info is: " + repairman.toString());
    }


    public void visit(LineRobot lineRobot) {

        System.out.println("Linerobot state is: " + lineRobot.getState().toString());
    }


    public void visit(ProductLine line) {

        System.out.println(line.toString());
    }


    public void visit(ProductionSeries series) {
        System.out.println("Series: " + series.toString());
    }


    public void visit(ProductionPlan plan) {
        System.out.println(plan.toString());
    }


    @Override
    public void visit(Object type) {

    }
}

