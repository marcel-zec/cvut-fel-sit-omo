package cz.cvut.fel.omo.hamrazec.model.machine;

import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.Visitor;
import cz.cvut.fel.omo.hamrazec.model.production.Product;

public class LineRobot extends Machine {

    public LineRobot(int serialNumber, int yearOfManufacture, int productPerTact) {
        super(serialNumber, yearOfManufacture, productPerTact);
    }

    @Override
    protected Product workOnProduct(Product product) {
        return null;
    }


    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
