package cz.cvut.fel.omo.hamrazec.model.machine;

import cz.cvut.fel.omo.hamrazec.model.VisitorDirector;
import cz.cvut.fel.omo.hamrazec.model.VisitorInspector;

public class LineRobot extends Machine {

    public LineRobot(int yearOfManufacture, int productPerTact, double kwPerTack, double oilPerTack, double petrolPerTack, double materialPerProduct) {
        super(yearOfManufacture, productPerTact, kwPerTack, oilPerTack, petrolPerTack, materialPerProduct);
    }

    @Override
    public void accept(VisitorDirector visitor) {
        visitor.visit(this);
    }


    @Override
    public void accept(VisitorInspector visitor) {
        visitor.visit(this);
    }
}
