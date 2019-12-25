package cz.cvut.fel.omo.hamrazec.services;

import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.events.Alert;
import cz.cvut.fel.omo.hamrazec.model.events.EndRepair;
import cz.cvut.fel.omo.hamrazec.model.events.Event;
import cz.cvut.fel.omo.hamrazec.model.person.Repairman;

import java.util.ArrayList;
import java.util.List;

public class EventOperator implements Observer {

    private static EventOperator instance;
    private EventList eventList = EventList.getInstance();
    private RepairPool repairPool = RepairPool.getInstance();
    private List<Alert> alertList = new ArrayList<>();
    private Alert alertPrioritiest = null;
    private Repairman repairman;


    private EventOperator() {

    }

    public static EventOperator getInstance(){
        if (instance == null) {
            instance = new EventOperator();
        }
        return instance;
    }


    private void goRepair(Event event){

        if (alertList.size() == 0 && event.getClass() == Alert.class) {
            repairman = repairPool.getRepairman();
            if ( repairman != null)  {
                repairman.repair(event.getSender());
            }
            else alertList.add((Alert) event);
        }
        if (event.getClass() == EndRepair.class && alertList.size() != 0){
            if (alertPrioritiest != null) {
                alertList.remove(alertPrioritiest);
                goRepair(alertPrioritiest);
            }
            else {
                goRepair(alertList.remove(0));
                setAlertPrioritiest();
            }
        }
        if (event.getClass() == Alert.class && alertPrioritiest!=null){
            repairman = repairPool.getRepairman();
            repairman.repair(event.getSender());
            alertPrioritiest = null;
        }
        repairman = null;

    }


    private void setAlertPrioritiest(){

        for (Alert alert: alertList) {
            if (alertPrioritiest == null) alertPrioritiest = alert;
            if (alertPrioritiest.getPriority() < alert.getPriority()) alertPrioritiest = alert;
        }
    }

    @Override
    public void update(Event event) {

        if (event.getClass() == Alert.class) {
            goRepair(event);
        }
        if (event.getClass() == EndRepair.class){
            repairPool.putRepairman((Repairman) event.getSender());
            goRepair(event);
        }

    }

    public List<Alert> getAlertList() {
        return alertList;
    }
}
