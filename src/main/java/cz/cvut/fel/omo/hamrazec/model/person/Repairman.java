package cz.cvut.fel.omo.hamrazec.model.person;

import cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import cz.cvut.fel.omo.hamrazec.model.Visitor;
import cz.cvut.fel.omo.hamrazec.model.machine.Machine;

public class Repairman extends Person {

    public Repairman(String firstName, String lastName, int wage) {
        super(firstName, lastName, wage);
    }

    public void repair(FactoryWorker machine){
        //todo repair
    }


    @Override
    public void accept(Visitor visitor) {
    }

}
