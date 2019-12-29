package cz.cvut.fel.omo.hamrazec.model;

public interface Visitable {

    void accept(VisitorDirector visitor);
}
