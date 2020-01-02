package cz.cvut.fel.omo.hamrazec.services;

import cz.cvut.fel.omo.hamrazec.controller.ProductionOperator;
import cz.cvut.fel.omo.hamrazec.model.events.Alert;
import cz.cvut.fel.omo.hamrazec.model.events.EndProduction;
import cz.cvut.fel.omo.hamrazec.model.events.EndRepair;
import cz.cvut.fel.omo.hamrazec.model.events.Event;
import cz.cvut.fel.omo.hamrazec.model.machine.Machine;
import cz.cvut.fel.omo.hamrazec.model.person.Repairman;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EventOperator implements Observer {

    private static EventOperator instance;
    private EventList eventList = EventList.getInstance();
    private RepairPool repairPool = RepairPool.getInstance();
    private List<Alert> alertList = new ArrayList<>();
    private Alert alertPrioritiest = null;
    private boolean wasPriority = false;
    private ProductionOperator productionOperator;
    private static final Logger LOG = Logger.getLogger(EventList.class.getName());


    private EventOperator() {
        eventList.attach(this);
    }

    public static EventOperator getInstance(){
        if (instance == null) {
            instance = new EventOperator();
        }
        return instance;
    }

    public ProductionOperator getProductionOperator() {
        return productionOperator;
    }

    public void setProductionOperator(ProductionOperator productionOperator) {
        this.productionOperator = productionOperator;
    }


    /**
     * If repairman is not available method add to alertList else method send event to method for setting repairman for repairing
     * @param event
     */
    private void processAlert(Alert event){

        if (alertList.size() != 0 && !wasPriority){
            alertList.add(event);
            LOG.warning("No repairman available.");
        }
        else goRepair(event);
    }

    private void processEndProduction(EndProduction event){
        productionOperator.endProduction(event.getLine());
    }


    /**
     * Method get repairman from pool and send him to repair machine from alert. If repairman is not available, add alert to list.
     * @param alert
     */
    private void goRepair(Alert alert){
        Repairman repairman = repairPool.getRepairman();
        if ( repairman != null)  {
            LOG.info("Repairman send for reparation. (" +repairman.getFirstName() + " " + repairman.getLastName() + ")");
            repairman.repair((Machine) alert.getSender());
        } else {
            alertList.add(alert);
            LOG.warning("No repairman available.");
        }
    }


    /**
     * Method pull alert from list and push alert to goRepair method, set Alert prioritiest and find out whether was prioritiest or oldest (wasPriority)
     * @param event
     */
    private void endRepair (EndRepair event){

        if (alertList.size() != 0){
            if (!wasPriority) {
                setAlertPrioritiest();
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


    /**
     * Method set Alert to prioritiest Alert from Alert list.
     */
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
        if (event.getClass() == EndProduction.class){
            processEndProduction((EndProduction) event);
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
