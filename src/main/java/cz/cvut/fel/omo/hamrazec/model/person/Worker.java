package main.java.cz.cvut.fel.omo.hamrazec.model.person;

import main.java.cz.cvut.fel.omo.hamrazec.model.LineWorker;
import main.java.cz.cvut.fel.omo.hamrazec.model.production.Product;

public class Worker extends Person implements LineWorker {
    public Worker(String firstname, String lastname, int wage) {
        super(firstname, lastname, wage);
    }

    @Override
    public int getTact() {
        return 0;
    }

    @Override
    public void updateTack(int tack) {

    }

    @Override
    public void work(Product product) {

    }
}
