package main.java.cz.cvut.fel.omo.hamrazec.model.machine;

import main.java.cz.cvut.fel.omo.hamrazec.model.production.Product;

public class LineMachine extends Machine {
    public LineMachine(int serialNumber, int yearOfManufacture) {

        super(serialNumber, yearOfManufacture);
    }


    @Override
    public void work(Product product) {

    }
}
