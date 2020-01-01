package cz.cvut.fel.omo.hamrazec.test.services;

import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.events.Alert;
import cz.cvut.fel.omo.hamrazec.model.machine.LineRobot;
import cz.cvut.fel.omo.hamrazec.services.EventList;
import cz.cvut.fel.omo.hamrazec.services.MachineGenerator;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EventListTest {
    EventList eventList = EventList.getInstance();
    List<LineWorker> workerList = new ArrayList<LineWorker>();
    Alert alert;
    MachineGenerator generator;


    public EventListTest() {
        workerList.add(generator.generateMachine());
        alert = new Alert(10, workerList.get(0));
    }


    @Test
    public void getRepairman_worksCorrect(){
        eventList.receive(alert);
        assertEquals("EventList doesnt receive alert",1,eventList.getEventList().size());
    }

}
