package cz.cvut.fel.omo.hamrazec.test.services;

import cz.cvut.fel.omo.hamrazec.controller.Factory;
import cz.cvut.fel.omo.hamrazec.exceptions.CannotBuildLineException;
import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.machine.ControllingRobot;
import cz.cvut.fel.omo.hamrazec.model.machine.LineMachine;
import cz.cvut.fel.omo.hamrazec.model.machine.LineRobot;
import cz.cvut.fel.omo.hamrazec.model.person.Worker;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionLine;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionSeries;
import cz.cvut.fel.omo.hamrazec.services.SeriesFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BuilderBTest {

        private Factory factory = Factory.getInstance();
        private SeriesFactory seriesFactory = new SeriesFactory();
        private ProductionSeries productionSeries = seriesFactory.getSeriesB(100,1);


    public BuilderBTest() throws IOException {
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() throws IOException {

        List<LineWorker> robotList = new ArrayList<>();
        List<LineWorker> machineList = new ArrayList<>();
        List<LineWorker> peopleList = new ArrayList<>();
        List<LineWorker> workers = new ArrayList<>();
        machineList.add(new LineMachine(2000, 5));
        machineList.add(new LineMachine(2000, 7));
        robotList.add(new LineRobot(2010, 7));
        robotList.add(new LineRobot(2010, 11));
        robotList.add(new LineRobot(2010, 5));
        robotList.add(new LineRobot(2010, 10));
        peopleList.add(new Worker("Jozef", "Jano", 200, 3));
        peopleList.add(new Worker("Jozef", "Judas", 200, 5));
        peopleList.add(new Worker("Peter", "Korel", 200, 2));
        peopleList.add(new Worker("Zdeno", "Dalas", 200, 6));
        peopleList.add(new Worker("Jan", "Bolo", 200, 8));
        ControllingRobot controllingRobot = new ControllingRobot(2018, 14);

        workers.addAll(robotList);
        workers.addAll(machineList);
        workers.addAll(peopleList);
        workers.add(controllingRobot);
        factory.setLineWorkers(workers);
        factory.putWorkersToProduction(workers);
    }


        @Test
    public void getProductionSeriesFromLineB_worksCorrect() throws CannotBuildLineException {

        ProductionLine productionLine = productionSeries.build();
        assertEquals("Line doesnt build",productionSeries,productionLine.getSeries());
    }

    @Test
    public void getProductionSeriesFromLineB_noEnaughtWorkers_worksCorrect() throws CannotBuildLineException {

        thrown.expect(CannotBuildLineException.class);
        thrown.reportMissingExceptionWithMessage("Line was created without lineworkers");
        productionSeries.build();
        productionSeries.build();
    }

}
