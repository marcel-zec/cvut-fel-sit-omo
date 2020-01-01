package cz.cvut.fel.omo.hamrazec.controller;

import cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.Visitable;
import cz.cvut.fel.omo.hamrazec.model.person.*;
import cz.cvut.fel.omo.hamrazec.services.EventOperator;
import cz.cvut.fel.omo.hamrazec.services.FactoryTimer;
import cz.cvut.fel.omo.hamrazec.services.FileManager;
import cz.cvut.fel.omo.hamrazec.services.RepairPool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Factory {

    private static Factory instance;
    private Director director;
    private Inspector inspector;
    private ProductionOperator productionOperator;
    private RepairPool pool;
    private List<LineWorker> lineWorkers;
    private FileManager fileManager;
    private FactoryTimer timer;

    private Factory() throws IOException {
        lineWorkers = new ArrayList<>();
        pool = RepairPool.getInstance();
        fileManager = new FileManager(this);
        productionOperator = ProductionOperator.getInstance();
        timer = FactoryTimer.getInstance();
        timer.setFactory(this);
        timer.addFactoryWorkers(productionOperator);
        EventOperator.getInstance().setProductionOperator(productionOperator);
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

    public void setRepairmen(List<Repairman> repairmen){
        pool.setRepairmen(repairmen);
        timer.addFactoryWorkers(new ArrayList<>(repairmen));
    }

    /**
     * Method add repairmen to repair pool and factory worker list in factory timer.
     * @param repairmen
     */
    public void addRepairmen(List<Repairman> repairmen){
        pool.putRepairman(repairmen);
        timer.addFactoryWorkers(new ArrayList<>(repairmen));
    }

    /**
     * Method add repairman to repair pool and factory worker list in factory timer.
     * @param repairman
     */
    public void addRepairmen(Repairman repairman){
        pool.putRepairman(repairman);
        timer.addFactoryWorkers(repairman);
    }

    /**
     * Method remove repairmen from repair pool and factory worker list in factory timer.
     * @param repairmen
     */
    public void removeRepairmen(List<Repairman> repairmen){
        pool.removeRepairmen(repairmen);
        timer.removeFactoryWorkers(new ArrayList<>(repairmen));
    }

    /**
     * Method remove repairman from repair pool and factory worker list in factory timer.
     * @param repairman
     */
    public void removeRepairmen(Repairman repairman){
        pool.removeRepairmen(repairman);
        timer.removeFactoryWorkers(repairman);
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



    /**
     * Method put workers to list of available workers for production.
     * Also put workers to list of factory workers in timer for updating their time.
     * @param workers
     */
    public void putWorkersToProduction(List<LineWorker> workers){
        productionOperator.addAvailableWorkers(workers);
        List<FactoryWorker> factoryWorkers = new ArrayList<>(workers);
        timer.addFactoryWorkers(factoryWorkers);
    }

    /**
     * Method put worker to list of available workers for production.
     * Also put worker to list of factory workers in timer for updating its time.
     * @param worker
     */
    public void putWorkersToProduction(LineWorker worker){
        productionOperator.addAvailableWorkers(worker);
        timer.addFactoryWorkers(worker);
    }

    /**
     * Method takes worker from list of available workers for production.
     * Also takes worker from list of factory workers in timer for updating its time.
     * @param worker
     */
    public void takeWorkersFromProduction(LineWorker worker){
        productionOperator.removeWorkers(worker);
        timer.removeFactoryWorkers(worker);
    }

    /**
     * Method takes workers from list of available workers for production.
     * Also takes workers from list of factory workers in timer for updating their time.
     * @param workers
     */
    public void takeWorkersFromProduction(List<LineWorker> workers){
        productionOperator.removeWorkers(workers);
        timer.removeFactoryWorkers(new ArrayList<>(workers));
    }


    /**
     * Method return all visitable workers from factory. That are line workers and repairmen.
     * They are use for visit from director.
     * @return list of visitable workers
     */
    public List<Visitable> getVisitableWorkers() {
        List<Visitable> visitable = new ArrayList<>(lineWorkers);
        visitable.addAll(pool.getAllRepairman());
        return visitable;
    }


    /**
     * Method called from factory timer for update status of factory.
     * Method call update method in production operator for update production.
     */
    public void update(){
        productionOperator.updateProduction();
    }

    /**
     * Method start inspection in factory.
     * @throws IOException
     */
    public void startInspection() throws IOException {
         inspector.startIterate(new InspectorIterator());
    }

    /**
     * Method start director visiting elements in factory.
     * @throws IOException
     */
    public void startDirectorVisit() throws IOException {
        director.startIterate(new DirectorIterator());
    }

}
