package cz.cvut.fel.omo.hamrazec.model.events;

import cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import cz.cvut.fel.omo.hamrazec.model.machine.ControllingRobot;

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
