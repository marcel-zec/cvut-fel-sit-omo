package main.java.cz.cvut.fel.omo.hamrazec.model.production;

import main.java.cz.cvut.fel.omo.hamrazec.model.LineWorker;

import java.util.ArrayList;
import java.util.List;

public class ProductLine {
    private ProductionSeries series;
    List<LineWorker> lineWorkers;
    LineWorker firstWorker;

    public ProductLine() {
        lineWorkers = new ArrayList<>();
    }

    public int getPriority(){
       return series.getPriority();
    }

    public ProductLine(LineWorker firstWorker) {
        this.firstWorker = firstWorker;
    }

    public void setLineWorkers(List<LineWorker> lineWorkers) {
        this.lineWorkers = lineWorkers;
    }

    public void update(){
        //TODO - ak funguje
        firstWorker.work(series.getProductFactory().getProduct());
    }


}
