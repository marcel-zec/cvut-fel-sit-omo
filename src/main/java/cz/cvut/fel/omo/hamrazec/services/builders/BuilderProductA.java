package main.java.cz.cvut.fel.omo.hamrazec.services.builders;

import main.java.cz.cvut.fel.omo.hamrazec.model.LineWorker;

public class BuilderProductA extends LineBuilder {
    //TODO - builderA


    public BuilderProductA() {
        this.machinesAmount = 4;
        this.peopleAmount = 2;
        this.robotsAmount = 3;
    }


    @Override
    public void setOrder() {
        robotList.get(0)
                .setShareInProduction(0)
                .setNextWorker(machineList.get(0))
                .setShareInProduction(0)
                .setNextWorker(machineList.get(1))
                .setShareInProduction(0)
                .setNextWorker(machineList.get(2))
                .setShareInProduction(0)
                .setNextWorker(peopleList.get(0))
                .setShareInProduction(0)
                .setNextWorker(robotList.get(1))
                .setShareInProduction(0)
                .setNextWorker(robotList.get(2))
                .setShareInProduction(0)
                .setNextWorker(machineList.get(3))
                .setShareInProduction(0)
                .setNextWorker(peopleList.get(1))
                .setShareInProduction(0)
                .setNextWorker(controllingRobot);
    }
}
