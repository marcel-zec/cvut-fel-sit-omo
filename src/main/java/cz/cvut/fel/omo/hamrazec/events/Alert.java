package main.java.cz.cvut.fel.omo.hamrazec.events;

public class Alert extends Event {

    private int priority;


    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
