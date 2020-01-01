package cz.cvut.fel.omo.hamrazec.services;

import cz.cvut.fel.omo.hamrazec.model.machine.ControllingRobot;
import cz.cvut.fel.omo.hamrazec.model.machine.LineMachine;
import cz.cvut.fel.omo.hamrazec.model.machine.LineRobot;

import java.util.Random;

public class MachineGenerator {
    int productPerTact;
    double kwPerTack;
    double oilPerTack;
    double petrolPerTack;
    double materialPerProduct;

    Random random = new Random();

    public LineMachine generateMachine(){
        generateMachineValues(false);
        LineMachine lineMachine = new LineMachine(2000 + random.nextInt(9), productPerTact, kwPerTack,oilPerTack,petrolPerTack,materialPerProduct);
        return lineMachine;
    }

    public LineRobot generateRobot(){
        generateMachineValues(true);
        LineRobot lineRobot = new LineRobot(2000 + random.nextInt(9), productPerTact, kwPerTack,oilPerTack,petrolPerTack,materialPerProduct);
        return lineRobot;
    }

    public ControllingRobot generateControlRobot(){
        generateControlRobotValues();
        ControllingRobot controllingRobot = new ControllingRobot(2000 + random.nextInt(9), productPerTact, kwPerTack,oilPerTack,petrolPerTack,materialPerProduct);
        return controllingRobot;
    }

    private void generateMachineValues(boolean robot){
        productPerTact = random.nextInt((12 - 2) + 1) + 2; //nextInt(max - min + 1) + min;
        if (robot) kwPerTack = Math.round(random.nextDouble()* 3 * 100.0) / 100.0;
        else kwPerTack = Math.round(random.nextDouble() * 100.0) / 100.0;

        oilPerTack = Math.round(random.nextDouble() * random.nextInt(5) * 100.0) / 100.0;
        petrolPerTack = Math.round(random.nextDouble() * random.nextInt(3) * 100.0) / 100.0;
        materialPerProduct = Math.round(random.nextDouble() * random.nextInt(4) * 100.0) / 100.0;
    }

    private void generateControlRobotValues(){
        productPerTact = random.nextInt((8 - 4) + 1) + 4; //nextInt(max - min + 1) + min;
        kwPerTack = Math.round(random.nextDouble() * 3 * 100.0) / 100.0;
        oilPerTack = Math.round(random.nextDouble() * random.nextInt(8) * 100.0) / 100.0;
        petrolPerTack = Math.round(random.nextDouble() * random.nextInt(2) * 100.0) / 100.0;
        materialPerProduct = 0;
    }


}
