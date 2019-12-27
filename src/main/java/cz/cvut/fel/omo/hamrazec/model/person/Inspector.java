package cz.cvut.fel.omo.hamrazec.model.person;

import cz.cvut.fel.omo.hamrazec.model.VisitorDirector;
import cz.cvut.fel.omo.hamrazec.model.VisitorInspector;

public class Inspector extends Person implements VisitorInspector {
    public Inspector(String firstName, String lastName, int wage) {
        super(firstName, lastName, wage);
    }


    @Override
    public void accept(VisitorDirector visitor) {
    }

}
