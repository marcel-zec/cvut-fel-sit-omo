package cz.cvut.fel.omo.hamrazec;

import cz.cvut.fel.omo.hamrazec.controller.Factory;
import cz.cvut.fel.omo.hamrazec.controller.ProductionOperator;
import cz.cvut.fel.omo.hamrazec.controller.SeriesName;
import cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.machine.ControllingRobot;
import cz.cvut.fel.omo.hamrazec.model.machine.LineMachine;
import cz.cvut.fel.omo.hamrazec.model.machine.LineRobot;
import cz.cvut.fel.omo.hamrazec.model.person.Director;
import cz.cvut.fel.omo.hamrazec.model.person.DirectorIterator;
import cz.cvut.fel.omo.hamrazec.model.person.Worker;
import cz.cvut.fel.omo.hamrazec.services.FactoryTimer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<LineWorker> workers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Factory factory = Factory.getInstance();
        ProductionOperator operator = factory.getProductionOperator();
        FactoryTimer timer = FactoryTimer.getInstance();
        timer.timeLapse();
        DirectorIterator directorIterator = new DirectorIterator();
        Director director = new Director("Palo", "Novu", 500);


        initWorkers();
        List<FactoryWorker> factoryWorkers = new ArrayList<>(workers);
        factory.setLineWorkers(workers);
        factory.putFactoryWorkersToFactory( factoryWorkers);
        factory.putWorkersToProduction(factory.getLineWorkers());
        operator.addSeriesToPlan(200, SeriesName.SeriesA,1);
        operator.addSeriesToPlan(50, SeriesName.SeriesA,2);
        System.out.println(operator.getPlan());
        operator.activateLines();

        director.startIterate(directorIterator);

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
        workers.add(new LineRobot(4,2002,4));
        workers.add(new LineRobot(5,2002,4));
        workers.add(new LineRobot(6,2002,4));
        workers.add(new LineRobot(7,2002,4));
        workers.add(new ControllingRobot(7,2002,4));

    }
}
