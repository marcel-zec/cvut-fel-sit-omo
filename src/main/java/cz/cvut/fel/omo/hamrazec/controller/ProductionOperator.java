package cz.cvut.fel.omo.hamrazec.controller;

import cz.cvut.fel.omo.hamrazec.exceptions.CannotBuildLineException;
import cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.Visitor;
import cz.cvut.fel.omo.hamrazec.model.events.StartProduction;
import cz.cvut.fel.omo.hamrazec.model.production.ProductLine;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionPlan;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionSeries;
import cz.cvut.fel.omo.hamrazec.services.EventList;
import cz.cvut.fel.omo.hamrazec.services.SeriesFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ProductionOperator implements FactoryWorker {

    private static ProductionOperator instance;
    private List<LineWorker> availableWorkers;
    private List<LineWorker> workerInUse;
    private ProductionPlan plan;
    private SeriesFactory seriesFactory;
    private List<ProductLine> activeLines;
    private EventList eventList;
    private static final Logger LOG = Logger.getLogger(ProductionOperator.class.getName());
    private int tact;

    private ProductionOperator() {
        this.availableWorkers = new ArrayList<>();
        this.workerInUse = new ArrayList<>();
        this.plan = new ProductionPlan();
        this.activeLines = new ArrayList<>();
        this.seriesFactory = new SeriesFactory();
        this.eventList = EventList.getInstance();
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


    public void removeWorkers(LineWorker worker) {
        this.availableWorkers.remove(worker);
    }

    public void removeWorkers(List<LineWorker> workers) {
        availableWorkers.removeAll(workers);
    }

    public List<LineWorker> getWorkersInUse() {
        return workerInUse;
    }


    public void setWorkersToUse(List<LineWorker> workers) {
        availableWorkers.removeAll(workers);
        workerInUse.addAll(workers);
    }

    public void setWorkersToUse(LineWorker worker) {
        availableWorkers.remove(worker);
        workerInUse.add(worker);
    }

    public void setWorkersToAvailable(List<LineWorker> workers) {
        workerInUse.removeAll(workers);
        availableWorkers.addAll(workers);
    }

    public void setWorkersToAvailable(LineWorker worker) {
        workerInUse.remove(worker);
        availableWorkers.add(worker);
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
                plan.addSeries(seriesFactory.getSeriesA(amount, priority));
                break;
            case SeriesB:
                plan.addSeries(seriesFactory.getSeriesB(amount, priority));
                break;
            case SeriesC:
                plan.addSeries(seriesFactory.getSeriesC(amount, priority));
                break;
        }
    }


    public void activateLines() {
        for (ProductionSeries series : plan.getPlan()) {
            try {
                ProductLine line = series.build();
                activeLines.add(line);
                LOG.info("Line was build for production series.");
                eventList.receive(new StartProduction(this, line, series));
            } catch (CannotBuildLineException e) {
                LOG.info("Line for production series cannot be build.");
            }
        }
    }

    public void updateProduction() {
        for (ProductLine line : activeLines) {
            line.update();
        }
    }

    @Override
    public int getTact() {
        return tact;
    }

    @Override
    public void updateTact(int tact) {
        this.tact = tact;
    }


    @Override
    public void accept(Visitor visitor) {

    }
}
