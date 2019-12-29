package cz.cvut.fel.omo.hamrazec.model.production;

import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.Visitable;
import cz.cvut.fel.omo.hamrazec.model.VisitorDirector;

import java.util.ArrayList;
import java.util.List;

public class ProductLine implements Visitable {
    private ProductionSeries series;
    List<LineWorker> lineWorkers;
    LineWorker firstWorker;

    public ProductLine() {
        lineWorkers = new ArrayList<>();
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

    public ProductLine(LineWorker firstWorker) {
        this.firstWorker = firstWorker;
    }

    public LineWorker getFirstWorker() {
        return firstWorker;
    }

    public void setFirstWorker(LineWorker firstWorker) {
        this.firstWorker = firstWorker;
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
        firstWorker.forWork(series.getProductFactory().getProduct());
        firstWorker.update();
    }


    @Override
    public void accept(VisitorDirector visitor) {
        visitor.visit(this);
    }


    @Override
    public String toString() {

        return  "ProductLine{" +
                "series=" + series.toString() +
                ", lineWorkers=" +  lineWorkers +
                " and firstWorker=" + firstWorker +
                '}';
    }
}
