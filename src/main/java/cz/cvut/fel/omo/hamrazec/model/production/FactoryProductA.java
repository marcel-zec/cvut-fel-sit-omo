package cz.cvut.fel.omo.hamrazec.model.production;

public class FactoryProductA implements ProductFactory {
    @Override
    public Product getProduct() {

        return new ProductA();
    }
}
