package cz.cvut.fel.omo.hamrazec.model.person;

import cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import cz.cvut.fel.omo.hamrazec.model.costs.PayCheck;

import java.util.ArrayList;
import java.util.List;

public abstract class Person implements FactoryWorker {

    protected String firstName;
    protected String lastName;
    protected List<PayCheck> payCheckList;
    protected int wage;
    protected int tack;


    public Person(String firstName, String lastName, int wage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.wage = wage;
    }


    public List<PayCheck> getPayCheckList() {
        if (payCheckList == null) payCheckList = new ArrayList<>();
        return payCheckList;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
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
