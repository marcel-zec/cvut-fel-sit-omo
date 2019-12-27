package cz.cvut.fel.omo.hamrazec.model.machine;

import cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.costs.CostStatement;
import cz.cvut.fel.omo.hamrazec.model.machine.state.State;
import cz.cvut.fel.omo.hamrazec.model.machine.state.Working;
import cz.cvut.fel.omo.hamrazec.model.production.Product;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract public class Machine implements FactoryWorker, LineWorker {

    protected int tact;
    protected int serialNumber;
    protected int yearOfManufacture;
    protected int productionShare;
    protected int productPerTact;
    protected List<Product> productsForWork;
    protected LineWorker nextLineWorker;
    protected List<CostStatement> costStatementList;
    protected int depreciation;
    protected Random random;
    protected State state;

    public Machine(int serialNumber, int yearOfManufacture, int productPerTact) {
        this.serialNumber = serialNumber;
        this.yearOfManufacture = yearOfManufacture;
        this.productPerTact = productPerTact;
        this.depreciation = 0;
        this.random = new Random();
        this.productsForWork = new ArrayList<>();
        this.state = new Working(this);
    }

    public List<CostStatement> getCostStatementList() {
        if (costStatementList == null) costStatementList = new ArrayList<>();
        return costStatementList;
    }

    public int getProductionShare() {
        return productionShare;
    }

    public void setProductionShare(int productionShare) {
        this.productionShare = productionShare;
    }

    public LineWorker getNext() {
        return nextLineWorker;
    }

    public void setNext(LineWorker next) {
        this.nextLineWorker = next;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public int getProductPerTact() {
        return productPerTact;
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
    public LineWorker setNextWorker(LineWorker nextWorker) {
        this.nextLineWorker = nextWorker;
        return nextWorker;
    }

    @Override
    public LineWorker setShareInProduction(int shareInProduction) {
        productionShare = shareInProduction;
        return this;
    }

    @Override
    public void forWork(Product product){
        productsForWork.add(product);
    }

    @Override
    public void update() {
        if (productsForWork.isEmpty() || !state.canWork()){
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

    protected abstract Product workOnProduct(Product product);
}
