package cz.cvut.fel.omo.hamrazec.services;

import cz.cvut.fel.omo.hamrazec.model.events.Event;

public interface Observer {

    public void update(Event event);
}
