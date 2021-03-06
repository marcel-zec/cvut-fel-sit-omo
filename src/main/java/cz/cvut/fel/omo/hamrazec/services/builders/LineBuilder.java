package cz.cvut.fel.omo.hamrazec.services.builders;

import cz.cvut.fel.omo.hamrazec.controller.ProductionOperator;
import cz.cvut.fel.omo.hamrazec.exceptions.BadPercentageException;
import cz.cvut.fel.omo.hamrazec.exceptions.NotEnoughWorkers;
import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.machine.ControllingRobot;
import cz.cvut.fel.omo.hamrazec.model.machine.LineMachine;
import cz.cvut.fel.omo.hamrazec.model.machine.LineRobot;
import cz.cvut.fel.omo.hamrazec.model.person.Worker;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionLine;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

abstract public class LineBuilder implements Builder {
    protected ProductionOperator operator;
    protected ProductionLine line;
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
    protected int controlAmount;


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

    public List<LineWorker> getPeopleList() {
        if (peopleList == null) peopleList = new ArrayList<>();
        return peopleList;
    }

    protected void addPerson(LineWorker person){
        if (peopleList == null) peopleList = new ArrayList<>();
        peopleList.add(person);
    }


    public LineWorker getControllingRobot() {
        return controllingRobot;
    }

    protected void setControllingRobot(LineWorker controllingRobot) {
        this.controllingRobot = controllingRobot;
    }

    /**
     * Count production share for line workers in production line and set is to builder attributes.
     * Count also control amount for controlling robot.
     * @param robotPercentage
     * @param machinePercentage
     * @param peoplePercentage
     */
    protected void countShareInProduction(int robotPercentage, int machinePercentage, int peoplePercentage){
        if((robotPercentage + machinePercentage + peoplePercentage) != 100) throw new BadPercentageException();
        machineShare = machinePercentage / machinesAmount;
        robotShare = robotPercentage / robotsAmount;
        peopleShare = peoplePercentage / peopleAmount;
        controlAmount = (machinesAmount * machineShare) + (robotsAmount * robotShare) + (peopleAmount * peopleShare);
    }

    /**
     * Creates new production line.
     */
    @Override
    public void createLine() {
        line = new ProductionLine();
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

        if (available.size() >= machinesAmount) {
            for (int i = 0; i < machinesAmount; i++) {
                addMachine(available.remove(0));
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

        if (available.size() >= peopleAmount) {
            for (int i = 0; i < peopleAmount; i++) {
                addPerson(available.remove(0));
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

        if (available.size() >= robotsAmount) {
            for (int i = 0; i < robotsAmount; i++) {
                addRobot(available.remove(0));
            }
        } else throw new NotEnoughWorkers();
    }

    /**
     * Take a list of available workers from ProductionOperator, filter it by ControllingRobot class and
     * add available controlling robot to line. Also set amount of product that robot control in production.
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
            ControllingRobot robot = (ControllingRobot) controllingRobot;
        } else throw new NotEnoughWorkers();
    }

    /**
     * Put line workers to production line and remove them from available workers.
     * @return production line
     */
    @Override
    public ProductionLine getResult() {
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


    /**
     * Set attribute production line to line workers in this line.
     */
    @Override
    public void setLine(){
        LineWorker worker = line.getFirstWorker().setProductionLine(line);
        while (true){
            if( worker.getNextWorker() != null) worker = worker.getNextWorker().setProductionLine(line);
            else return;
        }

    }


    @Override
    public void setSeries(ProductionSeries series){
        line.setSeries(series);
    }

    /**
     * Put new products to first line worker from product factory.
     */
    @Override
    public void putNewProductsForProduction(){
        for (int i = 0; i < line.getSeries().getAmount(); i++) {
            line.getFirstWorker().forWork(line.getSeries().getProductFactory().getProduct());
        }
    }


    /**
     * Method reset line workers lists when built is not successful.
     */
    @Override
    public void cancelBuilding() {
        if (line.getFirstWorker() != null) line.getFirstWorker().emptyForWorkList();
        if (machineList != null) machineList = null;
        if (peopleList != null) peopleList = null;
        if (robotList != null) robotList = null;
        if (controllingRobot != null) controllingRobot = null;
    }
}
