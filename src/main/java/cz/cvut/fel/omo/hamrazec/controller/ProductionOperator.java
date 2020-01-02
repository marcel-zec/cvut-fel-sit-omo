package cz.cvut.fel.omo.hamrazec.controller;

import cz.cvut.fel.omo.hamrazec.exceptions.CannotBuildLineException;
import cz.cvut.fel.omo.hamrazec.exceptions.SeriesNotExistException;
import cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.Visitable;
import cz.cvut.fel.omo.hamrazec.model.VisitorDirector;
import cz.cvut.fel.omo.hamrazec.model.events.StartProduction;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionLine;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionPlan;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionSeries;
import cz.cvut.fel.omo.hamrazec.services.EventList;
import cz.cvut.fel.omo.hamrazec.services.SeriesFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ProductionOperator implements FactoryWorker, Visitable {

    private static ProductionOperator instance;
    private List<LineWorker> availableWorkers;
    private List<LineWorker> workerInUse;
    private ProductionPlan plan;
    private SeriesFactory seriesFactory;
    private List<ProductionLine> activeLines;
    private List<ProductionLine> linesForEnded;
    private EventList eventList;
    private static final Logger LOG = Logger.getLogger(ProductionOperator.class.getName());
    private int tact;

    private ProductionOperator() {
        this.availableWorkers = new ArrayList<>();
        this.workerInUse = new ArrayList<>();
        this.plan = new ProductionPlan();
        this.activeLines = new ArrayList<>();
        this.linesForEnded = new ArrayList<>();
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

    /**
     * Move workers from list of available workers to list of workers in use.
     * Workers will not be available for another production line.
     *
     * @param workers
     */
    public void setWorkersToUse(List<LineWorker> workers) {
        availableWorkers.removeAll(workers);
        workerInUse.addAll(workers);
        LOG.info(workers.size() + " workers set to work.");
    }

    /**
     * Move worker from list of available workers to list of workers in use.
     * Worker will not be available for another production line.
     *
     * @param worker
     */
    public void setWorkersToUse(LineWorker worker) {
        availableWorkers.remove(worker);
        workerInUse.add(worker);
        LOG.info("Worker set to work.");
    }

    /**
     * Move workers from list of workers in use to list of available workers.
     * Workers will be available for work in new production line.
     *
     * @param workers
     */
    public void setWorkersToAvailable(List<LineWorker> workers) {
        workerInUse.removeAll(workers);
        availableWorkers.addAll(workers);
        LOG.info(workers.size() + " workers are available for work.");
    }

    /**
     * Move worker from list of workers in use to list of available workers.
     * Worker will be available for work in new production line.
     *
     * @param worker
     */
    public void setWorkersToAvailable(LineWorker worker) {
        workerInUse.remove(worker);
        availableWorkers.add(worker);
        LOG.info("Worker is available for work.");
    }

    public ProductionPlan getPlan() {
        return plan;
    }


    public void setPlan(ProductionPlan plan) {
        this.plan = plan;
    }

    /**
     * Add series to production plan by its name.
     *
     * @param amount   of products
     * @param name     of series
     * @param priority of series
     */
    public void addSeriesToPlan(int amount, SeriesName name, int priority) throws SeriesNotExistException {
        switch (name) {
            case SeriesA:
                plan.addSeries(seriesFactory.getSeriesA(amount, priority));
                LOG.info("Series of product A of amount " + amount + " with priority " + priority +
                        " was added to production plan.");
                break;
            case SeriesB:
                plan.addSeries(seriesFactory.getSeriesB(amount, priority));
                LOG.info("Series of product B of amount " + amount + " with priority " + priority +
                        " was added to production plan.");
                break;
            case SeriesC:
                plan.addSeries(seriesFactory.getSeriesC(amount, priority));
                LOG.info("Series of product C of amount " + amount + " with priority " + priority +
                        " was added to production plan.");
                break;
            default:
                throw new SeriesNotExistException();
        }
    }

    /**
     * Put production line which end production to list of ended lines. List of ended line will be executed
     * in next tact. Also move ended series to list of ended series in production line.
     *
     * @param line
     */
    public void endProduction(ProductionLine line) {
        if (activeLines.contains(line)) {
            linesForEnded.add(line);
            plan.addEndedSeries(line.getSeries());
        }
    }

    /**
     * Method iterate through production plan and try to build new production line.
     * Built line is added to list of active lines, that is used for updates from timer.
     * Series which will produce built lines are removed from production plan.
     */
    public void activateLines() {
        for (ProductionSeries series : plan.getPlan()) {
            try {
                ProductionLine line = series.build();
                activeLines.add(line);
                LOG.info("Line was build for production series.");
                eventList.receive(new StartProduction(this, line, series));
            } catch (CannotBuildLineException e) {
                LOG.info("Line for production series cannot be build.");
            }
        }
        for (ProductionLine line : activeLines) {
            plan.removeSeries(line.getSeries());
        }
    }

    /**
     * Method is used for production simulation during time. At first remove ended lines from list of active lines.
     * Than call update method on all active lines.
     */
    public void updateProduction() {
        if (linesForEnded.size() > 0) {
            for (ProductionLine line : linesForEnded) {
                activeLines.remove(line);
            }
            linesForEnded = new ArrayList<>();
        }
        for (ProductionLine line : activeLines) {
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
    public void accept(VisitorDirector visitor) {
        visitor.visit(this);
    }


    public List<ProductionLine> getActiveLines() {
        return activeLines;
    }
}
