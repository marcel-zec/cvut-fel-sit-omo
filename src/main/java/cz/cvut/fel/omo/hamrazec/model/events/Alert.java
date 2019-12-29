package cz.cvut.fel.omo.hamrazec.model.events;

import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.machine.Machine;

public class Alert extends Event {

    public Alert(int priority, LineWorker sender) {
        this.priority = priority;
        this.sender = sender;
        this.tact = sender.getTact();
    }

    private int priority;


    public int getPriority() {
        return priority;
    }

}
