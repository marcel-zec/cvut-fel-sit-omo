package cz.cvut.fel.omo.hamrazec;

import cz.cvut.fel.omo.hamrazec.controller.Factory;
import cz.cvut.fel.omo.hamrazec.controller.ProductionOperator;
import cz.cvut.fel.omo.hamrazec.controller.SeriesName;
import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.person.*;
import cz.cvut.fel.omo.hamrazec.services.FactoryTimer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<LineWorker> workers = new ArrayList<>();
    public static List<Repairman> repairmen = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        Factory factory = Factory.getInstance();
        ProductionOperator operator = factory.getProductionOperator();
        FactoryTimer timer = FactoryTimer.getInstance();

        timer.timeLapse();

 //       initWorkers();
  //      initRepairmen();
//        List<FactoryWorker> factoryWorkers = new ArrayList<>(workers);
//        factory.setLineWorkers(workers);
//        factory.putFactoryWorkersToFactory( factoryWorkers);
        factory.putWorkersToProduction(factory.getLineWorkers());
//        factory.getPool().setRepairmen(repairmen);
        operator.addSeriesToPlan(100, SeriesName.SeriesC,1);
        //operator.addSeriesToPlan(50, SeriesName.SeriesC,2);
        operator.activateLines();

//        factory.setInspector(new Inspector("Palo", "Novu", 300));
//        factory.setDirector(new Director("Palo", "Novu", 500));

//        factory.getDirector().startIterate(new DirectorIterator());
    }

    public static void initWorkers(){
//        workers.add(new LineMachine(2002,6));
//        workers.add(new LineMachine(2002,8));
//        workers.add(new LineMachine(2002,4));
//        workers.add(new LineMachine(2002,6));
//        workers.add(new Worker("Jan","Novak",200, 5));
//        workers.add(new Worker("Jan1","Novak1",200, 5));
//        workers.add(new Worker("Jan2","Novak2",200, 4));
//        workers.add(new Worker("Jan3","Novak3",200, 6));
//        workers.add(new LineRobot(2002,4));
//        workers.add(new LineRobot(2002,8));
//        workers.add(new LineRobot(2002,7));
//        workers.add(new LineRobot(2002,4));
//        workers.add(new ControllingRobot(2002,4));
    }

    public static void initRepairmen(){
//        repairmen.add(new Repairman("Janko","Hrasko", 300));
//        repairmen.add(new Repairman("Marian","Becko", 300));
    }
}
