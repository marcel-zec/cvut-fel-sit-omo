package main.java.cz.cvut.fel.omo.hamrazec.controller;

import main.java.cz.cvut.fel.omo.hamrazec.exceptions.CannotBuildLineException;
import main.java.cz.cvut.fel.omo.hamrazec.model.LineWorker;
import main.java.cz.cvut.fel.omo.hamrazec.model.machine.WorkMachine;
import main.java.cz.cvut.fel.omo.hamrazec.model.machine.WorkRobot;
import main.java.cz.cvut.fel.omo.hamrazec.model.person.Person;
import main.java.cz.cvut.fel.omo.hamrazec.model.production.ProductLine;
import main.java.cz.cvut.fel.omo.hamrazec.model.production.ProductionPlan;
import main.java.cz.cvut.fel.omo.hamrazec.model.production.ProductionSeries;
import main.java.cz.cvut.fel.omo.hamrazec.services.SeriesFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ProductionOperator {

    private static ProductionOperator instance;
    private List<LineWorker> availableWorkers;
    private List<LineWorker> workerInUse;
    private ProductionPlan plan;
    private SeriesFactory factory;
    private List<ProductLine> activeLines;
    private static final Logger LOG = Logger.getLogger(ProductionOperator.class.getName());

    private ProductionOperator() {
        this.availableWorkers = new ArrayList<>();
        this.workerInUse = new ArrayList<>();
        this.plan = new ProductionPlan();
        this.activeLines = new ArrayList<>();
        this.factory = new SeriesFactory();
    }


    public static ProductionOperator getInstance() {

        if (instance == null) {
            instance = new ProductionOperator();
        }
        return instance;
    }


    public List<LineWorker> getAvailableWorkers() {
        return availableWorkers;
    }

    public List<LineWorker> getAvailablePeople(){
        return availableWorkers.stream().filter(worker -> worker.getClass() == Person.class).collect(Collectors.toList());
    }

    public List<LineWorker> getAvailableMachines(){
        return availableWorkers.stream().filter(worker -> worker.getClass() == WorkMachine.class).collect(Collectors.toList());
    }

    public List<LineWorker> getAvailableRobots(){
        return availableWorkers.stream().filter(worker -> worker.getClass() == WorkRobot.class).collect(Collectors.toList());
    }

    public void setAvailableWorkers(List<LineWorker> availableWorkers) {
        this.availableWorkers = availableWorkers;
    }


    public void addAvailableWorkers(List<LineWorker> workers) {
        this.availableWorkers.addAll(workers);
    }


    public void addAvailableWorkers(LineWorker worker) {
        this.availableWorkers.add(worker);
    }


    public void removeWorkers(LineWorker worker) {
        this.availableWorkers.remove(worker);
    }

    public void removeWorkers(List<LineWorker> workers) {
        availableWorkers.removeAll(workers);
    }

    public List<LineWorker> getWorkersInUse() {
        return workerInUse;
    }

    public void setWorkersToUse(List<LineWorker> workers){
        availableWorkers.removeAll(workers);
        workerInUse.addAll(workers);
    }

    public void setWorkersToAvailable(List<LineWorker> workers){
        workerInUse.removeAll(workers);
        availableWorkers.addAll(workers);
    }


    public ProductionPlan getPlan() {
        return plan;
    }


    public void setPlan(ProductionPlan plan) {
        this.plan = plan;
    }


    public void addSeriesToPlan(int amount, SeriesName name, int priority) {
        switch (name) {
            case SeriesA:
                plan.addSeries(factory.getSeriesA(amount, priority));
                break;
            case SeriesB:
                plan.addSeries(factory.getSeriesB(amount, priority));
                break;
            case SeriesC:
                plan.addSeries(factory.getSeriesC(amount, priority));
                break;
        }
    }


    public void activateLines() {
        for (ProductionSeries series : plan.getPlan()) {
            try {
                activeLines.add(series.build());
                LOG.info("Line was build for production series.");
            } catch (CannotBuildLineException e) {
                LOG.info("Line for production series cannot be build.");
            }
        }
    }

    public void updateProduction(){
        for (ProductLine line: activeLines){
            line.update();
        }
    }
}
