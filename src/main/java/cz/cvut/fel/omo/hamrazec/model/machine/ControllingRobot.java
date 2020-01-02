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

    public ControllingRobot(int yearOfManufacture, int productPerTact, double kwPerTack, double oilPerTack, double petrolPerTack, double materialPerProduct) {
        super(yearOfManufacture, productPerTact, kwPerTack, oilPerTack, petrolPerTack, materialPerProduct);
        finishedAmount = 0;
    }

    /**
     * At first check if amount of finished product is equals amount of series. Send EndProduction events when amount of
     * finished products is correct. Otherwise check quality of product from line. At the end is called method for simulate
     * depreciation of machine.
     */
    @Override
    public void update() {
        int finishedProductBeforeUpdate = finishedAmount;
        boolean canWork = state.canWork();
        //checking amount of finished product for end production
        if(canWork && finishedAmount == productionLine.getSeries().getAmount()) {
            System.out.println(this.getClass().getSimpleName() + "(serial number: "+ getSerialNumber() +") count "
                    + finishedAmount + " finished products.");
            eventList.receive(new EndProduction(this,productionLine,productionLine.getSeries()));
            return;
        }
        if (canWork && !productsForWork.isEmpty()){
            //checking quality of products
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
