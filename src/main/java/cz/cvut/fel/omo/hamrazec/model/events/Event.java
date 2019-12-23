package main.java.cz.cvut.fel.omo.hamrazec.model.events;

import main.java.cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import main.java.cz.cvut.fel.omo.hamrazec.model.machine.ControllingRobot;

public abstract class Event {

    FactoryWorker sender;
    int tact;


    public FactoryWorker getSender() {

        return sender;
    }


    public int getTact() {

        return tact;
    }
}
