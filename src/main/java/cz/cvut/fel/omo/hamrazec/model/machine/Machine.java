package main.java.cz.cvut.fel.omo.hamrazec.model.machine;

import main.java.cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import main.java.cz.cvut.fel.omo.hamrazec.model.LineWorker;
import main.java.cz.cvut.fel.omo.hamrazec.model.costs.CostStatement;

import java.util.ArrayList;
import java.util.List;

abstract public class Machine implements FactoryWorker, LineWorker {

    protected int tack;
    protected int serialNumber;
    protected int yearOfManufacture;
    protected int productionShare;
    protected int productPerTack;
    protected LineWorker nextLineWorker;
    protected List<CostStatement> costStatementList;

    public Machine(int serialNumber, int yearOfManufacture, int productPerTack) {
        this.serialNumber = serialNumber;
        this.yearOfManufacture = yearOfManufacture;
        this.productPerTack = productPerTack;
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

    public int getSerialNumber() {
        return serialNumber;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public int getProductPerTack() {
        return productPerTack;
    }

    @Override
    public int getTact() {
        return tack;
    }


    @Override
    public void updateTack(int tack) {
        this.tack = tack;
    }
}
