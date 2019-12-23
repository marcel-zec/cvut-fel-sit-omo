package main.java.cz.cvut.fel.omo.hamrazec.services.builders;

import main.java.cz.cvut.fel.omo.hamrazec.model.machine.WorkMachine;
import main.java.cz.cvut.fel.omo.hamrazec.model.person.Person;

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
