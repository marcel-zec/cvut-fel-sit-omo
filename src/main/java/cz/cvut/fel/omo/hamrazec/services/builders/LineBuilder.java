package main.java.cz.cvut.fel.omo.hamrazec.services.builders;

import main.java.cz.cvut.fel.omo.hamrazec.controller.ProductionOperator;
import main.java.cz.cvut.fel.omo.hamrazec.exceptions.BadPercentageException;
import main.java.cz.cvut.fel.omo.hamrazec.exceptions.NotEnoughWorkers;
import main.java.cz.cvut.fel.omo.hamrazec.model.LineWorker;
import main.java.cz.cvut.fel.omo.hamrazec.model.machine.ControllingRobot;
import main.java.cz.cvut.fel.omo.hamrazec.model.machine.LineMachine;
import main.java.cz.cvut.fel.omo.hamrazec.model.machine.LineRobot;
import main.java.cz.cvut.fel.omo.hamrazec.model.person.Worker;
import main.java.cz.cvut.fel.omo.hamrazec.model.production.ProductLine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

abstract public class LineBuilder implements Builder {
    protected ProductionOperator operator;
    protected ProductLine line;
    protected List<LineWorker> robotList;
    protected List<LineWorker> machineList;
    protected List<LineWorker> peopleList;
    protected LineWorker controllingRobot;
    protected int machinesAmount;
    protected int peopleAmount;
    protected int robotsAmount;
    protected int machineShare;
    protected int peopleShare;
    protected int robotShare;


    public LineBuilder() {
        operator = ProductionOperator.getInstance();
    }

    public List<LineWorker> getRobotList() {
        if (robotList == null) robotList = new ArrayList<>();
        return robotList;
    }

    protected void addRobot(LineWorker robot) {
        if (robotList == null) robotList = new ArrayList<>();
        robotList.add(robot);
    }

    public List<LineWorker> getMachineList() {
        if (machineList == null) machineList = new ArrayList<>();
        return machineList;
    }

    protected void addMachine(LineWorker machine) {
        if (machineList == null) machineList = new ArrayList<>();
        machineList.add(machine);
    }

    //TODO - ak peoplelist neexistuje tak vytvorit, a dorobit addPerson
    public List<LineWorker> getPeopleList() {
        return peopleList;
    }


    public LineWorker getControllingRobot() {
        return controllingRobot;
    }

    protected void setControllingRobot(LineWorker controllingRobot) {
        this.controllingRobot = controllingRobot;
    }

    protected void countShareInProduction(int robotPercentage, int machinePercentage, int peoplePercentage){
        if((robotPercentage + machinePercentage + peoplePercentage) > 100) throw new BadPercentageException();
        machineShare = machinePercentage / machinesAmount;
        robotShare = robotPercentage / robotShare;
        peopleShare = peoplePercentage / peopleAmount;
    }

    @Override
    public void createLine() {
        line = new ProductLine();
    }

    /**
     * Take a list of available workers from ProductionOperator, filter it by LineMachine class and
     * add available machines to own machine list.
     * @throws NotEnoughWorkers - when amount of available machines is smaller than amount of needed machines
     */
    @Override
    public void setMachines() throws NotEnoughWorkers {
        List<LineWorker> available = operator.getAvailableWorkers()
                .stream()
                .filter(worker -> worker.getClass() == LineMachine.class)
                .collect(Collectors.toList());

        if (available.size() > machinesAmount) {
            for (int i = 0; i < machinesAmount; i++) {
                addMachine(available.remove(i));
            }
        } else throw new NotEnoughWorkers();
    }

    /**
     * Take a list of available workers from ProductionOperator, filter it by Worker class and
     * add available workers to own people list.
     * @throws NotEnoughWorkers - when amount of available workers is smaller than amount of needed workers
     */
    @Override
    public void setPeople() throws NotEnoughWorkers {
        List<LineWorker> available = operator.getAvailableWorkers()
                .stream()
                .filter(worker -> worker.getClass() == Worker.class)
                .collect(Collectors.toList());

        if (available.size() > peopleAmount) {
            for (int i = 0; i < peopleAmount; i++) {
//                addPerson(available.remove(i));
            }
        } else throw new NotEnoughWorkers();
    }


    /**
     * Take a list of available workers from ProductionOperator, filter it by LineRobot class and
     * add available robots to own robot list.
     * @throws NotEnoughWorkers - when amount of available robots is smaller than amount of needed robots
     */
    @Override
    public void setRobots() throws NotEnoughWorkers {
        List<LineWorker> available = operator.getAvailableWorkers()
                .stream()
                .filter(worker -> worker.getClass() == LineRobot.class)
                .collect(Collectors.toList());

        if (available.size() > robotsAmount) {
            for (int i = 0; i < robotsAmount; i++) {
                addRobot(available.remove(i));
            }
        } else throw new NotEnoughWorkers();
    }

    /**
     * Take a list of available workers from ProductionOperator, filter it by ControllingRobot class and
     * add available controlling robot to line.
     * @throws NotEnoughWorkers - when no controlling robot is available
     */
    @Override
    public void setControl() throws NotEnoughWorkers {
        List<LineWorker> available = operator.getAvailableWorkers()
                .stream()
                .filter(worker -> worker.getClass() == ControllingRobot.class)
                .collect(Collectors.toList());

        if (available.size() > 0) {
            setControllingRobot(available.remove(0));
        } else throw new NotEnoughWorkers();
    }

    @Override
    public ProductLine getResult() {
        //TODO - nejaka metoda na zgrupenie do jedneho listu? ak nebude cas tak netreba
        line.addLineWorkers(machineList);
        line.addLineWorkers(robotList);
        line.addLineWorkers(peopleList);
        line.addLineWorkers(controllingRobot);
        operator.setWorkersToUse(machineList);
        operator.setWorkersToUse(robotList);
        operator.setWorkersToUse(peopleList);
        operator.setWorkersToUse(controllingRobot);
        return line;
    }

    @Override
    public void cancelBuilding() {
        operator.setWorkersToAvailable(machineList);
        operator.setWorkersToAvailable(peopleList);
        operator.setWorkersToAvailable(robotList);
        operator.setWorkersToAvailable(controllingRobot);
    }
}
