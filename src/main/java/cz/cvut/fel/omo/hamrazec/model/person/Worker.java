package cz.cvut.fel.omo.hamrazec.model.person;

import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.VisitorDirector;
import cz.cvut.fel.omo.hamrazec.model.costs.PayCheck;
import cz.cvut.fel.omo.hamrazec.model.production.Product;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionLine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker extends Person implements LineWorker {

    private List<Product> productsForWork;
    private int productPerTact;
    private int productionShare;
    private LineWorker nextLineWorker;
    private ProductionLine productionLine;

    public Worker() {
        this.productsForWork = new ArrayList<>();
    }

    public Worker(String firstname, String lastname, double wage, int productPerTact) {
        super(firstname, lastname, wage);
        this.productsForWork = new ArrayList<>();
        this.productPerTact = productPerTact;
    }

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
    public void emptyForWorkList(){
        productsForWork = new ArrayList<>();
    }

    /**
     * When worked does not have products to work on, it called update on next line worker in chain of responsibility.
     * Otherwise worker works on product and send it to next line worker. At the end generate pay check for tact and
     * called update on next line worker in chain of responsibility.
     */
    @Override
    public void update() {
        if (productsForWork.isEmpty()){
            System.out.println(firstName + " " + lastName + " worked at 0 products.");
            nextLineWorker.update();
        } else {
            int workedProductInTact = Math.min(productPerTactCount(),productsForWork.size());
            for (int i = 0; i < workedProductInTact; i++) {
                Product product = productsForWork.get(0);
                productsForWork.remove(product);
                nextLineWorker.forWork(workOnProduct(product));
            }
            System.out.println(firstName + " " + lastName + " worked at " + workedProductInTact + " products.");
            nextLineWorker.update();

            if (workedProductInTact > productPerTact){
                addPayCheck(new PayCheck(tact, wage*1.05));
            }
            else if (workedProductInTact < productPerTact) {
                addPayCheck(new PayCheck(tact, wage*0.95));
            }
            else addPayCheck(new PayCheck(tact, wage));
        }
    }

    /**
     * Method is used for simulate amount of worked product of person.
     * It simulates that person working tempo is changing during work.
     * @return amountOfProductPerTack
     */
    private int productPerTactCount(){
        Random random = new Random();
        int numberToCount = random.nextInt(3);
        int minusOrPlus = random.nextInt(2);
        if (minusOrPlus == 0) return productPerTact + numberToCount;
        else return productPerTact - numberToCount;
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


    public int getProductPerTact() {
        return productPerTact;
    }


    public void setProductPerTact(int productPerTact) {
        this.productPerTact = productPerTact;
    }
}
