package main.java.cz.cvut.fel.omo.hamrazec.model.person;

import main.java.cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import main.java.cz.cvut.fel.omo.hamrazec.model.costs.PayCheck;

import java.util.ArrayList;
import java.util.List;

public abstract class Person implements FactoryWorker {

    protected String firstname;
    protected String lastname;
    protected List<PayCheck> payCheckList;
    protected int wage;
    protected int tack;


    public Person(String firstname, String lastname, int wage) {

        this.firstname = firstname;
        this.lastname = lastname;
        this.wage = wage;
    }


    public List<PayCheck> getPayCheckList() {
        if (payCheckList == null) payCheckList = new ArrayList<>();
        return payCheckList;
    }


    public int getTack() {

        return tack;
    }


    public String getFirstname() {

        return firstname;
    }


    public void setFirstname(String firstname) {

        this.firstname = firstname;
    }


    public String getLastname() {

        return lastname;
    }


    public void setLastname(String lastname) {

        this.lastname = lastname;
    }

}
