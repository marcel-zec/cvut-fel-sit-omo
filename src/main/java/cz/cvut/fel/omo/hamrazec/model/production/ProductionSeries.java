package cz.cvut.fel.omo.hamrazec.model.production;

import cz.cvut.fel.omo.hamrazec.exceptions.CannotBuildLineException;
import cz.cvut.fel.omo.hamrazec.exceptions.NotEnoughWorkers;
import cz.cvut.fel.omo.hamrazec.model.Visitable;
import cz.cvut.fel.omo.hamrazec.model.VisitorDirector;
import cz.cvut.fel.omo.hamrazec.services.builders.Builder;

/**
 * Instance of production series contains amount and of product and priority of series.
 * Also contains line builder that it build production line suitable for this series.
 * Last attributes reference to product factory that it return correct product for this series.
 */
public class ProductionSeries implements BuilderDirector, Visitable {
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


    /**
     * Method called line builder methods to build production line.
     * Or cancel building line when not enough workers are available.
     * @return production line
     * @throws CannotBuildLineException when some building method failed
     */
    @Override
    public ProductionLine build() throws CannotBuildLineException {
        try {
            lineBuilder.createLine();
            lineBuilder.setMachines();
            lineBuilder.setRobots();
            lineBuilder.setPeople();
            lineBuilder.setControl();
            lineBuilder.setOrder();
            lineBuilder.setSeries(this);
            lineBuilder.setLine();
            lineBuilder.putNewProductsForProduction();
            return lineBuilder.getResult();
        } catch (NotEnoughWorkers e) {
            lineBuilder.cancelBuilding();
            throw new CannotBuildLineException();
        }
    }

    @Override
    public String toString() {
        return productFactory.getProduct().getClass().getSimpleName() + " of amount " + amount + " with priority " + priority;
    }

    @Override
    public void accept(VisitorDirector visitor) {
        visitor.visit(this);
    }
}
