package main.java.cz.cvut.fel.omo.hamrazec.model.machine;

import main.java.cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import main.java.cz.cvut.fel.omo.hamrazec.model.LineWorker;

abstract public class Machine implements FactoryWorker {

    protected int tack;
    protected int SerialNumber;
    protected int YearOfManufacture;


    public Machine(int serialNumber, int yearOfManufacture) {

        SerialNumber = serialNumber;
        YearOfManufacture = yearOfManufacture;
    }


    public int getSerialNumber() {

        return SerialNumber;
    }


    public int getYearOfManufacture() {

        return YearOfManufacture;
    }


    @Override
    public int getTact() {
        return tack;
    }


    @Override
    public void updateTack(int tack) {
        this.tack = tack;
    }
}
