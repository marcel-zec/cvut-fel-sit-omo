package cz.cvut.fel.omo.hamrazec.model;

public interface VisitableInspector {
    void accept(VisitorInspector visitor);
}
