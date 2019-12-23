package main.java.cz.cvut.fel.omo.hamrazec.model.production;

import main.java.cz.cvut.fel.omo.hamrazec.services.BuilderDirector;
import main.java.cz.cvut.fel.omo.hamrazec.services.builders.LineBuilder;

public class ProductionSeries implements BuilderDirector {
    private int amount;
    private LineBuilder lineBuilder;
    private ProductFactory productFactory;


    public ProductionSeries(int amount, LineBuilder lineBuilder, ProductFactory productFactory) {

        this.amount = amount;
        this.lineBuilder = lineBuilder;
        this.productFactory = productFactory;
    }


    public int getAmount() {

        return amount;
    }


    public void setAmount(int amount) {

        this.amount = amount;
    }


    public LineBuilder getLineBuilder() {

        return lineBuilder;
    }


    public ProductFactory getProductFactory() {

        return productFactory;
    }
}
