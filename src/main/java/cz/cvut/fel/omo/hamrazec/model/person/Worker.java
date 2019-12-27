package cz.cvut.fel.omo.hamrazec.model.person;

import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.Visitor;
import cz.cvut.fel.omo.hamrazec.model.production.Product;

public class Worker extends Person implements LineWorker {
    public Worker(String firstname, String lastname, int wage) {
        super(firstname, lastname, wage);
    }

    @Override
    public void forWork(Product product) {

    }

    @Override
    public void update() {

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
    public int getTact() {
        return 0;
    }

    @Override
    public void updateTact(int tact) {

    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }


    @Override
    public String toString() {

        return "Worker{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", wage=" + wage +
                '}';
    }
}
