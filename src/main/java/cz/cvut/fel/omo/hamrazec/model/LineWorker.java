package main.java.cz.cvut.fel.omo.hamrazec.model;

import main.java.cz.cvut.fel.omo.hamrazec.model.production.Product;

public interface LineWorker {
    void work(Product product);

    /**
     * Set next line worker in chain of responsibility in production line.
     * @param nextWorker
     * @return set worker
     */
    LineWorker setNextWorker(LineWorker nextWorker);

    /** //TODO - prelozit a dopisat
     * Set share in production to line workers. It is value for /prelozit percento opracovania/.
     * @param shareInProduction
     * @return itself
     */
    LineWorker setShareInProduction(int shareInProduction);
}
