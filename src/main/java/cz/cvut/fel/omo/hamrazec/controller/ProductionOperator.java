package main.java.cz.cvut.fel.omo.hamrazec.controller;

import main.java.cz.cvut.fel.omo.hamrazec.exceptions.CannotBuildLineException;
import main.java.cz.cvut.fel.omo.hamrazec.model.LineWorker;
import main.java.cz.cvut.fel.omo.hamrazec.model.production.ProductLine;
import main.java.cz.cvut.fel.omo.hamrazec.model.production.ProductionPlan;
import main.java.cz.cvut.fel.omo.hamrazec.model.production.ProductionSeries;
import main.java.cz.cvut.fel.omo.hamrazec.services.SeriesFactory;

import java.util.ArrayList;
import java.util.List;

public class ProductionOperator {

    private static ProductionOperator instance;
    private List<LineWorker> availableWorkers;
    private List<LineWorker> workerInUse;
    private ProductionPlan plan;
    private SeriesFactory factory;
    private List<ProductLine> activeLines;


    private ProductionOperator() {

        this.availableWorkers = new ArrayList<>();
        this.workerInUse = new ArrayList<>();
        this.plan = new ProductionPlan();
        this.activeLines = new ArrayList<>();

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


    public void setAvailableWorkers(List<LineWorker> availableWorkers) {

        this.availableWorkers = availableWorkers;
    }


    public void addAvailableWorkers(List<LineWorker> workers) {

        this.availableWorkers.addAll(workers);
    }


    public void addAvailableWorkers(LineWorker worker) {

        this.availableWorkers.add(worker);
    }


    public void removeWorker(LineWorker worker) {

        this.availableWorkers.remove(worker);
    }


    public List<LineWorker> getWorkerInUse() {

        return workerInUse;
    }


    public void setWorkerInUse(List<LineWorker> workerInUse) {

        this.workerInUse = workerInUse;
    }


    public ProductionPlan getPlan() {

        return plan;
    }


    public void setPlan(ProductionPlan plan) {

        this.plan = plan;
    }


    public void addSeriesToPlan(int amount, SeriesName name) {

        switch (name) {
            case SeriesA:
                plan.addSeries(factory.getSeriesA(amount));
                break;
            case SeriesB:
                plan.addSeries(factory.getSeriesB(amount));
                break;
            case SeriesC:
                plan.addSeries(factory.getSeriesC(amount));
                break;
        }
    }


    public void startProduction() {

        for (ProductionSeries series : plan.getPlan()) {
            try {
                activeLines.add(series.build());
            } catch (CannotBuildLineException e){

            }

        }

        for (ProductLine line: activeLines){
            line.startProduction();
        }
    }


}
