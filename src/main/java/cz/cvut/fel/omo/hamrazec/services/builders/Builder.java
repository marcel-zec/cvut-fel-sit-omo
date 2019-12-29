package cz.cvut.fel.omo.hamrazec.services.builders;

import cz.cvut.fel.omo.hamrazec.exceptions.NotEnoughWorkers;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionLine;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionSeries;

public interface Builder {
    void createLine();
    void setMachines() throws NotEnoughWorkers;
    void setPeople() throws NotEnoughWorkers;
    void setRobots() throws NotEnoughWorkers;
    void setOrder();
    void setControl() throws NotEnoughWorkers;
    void setSeries(ProductionSeries series);
    void setLine();
    void cancelBuilding();
    ProductionLine getResult();
}
