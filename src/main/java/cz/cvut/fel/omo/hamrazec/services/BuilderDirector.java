package main.java.cz.cvut.fel.omo.hamrazec.services;

import main.java.cz.cvut.fel.omo.hamrazec.model.production.ProductLine;
import main.java.cz.cvut.fel.omo.hamrazec.services.builders.LineBuilder;

public interface BuilderDirector {
    ProductLine build();
}
