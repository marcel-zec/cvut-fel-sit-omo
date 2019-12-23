package main.java.cz.cvut.fel.omo.hamrazec.model.production;

import main.java.cz.cvut.fel.omo.hamrazec.exceptions.CannotBuildLineException;
import main.java.cz.cvut.fel.omo.hamrazec.exceptions.NotEnoughWorkers;
import main.java.cz.cvut.fel.omo.hamrazec.services.builders.Builder;

public class ProductionSeries implements BuilderDirector {
    private int amount;
    private Builder lineBuilder;
    private ProductFactory productFactory;
    private int priority;


    public ProductionSeries(int amount, int priority, Builder lineBuilder, ProductFactory productFactory) {
        this.amount = amount;
        this.priority = priority;
        this.lineBuilder = lineBuilder;
        this.productFactory = productFactory;
    }


    public int getPriority() {
        return priority;
    }


    public int getAmount() {
        return amount;
    }


    public void setAmount(int amount) {
        this.amount = amount;
    }


    public Builder getLineBuilder() {
        return lineBuilder;
    }


    public ProductFactory getProductFactory() {
        return productFactory;
    }


    @Override
    public ProductLine build() throws CannotBuildLineException {
        try {
            lineBuilder.setMachines();
            lineBuilder.setRobots();
            lineBuilder.setPeople();
            lineBuilder.setControl();
            lineBuilder.setOrder();
            return lineBuilder.getResult();
        } catch (NotEnoughWorkers e){
            lineBuilder.cancelBuilding();
            throw new CannotBuildLineException();
        }
    }
}
