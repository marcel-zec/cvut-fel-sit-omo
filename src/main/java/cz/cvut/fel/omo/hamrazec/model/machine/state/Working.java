package cz.cvut.fel.omo.hamrazec.model.machine.state;

import cz.cvut.fel.omo.hamrazec.model.machine.Machine;

public class Working extends State {
    public Working(Machine context) {
        super(context);
    }

    @Override
    public boolean canWork() {
        if (true)//podmienka kedy sa ma pokazit
        {
            context.setState(new Broken(context));
            return false;
        } else {
            return true;
        }
    }
}
