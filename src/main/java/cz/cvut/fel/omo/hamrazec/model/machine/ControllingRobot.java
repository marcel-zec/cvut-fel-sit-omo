package cz.cvut.fel.omo.hamrazec.model.machine;

import cz.cvut.fel.omo.hamrazec.model.VisitorDirector;
import cz.cvut.fel.omo.hamrazec.model.VisitorInspector;
import cz.cvut.fel.omo.hamrazec.model.production.Product;

public class ControllingRobot extends Machine {
    private int controlAmount;

    public ControllingRobot(int yearOfManufacture, int productPerTact) {
        super(yearOfManufacture, productPerTact);
    }

    @Override
    public void update() {
        if (!productsForWork.isEmpty() && state.canWork()){
            for (int i = 0; i < Math.min(productPerTact,productsForWork.size()); i++) {
                Product product = productsForWork.get(0);
                //TODO - kontrolovanie
            }
        }
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


    @Override
    public void accept(VisitorInspector visitor) {
        visitor.visit(this);
    }
}
