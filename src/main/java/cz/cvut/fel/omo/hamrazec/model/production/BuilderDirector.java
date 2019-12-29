package cz.cvut.fel.omo.hamrazec.model.production;

import cz.cvut.fel.omo.hamrazec.exceptions.CannotBuildLineException;

public interface BuilderDirector {
    ProductionLine build() throws CannotBuildLineException;
}
