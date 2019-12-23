package main.java.cz.cvut.fel.omo.hamrazec.controller;

import main.java.cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import main.java.cz.cvut.fel.omo.hamrazec.model.LineWorker;
import main.java.cz.cvut.fel.omo.hamrazec.model.person.Director;
import main.java.cz.cvut.fel.omo.hamrazec.model.person.Inspector;
import main.java.cz.cvut.fel.omo.hamrazec.services.EventOperator;
import main.java.cz.cvut.fel.omo.hamrazec.services.FileManager;
import main.java.cz.cvut.fel.omo.hamrazec.services.RepairPool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Factory {

    private static Factory instance;
    private Director director;
    private Inspector inspector;
    private ProductionOperator productionOperator;
    private RepairPool pool;
    private List<FactoryWorker> factoryWorkers;
    private FileManager fileManager;

    private Factory() throws IOException {
        factoryWorkers = new ArrayList<FactoryWorker>();
        pool = RepairPool.getInstance();
        fileManager = new FileManager();
        productionOperator = ProductionOperator.getInstance();
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

    public void putWorkersToProduction(List<LineWorker> workers){
        productionOperator.addAvailableWorkers(workers);
    }

    public void putWorkersToProduction(LineWorker worker){
        productionOperator.addAvailableWorkers(worker);
    }
}
