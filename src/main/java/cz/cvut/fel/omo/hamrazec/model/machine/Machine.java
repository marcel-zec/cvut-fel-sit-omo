package cz.cvut.fel.omo.hamrazec.model.machine;

import cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.costs.CostStatement;
import cz.cvut.fel.omo.hamrazec.model.machine.state.State;
import cz.cvut.fel.omo.hamrazec.model.machine.state.Working;
import cz.cvut.fel.omo.hamrazec.model.person.Repairman;
import cz.cvut.fel.omo.hamrazec.model.production.Product;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionLine;
import cz.cvut.fel.omo.hamrazec.services.EventList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract public class Machine implements FactoryWorker, LineWorker {

    protected int tact;
    protected static int machineCounter = 0;
    protected final int serialNumber;
    protected int yearOfManufacture;
    protected int productionShare;
    protected int productPerTact;
    protected List<Product> productsForWork;
    protected LineWorker nextLineWorker;
    protected List<CostStatement> costStatementList;
    protected int depreciation;
    protected Random random;
    protected State state;
    protected Repairman repairingBy;
    protected EventList eventList;
    protected ProductionLine productionLine;
    protected int allWorkedProductAmount;

    public Machine(int yearOfManufacture, int productPerTact) {
        machineCounter++;
        this.serialNumber = machineCounter;
        this.yearOfManufacture = yearOfManufacture;
        this.productPerTact = productPerTact;
        this.depreciation = 0;
        this.random = new Random();
        this.productsForWork = new ArrayList<>();
        this.state = new Working(this);
        this.eventList = EventList.getInstance();
        this.allWorkedProductAmount = 0;
    }

    public int getDepreciation() {
        return depreciation;
    }

    protected void setDepreciation(int depreciation) {
        this.depreciation = depreciation;
    }

    public ProductionLine getProductionLine() {
        return productionLine;
    }

    @Override
    public LineWorker setProductionLine(ProductionLine productionLine) {
        this.productionLine = productionLine;
        return this;
    }

    public Repairman getRepairingBy() {
        return repairingBy;
    }

    public void setRepairingBy(Repairman repairingBy) {
        if (state.getClass() != Working.class) this.repairingBy = repairingBy;
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public EventList getEventList() {
        return eventList;
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
    public LineWorker getNextWorker(){
        return nextLineWorker;
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
        deprecation();

        if (productsForWork.isEmpty() || !state.canWork()){
            nextLineWorker.update();
        } else {
            for (int i = 0; i < Math.min(productPerTact,productsForWork.size()); i++) {
                nextLineWorker.forWork(workOnProduct(productsForWork.get(0)));
                allWorkedProductAmount++;
            }
            nextLineWorker.update();
        }
    }

    public void deprecationAfterRepair(){
        depreciation -= 10;
        if (depreciation < 0) depreciation = 0;
    }

    protected Product workOnProduct(Product product) {
        product.setCompleted(product.getCompleted() + getProductionShare());
        return product;
    }

    protected void deprecation(){
        if(allWorkedProductAmount % (productPerTact * 3) == 0){
            depreciation++;
        }
    }
}
