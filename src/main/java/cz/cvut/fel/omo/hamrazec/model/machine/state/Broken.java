package cz.cvut.fel.omo.hamrazec.model.machine.state;

import cz.cvut.fel.omo.hamrazec.model.machine.Machine;

public class Broken extends State {
    private int partiallyRepair;

    public Broken(Machine context) {
        super(context);
        this.partiallyRepair = 0;
    }

    public Broken(Machine context, int partiallyRepair) {
        super(context);
        this.partiallyRepair = partiallyRepair;
    }

    @Override
    public boolean canWork() {
        if (context.getRepairingBy() != null){
            if (partiallyRepair > 0) context.setState(new UnderRepair(context,partiallyRepair));
            else context.setState(new UnderRepair(context));
        }
        return false;
    }
}
