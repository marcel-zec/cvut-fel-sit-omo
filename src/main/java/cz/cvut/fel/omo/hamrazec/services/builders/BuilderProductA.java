package main.java.cz.cvut.fel.omo.hamrazec.services.builders;

import main.java.cz.cvut.fel.omo.hamrazec.exceptions.CannotBuildLineException;
import main.java.cz.cvut.fel.omo.hamrazec.model.LineWorker;
import main.java.cz.cvut.fel.omo.hamrazec.model.production.ProductLine;

import java.util.ArrayList;
import java.util.List;


public class BuilderProductA extends LineBuilder {
    //TODO - builderA


    public BuilderProductA() {
        this.machines = 4;
        this.people = 2;
        this.robots = 3;
    }


    @Override
    public void setOrder() {


    }
}
