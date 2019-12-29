package cz.cvut.fel.omo.hamrazec.model.person;

import cz.cvut.fel.omo.hamrazec.controller.ProductionOperator;
import cz.cvut.fel.omo.hamrazec.model.VisitorDirector;
import cz.cvut.fel.omo.hamrazec.model.machine.ControllingRobot;
import cz.cvut.fel.omo.hamrazec.model.machine.LineMachine;
import cz.cvut.fel.omo.hamrazec.model.machine.LineRobot;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionLine;
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
    public void visit(ProductionLine line) {

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
    public void visit(ProductionOperator productionOperator) {
        System.out.println("Operator of production has: " + productionOperator.getWorkersInUse().size()+ " workers in production, "+
                productionOperator.getAvailableWorkers().size()+ " avalaible workers.");
    }


    @Override
    public void accept(VisitorDirector visitor) {
    }


    /**
     * Starting control factory by director.
     * @param directorIterator
     */
    public void startIterate(DirectorIterator directorIterator){

        while (directorIterator.hasNext()){
            directorIterator.next().accept(this);
        }
    }
}

