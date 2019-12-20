package main.java.cz.cvut.fel.omo.hamrazec.model.machine;

import main.java.cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import main.java.cz.cvut.fel.omo.hamrazec.model.LineWorker;
import main.java.cz.cvut.fel.omo.hamrazec.model.events.EventSender;

public abstract class AbstractMachine implements LineWorker, FactoryWorker, EventSender{

    protected int SerialNumber;
    protected int YearOfManufacture;
}
