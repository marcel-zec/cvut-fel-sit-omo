package main.java.cz.cvut.fel.omo.hamrazec.model.events;

import main.java.cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import main.java.cz.cvut.fel.omo.hamrazec.model.machine.Machine;

public class EndRepair extends Event {
    private Machine repaired;


    public EndRepair(FactoryWorker sender, Machine repaired) {
        this.sender = sender;
        this.repaired = repaired;
        this.tact = sender.getTact();
    }


    public Machine getRepaired() {

        return repaired;
    }
}
