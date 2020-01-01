package cz.cvut.fel.omo.hamrazec.model.person;

import cz.cvut.fel.omo.hamrazec.controller.Factory;
import cz.cvut.fel.omo.hamrazec.model.Visitable;
import cz.cvut.fel.omo.hamrazec.model.VisitableInspector;
import cz.cvut.fel.omo.hamrazec.model.machine.ControllingRobot;
import cz.cvut.fel.omo.hamrazec.model.machine.LineMachine;
import cz.cvut.fel.omo.hamrazec.model.machine.LineRobot;
import cz.cvut.fel.omo.hamrazec.model.machine.Machine;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class InspectorIterator implements Iterator {

    private Factory factory = Factory.getInstance();
    private List<VisitableInspector> visitableInspectors;
    private int counter = 0 ;

    public InspectorIterator() throws IOException {
        updateStateIterator(factory);
    }

    private void updateStateIterator(Factory factory){
        List<Machine> machines = new ArrayList<>();

        if (factory.getVisitableWorker() != null) {
            for (Visitable visitable : factory.getVisitableWorker()) {
                if (visitable.getClass() == LineMachine.class || visitable.getClass() == LineRobot.class || visitable.getClass()==ControllingRobot.class) machines.add((Machine) visitable);
            }
            visitableInspectors = machines.stream().sorted(Comparator.comparingInt(Machine::getDepreciation).reversed())
                    .collect(Collectors.toList());
        }
    }


    @Override
    public boolean hasNext() {
        updateStateIterator(factory);
        if (visitableInspectors == null) return false;
        if (visitableInspectors.size() > 0){
            return counter != visitableInspectors.size();
        }
        return false;
    }


    @Override
    public VisitableInspector next() {

        VisitableInspector context = visitableInspectors.get(counter);
        counter++;
        return context;
    }
}
