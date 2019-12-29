package cz.cvut.fel.omo.hamrazec.model.machine.state;

import cz.cvut.fel.omo.hamrazec.model.machine.Machine;

import java.util.Random;

public class UnderRepair extends State {

    private int repairCounter;
    private int repairTime;

    public UnderRepair(Machine context) {
        super(context);
        this.repairCounter = 0;
        this.repairTime = new Random().nextInt((7 - 3) + 1) + 3;
    }

    public UnderRepair(Machine context, int repairCounter) {
        super(context);
        this.repairCounter = repairCounter;
    }

    @Override
    public boolean canWork() {
        if (context.getRepairingBy() != null){
            if (++repairCounter == repairTime) {
                context.setState(new Working(context));
                context.getRepairingBy().repair(context);
            }
        } else {
            context.setState(new Broken(context,repairCounter));
        }
        return false;
    }
}
