package main.java.cz.cvut.fel.omo.hamrazec.services.builders;

import main.java.cz.cvut.fel.omo.hamrazec.model.LineWorker;
import main.java.cz.cvut.fel.omo.hamrazec.model.machine.ControllingRobot;
import main.java.cz.cvut.fel.omo.hamrazec.model.machine.Machine;
import main.java.cz.cvut.fel.omo.hamrazec.model.machine.WorkRobot;
import main.java.cz.cvut.fel.omo.hamrazec.model.person.Person;

import java.util.List;
import java.util.stream.Collectors;

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
