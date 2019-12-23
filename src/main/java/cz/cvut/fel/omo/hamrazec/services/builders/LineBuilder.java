package main.java.cz.cvut.fel.omo.hamrazec.services.builders;

import main.java.cz.cvut.fel.omo.hamrazec.model.production.ProductLine;

public interface LineBuilder {
    void setMachines();
    void setPeople();
    void setRobots();
    void setOrder();
    ProductLine getResult();
}
