package cz.cvut.fel.omo.hamrazec.controller;

import cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.Visitable;
import cz.cvut.fel.omo.hamrazec.model.person.Director;
import cz.cvut.fel.omo.hamrazec.model.person.Inspector;
import cz.cvut.fel.omo.hamrazec.model.person.Repairman;
import cz.cvut.fel.omo.hamrazec.services.EventOperator;
import cz.cvut.fel.omo.hamrazec.services.FactoryTimer;
import cz.cvut.fel.omo.hamrazec.services.FileManager;
import cz.cvut.fel.omo.hamrazec.services.RepairPool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Factory {

    private static Factory instance;
    private Director director;
    private List<Visitable> factoryWorkers;
    private Inspector inspector;
    private ProductionOperator productionOperator;
    private RepairPool pool;
    private List<LineWorker> lineWorkers;
    private FileManager fileManager;
    private FactoryTimer timer;

    private Factory() throws IOException {
        lineWorkers = new ArrayList<>();
        pool = RepairPool.getInstance();
//        fileManager = new FileManager();
        productionOperator = ProductionOperator.getInstance();
        timer = FactoryTimer.getInstance();
        timer.setFactory(this);
        timer.addFactoryWorkers(productionOperator);
        EventOperator.getInstance().setProductionOperator(productionOperator);
        //TODO - nacitavanie opravarov
        //TODO - nacitavanie strojov
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


    public ProductionOperator getProductionOperator() {
        return productionOperator;
    }


    public void setProductionOperator(ProductionOperator productionOperator) {
        this.productionOperator = productionOperator;
        timer.addFactoryWorkers(productionOperator);
    }


    public RepairPool getPool() {
        return pool;
    }


    public void setPool(RepairPool pool) {
        this.pool = pool;
    }

    public List<LineWorker> getLineWorkers() {
        return lineWorkers;
    }

    public void setLineWorkers(List<LineWorker> lineWorkers) {
        this.lineWorkers = lineWorkers;
    }

    public void addToLineWorkers(LineWorker lineWorker) {
        lineWorkers.add(lineWorker);
    }

    public void putWorkersToProduction(List<LineWorker> workers){
        productionOperator.addAvailableWorkers(workers);
        List<FactoryWorker> factoryWorkers = new ArrayList<>(workers);
        timer.addFactoryWorkers(factoryWorkers);
    }

    public void putWorkersToProduction(LineWorker worker){
        productionOperator.addAvailableWorkers(worker);
        timer.addFactoryWorkers(worker);
    }

    public void putFactoryWorkerToFactory(FactoryWorker worker){
        factoryWorkers.add(worker);
    }

    public void putFactoryWorkersToFactory(List<FactoryWorker> workers){
        factoryWorkers = new ArrayList<>(workers);
    }


    public List<Visitable> getFactoryWorkers() {
        return factoryWorkers;
    }


    public void update(){
        productionOperator.updateProduction();
    }
}
