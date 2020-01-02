package cz.cvut.fel.omo.hamrazec.model.person;

import cz.cvut.fel.omo.hamrazec.model.VisitorDirector;
import cz.cvut.fel.omo.hamrazec.model.VisitorInspector;
import cz.cvut.fel.omo.hamrazec.model.costs.PayCheck;
import cz.cvut.fel.omo.hamrazec.model.machine.ControllingRobot;
import cz.cvut.fel.omo.hamrazec.model.machine.LineMachine;
import cz.cvut.fel.omo.hamrazec.model.machine.LineRobot;

import java.util.logging.Logger;

public class Inspector extends Person implements VisitorInspector {
    private static final Logger LOG = Logger.getLogger(Inspector.class.getName());


    public Inspector() {
    }

    public Inspector(String firstName, String lastName, double wage) {
        super(firstName, lastName, wage);
    }

    @Override
    public void visit(LineRobot lineRobot) {
        LOG.info("Deprecation in " + lineRobot.getClass().getSimpleName() + "(serial number: " + lineRobot.getSerialNumber() + ")" + " is: " + lineRobot.getDepreciation() + "%");
    }


    @Override
    public void visit(ControllingRobot controllingRobot) {
        LOG.info("Deprecation in " + controllingRobot.getClass().getSimpleName()  + "(serial number: " + controllingRobot.getSerialNumber() + ")" + " is: " + controllingRobot.getDepreciation() + "%");
    }


    @Override
    public void visit(LineMachine machine) {
        LOG.info("Deprecation in " + machine.getClass().getSimpleName()  + "(serial number: " + machine.getSerialNumber() + ")" + " is: " + machine.getDepreciation() + "%");

    }

    /**
     * Starting inspection of factory.
     * @param inspectorIterator
     */
    public void startIterate(InspectorIterator inspectorIterator) {
        System.out.println("______________________________");
        System.out.println("\"It is time for inspection.\"\n - "+ firstName + " " + lastName +", inspector");
        while (inspectorIterator.hasNext()) {
            inspectorIterator.next().accept(this);
        }
        addPayCheck(new PayCheck(tact,wage));
        System.out.println("______________________________");
    }


    @Override
    public void accept(VisitorDirector visitor) {

    }
}
