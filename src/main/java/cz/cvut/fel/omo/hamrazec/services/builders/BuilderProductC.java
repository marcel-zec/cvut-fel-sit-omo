package cz.cvut.fel.omo.hamrazec.services.builders;

import cz.cvut.fel.omo.hamrazec.exceptions.CannotBuildLineException;
import cz.cvut.fel.omo.hamrazec.model.production.ProductLine;

public class BuilderProductC extends LineBuilder {


    public BuilderProductC() {
        this.machinesAmount = 2;
        this.peopleAmount = 1;
        this.robotsAmount = 2;
    }


    @Override
    public void setOrder() {
        countShareInProduction(50,35,15);
        peopleList.get(0)
                .setShareInProduction(peopleShare)
                .setNextWorker(machineList.get(0))
                .setShareInProduction(machineShare)
                .setNextWorker(robotList.get(0))
                .setShareInProduction(robotShare)
                .setNextWorker(machineList.get(1))
                .setShareInProduction(machineShare)
                .setNextWorker(robotList.get(1))
                .setShareInProduction(robotShare)
                .setNextWorker(controllingRobot);
    }
}
