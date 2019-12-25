package main.java.cz.cvut.fel.omo.hamrazec.model.machine;

import main.java.cz.cvut.fel.omo.hamrazec.model.production.Product;

public class ControllingRobot extends Machine {
    public ControllingRobot(int serialNumber, int yearOfManufacture, int productPerTack) {
        super(serialNumber, yearOfManufacture, productPerTack);
    }

    @Override
    public void work(Product product) {

    }
}
