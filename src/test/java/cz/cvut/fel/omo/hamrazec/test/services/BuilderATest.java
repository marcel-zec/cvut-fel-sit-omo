package cz.cvut.fel.omo.hamrazec.test.services;

import cz.cvut.fel.omo.hamrazec.model.machine.LineMachine;
import cz.cvut.fel.omo.hamrazec.model.machine.LineRobot;
import cz.cvut.fel.omo.hamrazec.model.person.Worker;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BuilderATest {

    @Before
    public void init() {

        LineMachine lineMachine = new LineMachine(2000,5);
        LineMachine lineMachine1 = new LineMachine(2000,7);
        LineMachine lineMachine2 = new LineMachine(2000,3);
        LineMachine lineMachine3 = new LineMachine(2000,9);
        LineRobot lineRobot = new LineRobot(2010,7);
        LineRobot lineRobot1 = new LineRobot(2010,11);
        LineRobot lineRobot2 = new LineRobot(2010,5);
        Worker worker = new Worker("Jozef","Jano", 200, 3);
        Worker worker1 = new Worker("Jozef","Jodo", 200, 5);
    }


    @Test
    public void getRepairman_worksCorrect(){
        //assertEquals("EventList doesnt receive alert",1,eventList.getEventList().size());
    }
}
