package cz.cvut.fel.omo.hamrazec.model.machine.state;

import cz.cvut.fel.omo.hamrazec.model.events.Alert;
import cz.cvut.fel.omo.hamrazec.model.machine.Machine;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionLine;

import java.util.Random;

public class Working extends State {
    private Random random;

    public Working(Machine context) {
        super(context);
        random = new Random();
    }

    /**
     * According to the size of depreciation of machine simulate machine damage.
     * @return true when machine works, false when machine broke
     */
    @Override
    public boolean canWork() {
        int deprecation = context.getDepreciation();
        if (deprecation < 20){
            if (random.nextInt(70) == 3) {
                brokeMachine();
                return false;
            }
        } else if (deprecation < 40){
            if (random.nextInt(60) == 3) {
                brokeMachine();
                return false;
            }
        } else if (deprecation < 60){
            if (random.nextInt(50) == 3) {
                brokeMachine();
                return false;
            }
        } else if (deprecation < 80){
            if (random.nextInt(40) == 3) {
                brokeMachine();
                return false;
            }
        } else if (deprecation < 100){
            if (random.nextInt(20) == 3) {
                brokeMachine();
                return false;
            }
        } else {
            brokeMachine();
            return false;
        }
        return true;
    }

    /**
     * Method set state of machine to Broken and send alert event to eventlist.
     */
    private void brokeMachine(){
        context.setState(new Broken(context));
        context.getEventList().receive(new Alert(context.getProductionLine().getPriority(), context));
    }
}
