package cz.cvut.fel.omo.hamrazec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Factory {

    private static Factory instance;
    private Director director;
    private Inspector inspector;
    private EventOperator eventOperator;
    private OperatorOfProduction operatorOfProduction;
    private RepairPool pool = RepairPool.getInstance();
    private List<FactoryWorker> factoryWorkers = new ArrayList<FactoryWorker>();
    private FileManager fileManager;

    private Factory() throws IOException {
        fileManager = new FileManager();
    }

    public static Factory getInstance() throws IOException {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }


    public Director getDirector() {
        return director;
    }


    public void setDirector(Director director) {
        this.director = director;
    }


    public Inspector getInspector() {
        return inspector;
    }


    public void setInspector(Inspector inspector) {
        this.inspector = inspector;
    }


    public EventOperator getEventOperator() {
        return eventOperator;
    }


    public void setEventOperator(EventOperator eventOperator) {
        this.eventOperator = eventOperator;
    }


    public OperatorOfProduction getOperatorOfProduction() {
        return operatorOfProduction;
    }


    public void setOperatorOfProduction(OperatorOfProduction operatorOfProduction) {
        this.operatorOfProduction = operatorOfProduction;
    }


    public RepairPool getPool() {
        return pool;
    }


    public void setPool(RepairPool pool) {
        this.pool = pool;
    }


    public List<FactoryWorker> getFactoryWorkers() {
        return factoryWorkers;
    }


    public void setFactoryWorkers(List<FactoryWorker> factoryWorkers) {
        this.factoryWorkers = factoryWorkers;
    }

    public void addToFactoryWorkers(FactoryWorker factoryWorker) {
        factoryWorkers.add(factoryWorker);
    }
}
