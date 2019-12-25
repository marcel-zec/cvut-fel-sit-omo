package cz.cvut.fel.omo.hamrazec.model.production;

import cz.cvut.fel.omo.hamrazec.exceptions.CannotBuildLineException;
import cz.cvut.fel.omo.hamrazec.model.production.ProductLine;

public interface BuilderDirector {
    ProductLine build() throws CannotBuildLineException;
}
