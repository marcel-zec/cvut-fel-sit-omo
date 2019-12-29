package cz.cvut.fel.omo.hamrazec.services;

import cz.cvut.fel.omo.hamrazec.controller.ProductionOperator;
import cz.cvut.fel.omo.hamrazec.model.events.Alert;
import cz.cvut.fel.omo.hamrazec.model.events.Event;

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
            LOG.warning("Machine with serial number ");
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
