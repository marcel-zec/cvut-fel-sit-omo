package cz.cvut.fel.omo.hamrazec.model.person;

import cz.cvut.fel.omo.hamrazec.model.VisitorDirector;
import cz.cvut.fel.omo.hamrazec.model.VisitorInspector;
import cz.cvut.fel.omo.hamrazec.model.machine.ControllingRobot;
import cz.cvut.fel.omo.hamrazec.model.machine.LineMachine;
import cz.cvut.fel.omo.hamrazec.model.machine.LineRobot;

public class Inspector extends Person implements VisitorInspector {
    public Inspector(String firstName, String lastName, int wage) {
        super(firstName, lastName, wage);
    }

    @Override
    public void visit(LineRobot lineRobot) {
    }


    @Override
    public void visit(ControllingRobot controllingRobot) {

    }


    @Override
    public void visit(LineMachine machine) {
    }

    public void startIterate(InspectorIterator inspectorIterator){

        while (inspectorIterator.hasNext()){
            //inspectorIterator.next().acc
        }
    }


    @Override
    public void accept(VisitorDirector visitor) {

    }
}
