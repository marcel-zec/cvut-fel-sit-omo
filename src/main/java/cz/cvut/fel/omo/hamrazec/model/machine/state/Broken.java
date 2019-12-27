package cz.cvut.fel.omo.hamrazec.model.machine.state;

import cz.cvut.fel.omo.hamrazec.model.machine.Machine;

public class Broken extends State {
    public Broken(Machine context) {
        super(context);
    }

    @Override
    public boolean canWork() {
        //TODO - broken
        return false;
    }
}
