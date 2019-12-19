package cz.cvut.fel.omo.hamrazec;

import java.util.ArrayList;
import java.util.List;

public class OperatorOfProduction {

    private static OperatorOfProduction instance;
    private List<LineWorker> workerList;


    private OperatorOfProduction(){
        this.workerList = new ArrayList<LineWorker>();
    }

    public static OperatorOfProduction getInstance() {
        if (instance == null) {
            instance = new OperatorOfProduction();
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
