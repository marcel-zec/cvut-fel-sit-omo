package main.java.cz.cvut.fel.omo.hamrazec.services.builders;

import main.java.cz.cvut.fel.omo.hamrazec.exceptions.CannotBuildLineException;
import main.java.cz.cvut.fel.omo.hamrazec.model.production.ProductLine;

public class BuilderProductC extends LineBuilder {
    //TODO - builderC
    @Override
    public void setMachines() throws CannotBuildLineException {

        throw new CannotBuildLineException();
    }


    @Override
    public void setPeople() throws CannotBuildLineException {

        throw new CannotBuildLineException();
    }


    @Override
    public void setRobots() throws CannotBuildLineException {

        throw new CannotBuildLineException();

    }


    @Override
    public void setOrder() throws CannotBuildLineException {

        throw new CannotBuildLineException();

    }


    @Override
    public ProductLine getResult() throws CannotBuildLineException {

        throw new CannotBuildLineException();
    }
}
