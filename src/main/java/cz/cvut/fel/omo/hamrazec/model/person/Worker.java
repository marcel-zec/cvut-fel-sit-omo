package cz.cvut.fel.omo.hamrazec.model.person;

import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.VisitorDirector;
import cz.cvut.fel.omo.hamrazec.model.production.Product;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.List;

public class Worker extends Person implements LineWorker {
    public Worker(String firstname, String lastname, int wage) {
        super(firstname, lastname, wage);
    }

    private List<Product> productsForWork;
    private int productPerTact;
    private int productionShare;
    private LineWorker nextLineWorker;

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

    private Product workOnProduct(Product product) {
        product.setCompleted(product.getCompleted() + getProductionShare());
        return product;
    }

    @Override
    public LineWorker setNextWorker(LineWorker nextWorker) {
        return null;
    }

    @Override
    public LineWorker setShareInProduction(int shareInProduction) {
        return null;
    }

    @Override
    public int getTact() {
        return 0;
    }

    @Override
    public void updateTact(int tact) {

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
