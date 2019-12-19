package cz.cvut.fel.omo.hamrazec;

import java.util.ArrayList;
import java.util.List;

public class EventList {

    private List<Event> eventList;
    private static EventList instance;

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
}
