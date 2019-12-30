package cz.cvut.fel.omo.hamrazec.test.services;

import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.events.Alert;
import cz.cvut.fel.omo.hamrazec.model.machine.LineRobot;
import cz.cvut.fel.omo.hamrazec.services.EventList;
import org.junit.Test;
import org.junit.Before;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EventListTest {
    EventList eventList = EventList.getInstance();
    List<LineWorker> workerList = new ArrayList<LineWorker>();
    Alert alert;


    public EventListTest() {
        workerList.add(new LineRobot(2000,7));
        alert = new Alert(10, workerList.get(0));
    }

    @Before
    public void init() {

    }

    @Test
    public void getRepairman_worksCorrect(){
        eventList.receive(alert);
        assertEquals("EventList doesnt receive alert",1,eventList.getEventList().size());
    }

}
