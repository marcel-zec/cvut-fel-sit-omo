package cz.cvut.fel.omo.hamrazec;

public class EventOperator extends Person {


    private EventList eventList = EventList.getInstance();
    private RepairPool repairPool = RepairPool.getInstance();
    private Alert alertOldest = null;
    private Alert alertPrioritiest = null;

    public void goRepair(){

        for (Event event: eventList.getEventList()) {
            if (event.getClass() == Alert.class){
                if (alertOldest == null) alertOldest = (Alert) event;
                if (alertOldest.getPriority() < ((Alert) event).getPriority() ) alertPrioritiest = (Alert) event;
            }
        }

        //posli repairPool.getRepairman(); opravit stroj
    }
}
