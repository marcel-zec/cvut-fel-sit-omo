package cz.cvut.fel.omo.hamrazec.model.events;

import cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.machine.ControllingRobot;
import cz.cvut.fel.omo.hamrazec.model.machine.Machine;

public abstract class Event<T extends FactoryWorker> {

    T sender;
    int tact;


    public T getSender() {

        return sender;
    }


    public int getTact() {

        return tact;
    }
}
