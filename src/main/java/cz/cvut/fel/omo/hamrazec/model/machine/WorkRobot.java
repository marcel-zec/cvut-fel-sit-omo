package main.java.cz.cvut.fel.omo.hamrazec.model.machine;

import main.java.cz.cvut.fel.omo.hamrazec.model.production.Product;

public class WorkRobot extends Machine {
    public WorkRobot(int serialNumber, int yearOfManufacture) {

        super(serialNumber, yearOfManufacture);
    }


    @Override
    public void work(Product product) {

    }
}
