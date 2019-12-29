package cz.cvut.fel.omo.hamrazec.model.person;

import cz.cvut.fel.omo.hamrazec.controller.Factory;
import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.Visitable;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionSeries;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DirectorIterator implements cz.cvut.fel.omo.hamrazec.Iterator {

    Factory factory = Factory.getInstance();
    Visitable context = factory.getProductionOperator();
    List<Visitable> list = new ArrayList<>();
    List<ProductionSeries> psList = new ArrayList<>();
    boolean first = true;
    int counter = 0;
    int listNumber = 0;

    public DirectorIterator() throws IOException {

    }


    public boolean hasNext() {
        if (first) {
            return true;
        }
        if (factory.getProductionOperator().getPlan() != null && counter == 1) return true;
        else if (factory.getProductionOperator().getPlan().getPlan().size() >0 && counter == 2){
            if (listNumber < factory.getProductionOperator().getPlan().getPlan().size()) return true;
        }
        else if (factory.getFactoryWorkers().size()>0 && counter==3) {
            if (listNumber < factory.getFactoryWorkers().size()) return true;
        }
        return false;
    }

    public Visitable next() {

        if (first) {
            first = false;
            counter++;
            return context;
        }
        else {
            switch (counter){
                case 1:
                    context = factory.getProductionOperator().getPlan();
                    counter++;
                    break;
                case 2:
                    psList =  factory.getProductionOperator().getPlan().getPlan();
                    if (psList.size()>0) {
                        context = psList.get(listNumber);
                        if (psList.size()-1 == listNumber){
                           listNumber = 0;
                           counter++;
                           break;
                        }
                        listNumber++;
                    }
                    break;
                case 3:
                    list = factory.getFactoryWorkers();
                    context = list.get(listNumber);
                    listNumber++;
                    break;
            }
        }

        return context;
    }
}
