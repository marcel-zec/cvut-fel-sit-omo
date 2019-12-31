package cz.cvut.fel.omo.hamrazec.model.machine.state;

import cz.cvut.fel.omo.hamrazec.model.machine.Machine;

import java.util.Random;

public class UnderRepair extends State {

    private int repairCounter;
    private int repairTime;

    public UnderRepair(Machine context) {
        super(context);
        this.repairCounter = 0;
        this.repairTime = new Random().nextInt((7 - 3) + 1) + 3; //nextInt(max - min + 1) + min
    }

    public UnderRepair(Machine context, int repairCounter) {
        super(context);
        this.repairCounter = repairCounter;
        this.repairTime = new Random().nextInt((7 - 3) + 1) + 3;
    }

    @Override
    public boolean canWork() {
        if (context.getRepairingBy() != null){
            System.out.println("Repairing in progress at " + context.getClass().getSimpleName() + "(serial number:" + context.getSerialNumber() + ")");
            if (++repairCounter == repairTime) {
                context.getRepairingBy().endRepair(context);
                context.setState(new Working(context));
            }
        } else {
            context.setState(new Broken(context,repairCounter));
        }
        return false;
    }
}
