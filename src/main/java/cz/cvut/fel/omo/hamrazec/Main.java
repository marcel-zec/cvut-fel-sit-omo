package cz.cvut.fel.omo.hamrazec;

import cz.cvut.fel.omo.hamrazec.controller.Factory;
import cz.cvut.fel.omo.hamrazec.controller.ProductionOperator;
import cz.cvut.fel.omo.hamrazec.controller.SeriesName;
import cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.machine.LineMachine;
import cz.cvut.fel.omo.hamrazec.model.person.Worker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<LineWorker> workers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Factory factory = Factory.getInstance();
        ProductionOperator operator = factory.getProductionOperator();
        initWorkers();
        factory.setLineWorkers(workers);
        factory.putWorkersToProduction(factory.getLineWorkers());
        operator.addSeriesToPlan(200, SeriesName.SeriesA,1);
        operator.addSeriesToPlan(50, SeriesName.SeriesA,2);
        System.out.println(operator.getPlan());
        operator.activateLines();

    }

    public static void initWorkers(){
        workers.add(new LineMachine(0,2002,6));
        workers.add(new LineMachine(1,2002,8));
        workers.add(new LineMachine(2,2002,4));
        workers.add(new LineMachine(3,2002,6));
        workers.add(new Worker("Jan","Novak",200));
        workers.add(new Worker("Jan1","Novak1",200));
        workers.add(new Worker("Jan2","Novak2",200));
        workers.add(new Worker("Jan3","Novak3",200));

    }
}
