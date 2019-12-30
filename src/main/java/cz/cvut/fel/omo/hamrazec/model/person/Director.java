package cz.cvut.fel.omo.hamrazec.model.person;

import cz.cvut.fel.omo.hamrazec.controller.ProductionOperator;
import cz.cvut.fel.omo.hamrazec.model.VisitorDirector;
import cz.cvut.fel.omo.hamrazec.model.machine.ControllingRobot;
import cz.cvut.fel.omo.hamrazec.model.machine.LineMachine;
import cz.cvut.fel.omo.hamrazec.model.machine.LineRobot;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionLine;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionPlan;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionSeries;


import java.util.logging.Logger;

public class Director extends Person implements VisitorDirector {
    private static final Logger LOG = Logger.getLogger(Director.class.getName());

    public Director(String firstName, String lastName, int wage) {
        super(firstName, lastName, wage);
    }

    @Override
    public void visit(LineMachine machine) {
        LOG.info(machine.toString());
    }

    @Override
    public void visit(ControllingRobot controllingRobot) {
        LOG.info("Controlling robot state is: " + controllingRobot.getState().getClass().getSimpleName());
    }

    @Override
    public void visit(Worker worker) {
        LOG.info(worker.toString());
    }

    @Override
    public void visit(Repairman repairman) {
        LOG.info(repairman.toString());
    }

    @Override
    public void visit(LineRobot lineRobot) {
        LOG.info("Linerobot state is: " + lineRobot.getState().getClass().getSimpleName());
    }

    @Override
    public void visit(ProductionLine line) {
        LOG.info("First worker of line " + line.getSerialNumber() + " is: "+ line.getFirstWorker().getClass().getSimpleName());
    }

    @Override
    public void visit(ProductionSeries series) {
        LOG.info("Series: " + series.toString());
    }

    @Override
    public void visit(ProductionPlan plan) {
        LOG.info(plan.toString());
    }


    @Override
    public void visit(ProductionOperator productionOperator) {
        LOG.info("Operator of production has: " + productionOperator.getWorkersInUse().size()+ " workers in production, "+
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

