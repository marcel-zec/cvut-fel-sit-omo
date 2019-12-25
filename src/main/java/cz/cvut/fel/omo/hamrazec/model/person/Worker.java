package cz.cvut.fel.omo.hamrazec.model.person;

import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.production.Product;

public class Worker extends Person implements LineWorker {
    public Worker(String firstname, String lastname, int wage) {
        super(firstname, lastname, wage);
    }

    @Override
    public LineWorker setNextWorker(LineWorker nextWorker) {
        return null;
    }

    @Override
    public LineWorker setShareInProduction(int shareInProduction) {
        return null;
    }

    @Override
    public void work(Product product) {

    }
}
