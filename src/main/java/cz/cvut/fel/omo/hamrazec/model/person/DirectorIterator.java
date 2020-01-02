package cz.cvut.fel.omo.hamrazec.model.person;

import cz.cvut.fel.omo.hamrazec.controller.Factory;
import cz.cvut.fel.omo.hamrazec.model.Visitable;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionLine;
import cz.cvut.fel.omo.hamrazec.model.production.ProductionSeries;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class DirectorIterator implements Iterator {

    private Factory factory = Factory.getInstance();
    private Visitable context = factory.getProductionOperator();
    private boolean first = true;
    private int counter = 0;
    private int listNumber = 0;

    public DirectorIterator() throws IOException {
    }

    public boolean hasNext() {
        if (first) {
            return true;
        }
        if (factory.getProductionOperator().getPlan() != null && counter == 1) return true;
        else if (factory.getProductionOperator().getPlan().getPlan().size() >0 && counter <= 2){
            if (listNumber < factory.getProductionOperator().getPlan().getPlan().size()){
                counter=2;
                return true;
            }
        }
        else if (factory.getProductionOperator().getActiveLines().size()>0 && counter<=3){
            if (listNumber < factory.getProductionOperator().getActiveLines().size()) {
                counter=3;
                return true;
            }
        }
        else if (factory.getVisitableWorkers().size()>0 && counter<=4) {
            if (listNumber < factory.getVisitableWorkers().size()) {
                counter=4;
                return true;
            }
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
                    List<ProductionSeries> psList = factory.getProductionOperator().getPlan().getPlan();
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
                    List<ProductionLine> lineList = factory.getProductionOperator().getActiveLines();
                    if (lineList.size()>0) {
                        context = lineList.get(listNumber);
                        if (lineList.size() - 1 == listNumber) {
                            listNumber = 0;
                            counter++;
                            break;
                        }
                        listNumber++;
                    }
                    break;
                case 4:
                    List<Visitable> list = factory.getVisitableWorkers();
                    context = list.get(listNumber);
                    listNumber++;
                    break;
            }
        }

        return context;
    }
}
