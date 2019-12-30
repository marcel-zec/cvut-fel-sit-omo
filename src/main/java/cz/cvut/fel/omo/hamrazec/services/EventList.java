package cz.cvut.fel.omo.hamrazec.services;

import cz.cvut.fel.omo.hamrazec.controller.ProductionOperator;
import cz.cvut.fel.omo.hamrazec.model.events.*;
import cz.cvut.fel.omo.hamrazec.model.machine.Machine;
import cz.cvut.fel.omo.hamrazec.model.person.Repairman;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionLine;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EventList implements Subject{

    private List<Event> eventList;
    private static EventList instance;
    private List<Observer> observers = new ArrayList<Observer>();
    private static final Logger LOG = Logger.getLogger(ProductionOperator.class.getName());


    private EventList(){
        eventList = new ArrayList<Event>();
    }

    public static EventList getInstance() {
        if (instance == null) {
            instance = new EventList();
        }
        return instance;
    }


    public List<Event> getEventList() {
        return eventList;
    }

    public void receive(Event event){
        if (event.getClass() == Alert.class){
            Machine machine = (Machine) event.getSender();
            LOG.warning("Machine broke down. (machine serial number: " + machine.getSerialNumber() +")" );
        } else if(event.getClass() == EndRepair.class){
            EndRepair endRepairEvent = (EndRepair) event;
            Machine machine = (Machine) endRepairEvent.getRepaired();
            Repairman repairman = (Repairman) event.getSender();
            LOG.info("Repair ended. (machine serial number: " + machine.getSerialNumber() +
                    ", repairman: " + repairman.getFirstName() + " " + repairman.getLastName() + ")");
        } else if(event.getClass() == StartRepair.class){
            StartRepair endRepairEvent = (StartRepair) event;
            Machine machine = (Machine) endRepairEvent.getRepairing();
            Repairman repairman = (Repairman) event.getSender();
            LOG.info("Repair started. (repairman: " + repairman.getFirstName() + " " + repairman.getLastName()
                    + ", machine serial number: " + machine.getSerialNumber() + ")");
        } else if(event.getClass() == EndProduction.class){
            EndProduction endProductionEvent = (EndProduction) event;
            ProductionSeries series = endProductionEvent.getSeries();
            ProductionLine line = endProductionEvent.getLine();
            LOG.info("Production ended. (line serial number: " + line.getSerialNumber() + ")");
        }
        eventList.add(event);
        notifyAllObservers(event);
    }


    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }


    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }


    @Override
    public void notifyAllObservers(Event event) {

        for (Observer observer: observers) {
            observer.update(event);
        }
    }
}
