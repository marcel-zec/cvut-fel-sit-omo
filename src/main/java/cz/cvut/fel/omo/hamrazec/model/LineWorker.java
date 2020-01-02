package cz.cvut.fel.omo.hamrazec.model;

import cz.cvut.fel.omo.hamrazec.model.production.Product;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionLine;


public interface LineWorker extends FactoryWorker{

    /**
     * Method add product to list of product which wait for work at line worker.
     * @param product
     */
    void forWork(Product product);

    /**
     * Method empty list of product, that wait for work at line worker.
     */
    void emptyForWorkList();

    /**
     * Method simulate work of line worker.
     */
    void update();

    /**
     * Set production line to line worker.
     * @param line
     * @return line worker for fluent set
     */
    LineWorker setProductionLine(ProductionLine line);
    LineWorker getNextWorker();

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
