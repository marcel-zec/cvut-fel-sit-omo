package cz.cvut.fel.omo.hamrazec.model.machine;

import cz.cvut.fel.omo.hamrazec.model.VisitorDirector;
import cz.cvut.fel.omo.hamrazec.model.VisitorInspector;


public class LineMachine extends Machine {

    public LineMachine(int yearOfManufacture, int productPerTact, double kwPerTack, double oilPerTack, double petrolPerTack, double materialPerProduct) {
        super(yearOfManufacture, productPerTact, kwPerTack, oilPerTack, petrolPerTack, materialPerProduct);
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


    @Override
    public void accept(VisitorInspector visitor) {
        visitor.visit(this);
    }
}
