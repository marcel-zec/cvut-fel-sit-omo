package cz.cvut.fel.omo.hamrazec.model.person;

import cz.cvut.fel.omo.hamrazec.model.Visitor;

public class Inspector extends Person implements Visitor {
    public Inspector(String firstName, String lastName, int wage) {
        super(firstName, lastName, wage);
    }


    @Override
    public void visit(Object o) {

    }
}
