package cz.cvut.fel.omo.hamrazec.model.production;

public class FactoryProductC implements ProductFactory {
    @Override
    public Product getProduct() {

        return new ProductC();
    }
}
