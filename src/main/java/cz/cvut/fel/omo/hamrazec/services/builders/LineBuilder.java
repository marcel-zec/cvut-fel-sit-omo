package main.java.cz.cvut.fel.omo.hamrazec.services.builders;

import main.java.cz.cvut.fel.omo.hamrazec.controller.ProductionOperator;
import main.java.cz.cvut.fel.omo.hamrazec.exceptions.NotEnoughWorkers;
import main.java.cz.cvut.fel.omo.hamrazec.model.LineWorker;
import main.java.cz.cvut.fel.omo.hamrazec.model.production.ProductLine;

import java.util.ArrayList;
import java.util.List;

abstract public class LineBuilder implements Builder {
    protected ProductionOperator operator;
    protected ProductLine line;
    protected List<LineWorker> workers;
    protected int machines;
    protected int people;
    protected int robots;


    public LineBuilder() {
        operator = ProductionOperator.getInstance();
        workers = new ArrayList<>();
    }


    public List<LineWorker> getWorkers() {
        return workers;
    }


    public void setWorkers(List<LineWorker> workers) {
        this.workers = workers;
    }


    public void addWorker(LineWorker worker) {
        workers.add(worker);
    }

    @Override
    public void createLine() {
        line = new ProductLine();
    }

    @Override
    public void setMachines() throws NotEnoughWorkers {
        List<LineWorker> available = operator.getAvailableMachines();
        if (available.size() > machines){
            for (int i = 0; i < machines; i++) {
                addWorker(available.remove(i));
            }
        } else throw new NotEnoughWorkers();
    }


    @Override
    public void setPeople() throws NotEnoughWorkers {
        List<LineWorker> available = operator.getAvailablePeople();
        if (available.size() > people){
            for (int i = 0; i < people; i++) {
                addWorker(available.remove(i));
            }
        } else throw new NotEnoughWorkers();
    }


    @Override
    public void setRobots() throws NotEnoughWorkers {
        List<LineWorker> available = operator.getAvailablePeople();
        if (available.size() > people){
            for (int i = 0; i < people; i++) {
                addWorker(available.remove(i));
            }
        } else throw new NotEnoughWorkers();
    }

    @Override
    public void setControl() throws NotEnoughWorkers {
        List<LineWorker> available = operator.getAvailableControlRobots();
        if (available.size() > 0){
                addWorker(available.remove(0));
        } else throw new NotEnoughWorkers();
    }

    @Override
    public ProductLine getResult(){
        line.setLineWorkers(workers);
        operator.setWorkersToUse(workers);
        return line;
    }

    @Override
    public void cancelBuilding(){
        operator.setWorkersToAvailable(workers);
    }
}
