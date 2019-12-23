package main.java.cz.cvut.fel.omo.hamrazec.model.production;

import java.util.List;

public class ProductionPlan {
    private List<ProductionSeries> endedSeries;
    private List<ProductionSeries> plan;


    public List<ProductionSeries> getEndedSeries() {

        return endedSeries;
    }


    public List<ProductionSeries> getPlan() {

        return plan;
    }


    public void setPlan(List<ProductionSeries> plan) {

        this.plan = plan;
    }

    public void addSeries(ProductionSeries series){
        plan.add(series);
    }
}
