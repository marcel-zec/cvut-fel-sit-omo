package cz.cvut.fel.omo.hamrazec.model.production;

import java.util.ArrayList;
import java.util.List;

public class ProductionPlan {
    private List<ProductionSeries> endedSeries;
    private List<ProductionSeries> plan;

    public ProductionPlan() {
        this.endedSeries = new ArrayList<>();
        this.plan = new ArrayList<>();
    }

    public ProductionPlan(List<ProductionSeries> plan) {
        this.endedSeries = new ArrayList<>();
        this.plan = plan;
    }

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

    @Override
    public String toString() {
        return "Production plan [" + plan.size() + " planned series. " + endedSeries.size() + " ended series.] \n" + plan;
    }
}
