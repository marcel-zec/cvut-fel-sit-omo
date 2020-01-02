package cz.cvut.fel.omo.hamrazec.model.costs;

public class PayCheck {
    private int tact;
    private double wage;


    public int getTact() {
        return tact;
    }


    public double getWage() {
        return wage;
    }


    public PayCheck(int tact, double wage) {

        this.tact = tact;
        this.wage = wage;
    }
}
