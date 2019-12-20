package main.java.cz.cvut.fel.omo.hamrazec.controller;

import main.java.cz.cvut.fel.omo.hamrazec.model.LineWorker;
import main.java.cz.cvut.fel.omo.hamrazec.model.events.EventSender;

import java.util.ArrayList;
import java.util.List;

public class ProductionOperator implements EventSender {

    private static ProductionOperator instance;
    private List<LineWorker> workerList;


    private ProductionOperator(){
        this.workerList = new ArrayList<LineWorker>();
    }

    public static ProductionOperator getInstance() {
        if (instance == null) {
            instance = new ProductionOperator();
        }
        return instance;
    }


    public List<LineWorker> getWorkerList() {
        return workerList;
    }

    public void setWorkerList(List<LineWorker> workerList) {
        this.workerList = workerList;
    }

}
