package cz.cvut.fel.omo.hamrazec.model.machine.state;

import cz.cvut.fel.omo.hamrazec.model.machine.Machine;

abstract public class State {
    protected Machine context;

    public State(Machine context) {
        this.context = context;
    }

    /**
     * Method simulated time of states of machines.
     * @return true when machine can work, false otherwise
     */
    public abstract boolean canWork();


}
