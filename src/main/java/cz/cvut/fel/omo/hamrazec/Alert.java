package cz.cvut.fel.omo.hamrazec;

public class Alert extends Event {

    private int priority;


    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
