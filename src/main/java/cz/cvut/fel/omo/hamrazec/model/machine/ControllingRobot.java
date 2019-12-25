package cz.cvut.fel.omo.hamrazec.model.machine;

import cz.cvut.fel.omo.hamrazec.model.production.Product;

public class ControllingRobot extends Machine {
    private int controlAmount;

    public ControllingRobot(int serialNumber, int yearOfManufacture, int productPerTact) {
        super(serialNumber, yearOfManufacture, productPerTact);
    }

    public int getControlAmount() {
        return controlAmount;
    }

    public void setControlAmount(int controlAmount) {
        this.controlAmount = controlAmount;
    }

    @Override
    public void work(Product product) {

    }
}
