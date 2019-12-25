package cz.cvut.fel.omo.hamrazec.model.machine;

import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.production.Product;

public class LineRobot extends Machine {
    public LineRobot(int serialNumber, int yearOfManufacture, int productPerTact) {
        super(serialNumber, yearOfManufacture, productPerTact);
    }


    @Override
    public void work(Product product) {
        product.setCompleted(product.getCompleted() + productionShare);
        nextLineWorker.work(product);
    }
}
