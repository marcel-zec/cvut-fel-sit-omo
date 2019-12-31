package cz.cvut.fel.omo.hamrazec.services.builders;

public class BuilderProductB extends LineBuilder {

    public BuilderProductB() {
        this.machinesAmount = 2;
        this.peopleAmount = 5;
        this.robotsAmount = 4;
    }


    @Override
    public void setOrder() {
        countShareInProduction(35,30,35);
        line.setFirstWorker(machineList.get(0))
                .setShareInProduction(machineShare)
                .setNextWorker(robotList.get(0))
                .setShareInProduction(robotShare)
                .setNextWorker(robotList.get(1))
                .setShareInProduction(robotShare)
                .setNextWorker(peopleList.get(0))
                .setShareInProduction(peopleShare)
                .setNextWorker(robotList.get(2))
                .setShareInProduction(robotShare)
                .setNextWorker(peopleList.get(1))
                .setShareInProduction(peopleShare)
                .setNextWorker(peopleList.get(2))
                .setShareInProduction(peopleShare)
                .setNextWorker(machineList.get(1))
                .setShareInProduction(machineShare)
                .setNextWorker(peopleList.get(3))
                .setShareInProduction(peopleShare)
                .setNextWorker(robotList.get(3))
                .setShareInProduction(robotShare)
                .setNextWorker(peopleList.get(4))
                .setShareInProduction(peopleShare)
                .setNextWorker(controllingRobot)
                .setShareInProduction(controlAmount);
    }


}
