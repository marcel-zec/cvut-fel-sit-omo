package main.java.cz.cvut.fel.omo.hamrazec.model.machine;

import main.java.cz.cvut.fel.omo.hamrazec.model.production.Product;

public class LineRobot extends Machine {
    public LineRobot(int serialNumber, int yearOfManufacture) {

        super(serialNumber, yearOfManufacture);
    }


    @Override
    public void work(Product product) {

    }
}
