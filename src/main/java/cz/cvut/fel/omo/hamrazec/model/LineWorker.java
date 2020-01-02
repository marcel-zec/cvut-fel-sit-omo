package cz.cvut.fel.omo.hamrazec.model;

import cz.cvut.fel.omo.hamrazec.model.production.Product;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionLine;


public interface LineWorker extends FactoryWorker{
    void forWork(Product product);
    void emptyForWorkList();
    void update();
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
    int getProductPerTact();

}
