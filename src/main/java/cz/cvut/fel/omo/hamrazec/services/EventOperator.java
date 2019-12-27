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
    private boolean wasPriority = false;
    private Repairman repairman;


    private EventOperator() {
        eventList.attach(this);
    }

    public static EventOperator getInstance(){
        if (instance == null) {
            instance = new EventOperator();
        }
        return instance;
    }


    private void processAlert(Alert event){

        if (alertList.size() != 0 && !wasPriority){
            setAlertPrioritiest();
        }
        else goRepair(event);
    }

    private void goRepair(Alert alert){

        repairman = repairPool.getRepairman();
        if ( repairman != null)  {
            repairman.repair(alert.getSender());
            repairman = null;
            return;
        }
        else {
            alertList.add(alert);
        }
    }

    private void endRepair (EndRepair event ){

        if (alertList.size() != 0){
            if (!wasPriority) {
                wasPriority = true;
                alertList.remove(alertPrioritiest);
                goRepair(alertPrioritiest);
                alertPrioritiest = null;
            }
            else {
                goRepair(alertList.remove(0));
                setAlertPrioritiest();
                wasPriority = false;
            }
        }

    }

    private void setAlertPrioritiest(){

        if (alertList.size() > 0) {
            for (Alert alert : alertList) {
                if (alertPrioritiest == null) alertPrioritiest = alert;
                if (alertPrioritiest.getPriority() < alert.getPriority()) alertPrioritiest = alert;
            }
        }
    }

    @Override
    public void update(Event event) {

        if (event.getClass() == Alert.class) {
            processAlert((Alert) event);
        }
        if (event.getClass() == EndRepair.class){
            repairPool.putRepairman((Repairman) event.getSender());
            endRepair((EndRepair) event);
        }

    }

    public List<Alert> getAlertList() {
        return alertList;
    }
}
