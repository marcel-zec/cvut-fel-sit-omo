package cz.cvut.fel.omo.hamrazec;

import cz.cvut.fel.omo.hamrazec.controller.Factory;
import cz.cvut.fel.omo.hamrazec.controller.ProductionOperator;
import cz.cvut.fel.omo.hamrazec.controller.SeriesName;
import cz.cvut.fel.omo.hamrazec.exceptions.SeriesNotExistException;
import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.person.*;
import cz.cvut.fel.omo.hamrazec.services.FactoryTimer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<LineWorker> workers = new ArrayList<>();
    public static List<Repairman> repairmen = new ArrayList<>();

    public static void main(String[] args) throws IOException, SeriesNotExistException {

        Factory factory = Factory.getInstance();
        ProductionOperator operator = factory.getProductionOperator();
        FactoryTimer timer = FactoryTimer.getInstance();

        timer.timeLapse(1);

        factory.putWorkersToProduction(factory.getLineWorkers());
        operator.addSeriesToPlan(200, SeriesName.SeriesC,1);
        operator.addSeriesToPlan(50, SeriesName.SeriesB,2);
        operator.addSeriesToPlan(300, SeriesName.SeriesA,1);
    }
}
