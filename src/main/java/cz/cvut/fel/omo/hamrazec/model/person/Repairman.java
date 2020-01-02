package cz.cvut.fel.omo.hamrazec.model.person;

import cz.cvut.fel.omo.hamrazec.model.VisitorDirector;
import cz.cvut.fel.omo.hamrazec.model.events.EndRepair;
import cz.cvut.fel.omo.hamrazec.model.events.StartRepair;
import cz.cvut.fel.omo.hamrazec.model.machine.Machine;

public class Repairman extends Person {

    public Repairman() {
    }


    public Repairman(String firstName, String lastName, int wage) {
        super(firstName, lastName, wage);
    }

    /**
     * Method set this repairman to machine and send StartRepair event to eventlist.
     * Reparation is simulated in UnderRepair state of machine.
     * @param machine
     */
    public void repair(Machine machine){
        machine.setRepairingBy(this);
        eventList.receive(new StartRepair(this,machine));
    }

    /**
     * At first is called method for decrease depreciation on machine if its very depreciation.
     * Than is repairman unset from machine and sended EndRepair event to eventlist.
     * @param machine
     */
    public void endRepair(Machine machine){
        machine.depreciationAfterRepair();
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
