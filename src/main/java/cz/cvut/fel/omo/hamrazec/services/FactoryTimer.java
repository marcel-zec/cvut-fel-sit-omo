package cz.cvut.fel.omo.hamrazec.services;

import cz.cvut.fel.omo.hamrazec.controller.Factory;
import cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import cz.cvut.fel.omo.hamrazec.model.person.InspectorIterator;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class FactoryTimer {

    private Timer timer = new Timer();
    private LocalDate date = LocalDate.now();
    private static FactoryTimer instance;
    private Factory factory;
    private List<FactoryWorker> factoryWorkers;
    private int tact;

    private FactoryTimer() throws IOException {
        factoryWorkers = new ArrayList<>();
    }

    private TimerTask tt = new TimerTask() {
        @Override
        public void run() {
            tact++;
            for (FactoryWorker worker: factoryWorkers) {
                worker.updateTact(tact);
            }
            System.out.println("---------");
            System.out.println("tack: " + tact);
            factory.update();

            if (tact % 10 == 0 && tact!=0) {
                try {
                    factory.startInspection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (tact % 8 == 0 && tact!=0) {
                try {
                    factory.startDirectorVisit();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    };

    public void timeLapse(){
        timer.schedule(tt,Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()) ,1500);
    }


    public static FactoryTimer getInstance() throws IOException {
        if (instance == null) {
            instance = new FactoryTimer();
        }
        return instance;
    }

    public List<FactoryWorker> getVisitableWorkers() {
        return factoryWorkers;
    }

    public void addFactoryWorkers(List<FactoryWorker> factoryWorkers){
        this.factoryWorkers.addAll(factoryWorkers);
    }

    public void addFactoryWorkers(FactoryWorker factoryWorker){
        factoryWorkers.add(factoryWorker);
    }

    public void removeFactoryWorkers(List<FactoryWorker> factoryWorkers){
        this.factoryWorkers.removeAll(factoryWorkers);
    }

    public void removeFactoryWorkers(FactoryWorker factoryWorker){
        this.factoryWorkers.remove(factoryWorker);
    }

    public void setFactoryWorkers(List<FactoryWorker> factoryWorkers) {
        this.factoryWorkers = factoryWorkers;
    }


    public void setFactory(Factory factory) {
        this.factory = factory;
    }

}
