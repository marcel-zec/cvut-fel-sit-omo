package main.java.cz.cvut.fel.omo.hamrazec.services.builders;

import main.java.cz.cvut.fel.omo.hamrazec.exceptions.CannotBuildLineException;
import main.java.cz.cvut.fel.omo.hamrazec.model.production.ProductLine;

public interface LineBuilder {
    void setMachines() throws CannotBuildLineException;
    void setPeople() throws CannotBuildLineException;
    void setRobots() throws CannotBuildLineException;
    void setOrder() throws CannotBuildLineException;
    ProductLine getResult() throws CannotBuildLineException;
}
