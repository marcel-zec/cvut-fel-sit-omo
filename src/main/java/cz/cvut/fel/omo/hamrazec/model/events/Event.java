package main.java.cz.cvut.fel.omo.hamrazec.model.events;

import main.java.cz.cvut.fel.omo.hamrazec.model.machine.ControllingRobot;

public abstract class Event {

    EventSender sender;
    int tact;


    public EventSender getSender() {

        return sender;
    }


    public int getTact() {

        return tact;
    }
}
