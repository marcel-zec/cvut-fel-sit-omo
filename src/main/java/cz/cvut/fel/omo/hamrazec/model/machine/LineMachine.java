package cz.cvut.fel.omo.hamrazec.model.machine;

import cz.cvut.fel.omo.hamrazec.model.VisitorDirector;
import cz.cvut.fel.omo.hamrazec.model.production.Product;


public class LineMachine extends Machine {

    public LineMachine(int serialNumber, int yearOfManufacture, int productPerTact) {
        super(serialNumber, yearOfManufacture, productPerTact);
    }

    @Override
    protected Product workOnProduct(Product product) {
        return null;
    }


    @Override
    public void accept(VisitorDirector visitor) {
        visitor.visit(this);
    }


    @Override
    public String toString() {

        return "LineMachine: " +
                "serialNumber = " + serialNumber +
                ", yearOfManufacture = " + yearOfManufacture +
                ", productionShare = " + productionShare +
                ", productPerTact = " + productPerTact +
                ", state = " + state.getClass().getSimpleName();
    }
}
