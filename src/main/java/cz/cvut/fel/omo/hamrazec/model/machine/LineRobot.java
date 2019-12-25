package main.java.cz.cvut.fel.omo.hamrazec.model.machine;

import main.java.cz.cvut.fel.omo.hamrazec.model.LineWorker;
import main.java.cz.cvut.fel.omo.hamrazec.model.production.Product;

public class LineRobot extends Machine {
    public LineRobot(int serialNumber, int yearOfManufacture, int productPerTack) {
        super(serialNumber, yearOfManufacture, productPerTack);
    }


    @Override
    public void work(Product product) {
        product.setCompleted(product.getCompleted() + productionShare);
        nextLineWorker.work(product);
    }
}
