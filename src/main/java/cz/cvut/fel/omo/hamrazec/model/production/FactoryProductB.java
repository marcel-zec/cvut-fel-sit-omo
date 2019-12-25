package cz.cvut.fel.omo.hamrazec.model.production;

public class FactoryProductB implements ProductFactory {
    @Override
    public Product getProduct() {

        return new ProductB();
    }
}
