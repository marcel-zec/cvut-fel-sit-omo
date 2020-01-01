package cz.cvut.fel.omo.hamrazec.model.machine;

import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.VisitorDirector;
import cz.cvut.fel.omo.hamrazec.model.VisitorInspector;
import cz.cvut.fel.omo.hamrazec.model.events.EndProduction;
import cz.cvut.fel.omo.hamrazec.model.production.Product;

import java.util.ArrayList;
import java.util.List;

public class ControllingRobot extends Machine {
    private int controlAmount;
    private int finishedAmount;
    private List<Product> finishedProducts;

    public ControllingRobot(int yearOfManufacture, int productPerTact) {
        super(yearOfManufacture, productPerTact);
        finishedAmount = 0;
    }

    @Override
    public void update() {
        int finishedProductBeforeUpdate = finishedAmount;
        if(finishedAmount == productionLine.getSeries().getAmount()) {
            System.out.println(this.getClass().getSimpleName() + "(serial number: "+ getSerialNumber() +") count "
                    + finishedAmount + " finished products.");
            eventList.receive(new EndProduction(this,productionLine,productionLine.getSeries()));
            return;
        }
        if (!productsForWork.isEmpty() && state.canWork()){
            int size = productsForWork.size();
            for (int i = 0; i < Math.min(productPerTact,size); i++) {
                Product product = productsForWork.get(0);
                productsForWork.remove(product);
                if (product.getCompleted() == controlAmount){
                    finishedAmount++;
                    allWorkedProductAmount++;
                    addFinishedProduct(product);
                }
                depreciation();

            }
            System.out.println(this.getClass().getSimpleName() + "(serial number: "+ getSerialNumber() +") count "
                    + finishedAmount + " finished products (" + (finishedAmount - finishedProductBeforeUpdate) +" in this tact).");
        } else {
            System.out.println(this.getClass().getSimpleName() + "(serial number: "+ getSerialNumber() +") count "
                    + finishedAmount + " finished products (0 in this tact).");
        }

    }

    @Override
    public LineWorker setShareInProduction(int shareInProduction) {
        controlAmount = shareInProduction;
        return this;
    }

    private void addFinishedProduct(Product product){
        if (finishedProducts == null) finishedProducts = new ArrayList<>();
        finishedProducts.add(product);
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
