package cz.cvut.fel.omo.hamrazec.services;

import cz.cvut.fel.omo.hamrazec.model.events.Event;

public interface Subject {

    public void attach(Observer observer);

    public void detach(Observer observer);

    public void notifyAllObservers(Event event);

}
