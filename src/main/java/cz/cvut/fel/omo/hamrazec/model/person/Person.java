package main.java.cz.cvut.fel.omo.hamrazec.model.person;

import main.java.cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import main.java.cz.cvut.fel.omo.hamrazec.model.events.EventSender;

public abstract class Person implements FactoryWorker, EventSender {

    protected String firstname;
    protected String lastname;


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
