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
    protected double wage;
    protected int tact;
    protected EventList eventList;


    public Person() {
        this.eventList = EventList.getInstance();
        this.payCheckList =  new ArrayList<>();
    }


    public Person(String firstName, String lastName, double wage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.wage = wage;
        this.eventList = EventList.getInstance();
    }


    public List<PayCheck> getPayCheckList() {
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


    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }


    public void addPayCheck(PayCheck payCheck) {
        payCheckList.add(payCheck);
    }

}
