package cz.cvut.fel.omo.hamrazec.model.person;

import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.VisitorDirector;
import cz.cvut.fel.omo.hamrazec.model.production.Product;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionLine;

import java.util.ArrayList;
import java.util.List;

public class Worker extends Person implements LineWorker {
    public Worker(String firstname, String lastname, int wage) {
        super(firstname, lastname, wage);
        this.productsForWork = new ArrayList<>();
    }

    private List<Product> productsForWork;
    private int productPerTact;
    private int productionShare;
    private LineWorker nextLineWorker;
    private ProductionLine productionLine;

    public int getProductionShare() {
        return productionShare;
    }

    public void setProductionShare(int productionShare) {
        this.productionShare = productionShare;
    }

    @Override
    public void forWork(Product product){
        productsForWork.add(product);
    }

    @Override
    public void update() {
        if (productsForWork.isEmpty()){
            nextLineWorker.update();
        } else {
            for (int i = 0; i < Math.min(productPerTact,productsForWork.size()); i++) {
                Product product = productsForWork.get(0);
                product = workOnProduct(product);
                nextLineWorker.forWork(product);
            }
            nextLineWorker.update();
        }
    }

    @Override
    public LineWorker setProductionLine(ProductionLine line) {
        this.productionLine = line;
        return this;
    }

    private Product workOnProduct(Product product) {
        product.setCompleted(product.getCompleted() + getProductionShare());
        return product;
    }

    @Override
    public LineWorker setNextWorker(LineWorker nextWorker) {
        this.nextLineWorker = nextWorker;
        return nextWorker;
    }

    public LineWorker getNextWorker(){
        return nextLineWorker;
    }

    @Override
    public LineWorker setShareInProduction(int shareInProduction) {
        this.productionShare = shareInProduction;
        return this;
    }

    @Override
    public int getTact() {
        return tact;
    }

    @Override
    public void updateTact(int tact) {
        this.tact = tact;
    }

    @Override
    public void accept(VisitorDirector visitor) {
        visitor.visit(this);
    }


    @Override
    public String toString() {

        return "Worker: " + firstName + " " + lastName  +
                ", wage=" + wage;
    }
}
