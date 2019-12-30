package cz.cvut.fel.omo.hamrazec.model.person;

import cz.cvut.fel.omo.hamrazec.controller.Factory;
import cz.cvut.fel.omo.hamrazec.model.Visitable;
import cz.cvut.fel.omo.hamrazec.model.VisitableInspector;
import cz.cvut.fel.omo.hamrazec.model.machine.Machine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class InspectorIterator implements Iterator {

    private Factory factory = Factory.getInstance();
    private List<Machine> visitableInspectors = new ArrayList<>();

    public InspectorIterator() throws IOException {

        for (Visitable visitable: factory.getFactoryWorkers()) {
            if (visitable.getClass() == Machine.class) visitableInspectors.add((Machine) visitable);
        }

       // visitableInspectors.stream().sorted(Comparator.comparingInt(Machine::get)).collect(Collectors.toList());
    }


    @Override
    public boolean hasNext() {

        return false;
    }


    @Override
    public Object next() {

        return null;
    }
}
