package cz.cvut.fel.omo.hamrazec.services.builders;

public class BuilderProductA extends LineBuilder {

    public BuilderProductA() {
        this.machinesAmount = 4;
        this.peopleAmount = 2;
        this.robotsAmount = 3;
    }


    @Override
    public void setOrder() {
        countShareInProduction(30,60,10);
        line.setFirstWorker(robotList.get(0))
                .setShareInProduction(robotShare)
                .setNextWorker(machineList.get(0))
                .setShareInProduction(machineShare)
                .setNextWorker(machineList.get(1))
                .setShareInProduction(machineShare)
                .setNextWorker(machineList.get(2))
                .setShareInProduction(machineShare)
                .setNextWorker(peopleList.get(0))
                .setShareInProduction(peopleShare)
                .setNextWorker(robotList.get(1))
                .setShareInProduction(robotShare)
                .setNextWorker(robotList.get(2))
                .setShareInProduction(robotShare)
                .setNextWorker(machineList.get(3))
                .setShareInProduction(machineShare)
                .setNextWorker(peopleList.get(1))
                .setShareInProduction(peopleShare)
                .setNextWorker(controllingRobot)
                .setShareInProduction(controlAmount);
    }
}
