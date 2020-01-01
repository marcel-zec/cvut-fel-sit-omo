package cz.cvut.fel.omo.hamrazec.test.services;

import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.events.Alert;
import cz.cvut.fel.omo.hamrazec.model.machine.LineRobot;
import cz.cvut.fel.omo.hamrazec.services.EventList;
import cz.cvut.fel.omo.hamrazec.services.EventOperator;
import cz.cvut.fel.omo.hamrazec.services.MachineGenerator;
import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EventOperatorTest {
    private EventOperator eventOperator = EventOperator.getInstance();
    private EventList eventList = EventList.getInstance();
    private List<LineWorker> workerList = new ArrayList<LineWorker>();
    private Alert alert;
    private MachineGenerator generator;


    public EventOperatorTest() {
        workerList.add(generator.generateRobot());
        alert = new Alert(10, workerList.get(0));
    }


    @Before
    public void init() {
        eventList.attach(eventOperator);
    }

    @Test
    public void eventOperatorNotified_worksCorrect(){
        eventList.receive(alert);
        assertEquals("Event operator doesnt receive alert",1,eventOperator.getAlertList().size());
    }

}
