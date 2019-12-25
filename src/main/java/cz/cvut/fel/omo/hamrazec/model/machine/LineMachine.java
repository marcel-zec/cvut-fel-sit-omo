package cz.cvut.fel.omo.hamrazec.model.machine;

import cz.cvut.fel.omo.hamrazec.model.production.Product;


public class LineMachine extends Machine {

    public LineMachine(int serialNumber, int yearOfManufacture, int productPerTack) {
        super(serialNumber, yearOfManufacture, productPerTack);
    }


    @Override
    public void work(Product product) {
        //TODO - state
        if (true){
//            if (random.nextInt(50) == 2) setState(new Broken());
            product.setCompleted(product.getCompleted() + productionShare);
            nextLineWorker.work(product);
        }
    }
}
