package cz.cvut.fel.omo.hamrazec.model.person;

import cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import cz.cvut.fel.omo.hamrazec.model.VisitorDirector;

public class Repairman extends Person {

    public Repairman(String firstName, String lastName, int wage) {
        super(firstName, lastName, wage);
    }

    public void repair(FactoryWorker machine){
        //todo repair
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
