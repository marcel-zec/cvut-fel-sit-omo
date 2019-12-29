package cz.cvut.fel.omo.hamrazec.model.machine;

import cz.cvut.fel.omo.hamrazec.model.VisitorDirector;
import cz.cvut.fel.omo.hamrazec.model.production.Product;

public class ControllingRobot extends Machine {
    private int controlAmount;

    public ControllingRobot(int serialNumber, int yearOfManufacture, int productPerTact) {
        super(serialNumber, yearOfManufacture, productPerTact);
    }

    @Override
    public void update() {

    }

    @Override
    protected Product workOnProduct(Product product) {
        return null;
    }

    public int getControlAmount() {
        return controlAmount;
    }

    public void setControlAmount(int controlAmount) {
        this.controlAmount = controlAmount;
    }

    @Override
    public void accept(VisitorDirector visitor) {
        visitor.visit(this);
    }
}
