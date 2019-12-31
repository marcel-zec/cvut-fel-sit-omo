package cz.cvut.fel.omo.hamrazec.model.person;

import cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import cz.cvut.fel.omo.hamrazec.model.costs.PayCheck;
import cz.cvut.fel.omo.hamrazec.services.EventList;

import java.util.ArrayList;
import java.util.List;

public abstract class Person implements FactoryWorker {

    protected String firstName;
    protected String lastName;
    protected List<PayCheck> payCheckList;
    protected int wage;
    protected int tact;
    protected EventList eventList;


    public Person() {
    }


    public Person(String firstName, String lastName, int wage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.wage = wage;
        this.eventList = EventList.getInstance();
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
        return tact;
    }

    @Override
    public void updateTact(int tact) {
        this.tact = tact;
    }


    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }
}
