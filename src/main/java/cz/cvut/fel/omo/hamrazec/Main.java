package cz.cvut.fel.omo.hamrazec;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Factory factory = Factory.getInstance();
        FactoryTimer timer = new FactoryTimer();
        timer.timeLapse();
    }
}
