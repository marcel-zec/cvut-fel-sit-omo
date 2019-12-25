package main.java.cz.cvut.fel.omo.hamrazec.model.machine;

import main.java.cz.cvut.fel.omo.hamrazec.model.production.Product;

public class ControllingRobot extends Machine {
    private int controlAmount;

    public ControllingRobot(int serialNumber, int yearOfManufacture, int productPerTack) {
        super(serialNumber, yearOfManufacture, productPerTack);
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
