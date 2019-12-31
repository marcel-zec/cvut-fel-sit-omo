package cz.cvut.fel.omo.hamrazec.model.production;

import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.Visitable;
import cz.cvut.fel.omo.hamrazec.model.VisitorDirector;

import java.util.ArrayList;
import java.util.List;

public class ProductionLine implements Visitable {
    private ProductionSeries series;
    private List<LineWorker> lineWorkers;
    private LineWorker firstWorker;
    private final int serialNumber;
    private static int counter = 1;

    public ProductionLine() {
        lineWorkers = new ArrayList<>();
        this.serialNumber = counter++;

    }

    public ProductionSeries getSeries() {
        return series;
    }

    public void setSeries(ProductionSeries series) {
        this.series = series;
    }

    public int getPriority(){
       return series.getPriority();
    }

    public LineWorker getFirstWorker() {
        return firstWorker;
    }

    public LineWorker setFirstWorker(LineWorker firstWorker) {
        this.firstWorker = firstWorker;
        return firstWorker;
    }

    public void setLineWorkers(List<LineWorker> lineWorkers) {
        this.lineWorkers = lineWorkers;
    }

    public void addLineWorkers(List<LineWorker> lineWorkers){
        this.lineWorkers.addAll(lineWorkers);
    }

    public void addLineWorkers(LineWorker lineWorker){
        lineWorkers.add(lineWorker);
    }

    public void update(){
        firstWorker.update();
    }

    public int getSerialNumber() {
        return serialNumber;
    }


    @Override
    public void accept(VisitorDirector visitor) {
        visitor.visit(this);
    }


    @Override
    public String toString() {

        return  "ProductLine{ " +
                "series=" + series.toString() +
                ", lineWorkers=" +  lineWorkers +
                " and firstWorker=" + firstWorker.getClass().getSimpleName() +
                '}';
    }
}
