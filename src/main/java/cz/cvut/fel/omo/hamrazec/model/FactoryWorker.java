package cz.cvut.fel.omo.hamrazec.model;

import java.math.BigInteger;

public interface FactoryWorker extends Visitable{
    int getTact();
    void updateTact(int tact);
}
