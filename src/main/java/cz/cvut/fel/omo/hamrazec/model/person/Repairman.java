package cz.cvut.fel.omo.hamrazec.model.person;

import cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.VisitorDirector;
import cz.cvut.fel.omo.hamrazec.model.events.EndRepair;
import cz.cvut.fel.omo.hamrazec.model.machine.Machine;

public class Repairman extends Person {

    public Repairman(String firstName, String lastName, int wage) {
        super(firstName, lastName, wage);
    }

    public void repair(Machine machine){
        machine.setRepairingBy(this);
    }

    public void endRepair(Machine machine){
        machine.setRepairingBy(null);
        eventList.receive(new EndRepair(this,machine));
    }


    @Override
    public void accept(VisitorDirector visitor) {
        visitor.visit(this);
    }


    @Override
    public String toString() {

        return "Repairman: " +
                firstName + " " + lastName  +
                ", wage=" + wage;
    }
}
