package cz.cvut.fel.omo.hamrazec.services;

import cz.cvut.fel.omo.hamrazec.controller.Factory;
import cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import cz.cvut.fel.omo.hamrazec.model.person.InspectorIterator;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * FactoryTimer is used for simulation of real time. Time is represented by tact that should represent one hour.
 * It is possible to change time of tack for better simulation by calling method timeLapse with argument.
 */
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

    /**
     * Method change tact and update it in all factory worker which has in list.
     * In few tact start inspection of inspector or director by calling method startInspection and startDirectorVisit in factory.
     */
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

            if (tact % 15 == 0 && tact!=0) {
                try {
                    factory.startDirectorVisit();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    };

    /**
     * Method that start schedule call of run method in period of one hour.
     */
    public void timeLapse(){
        timer.schedule(tt,Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()) , 1000 * 60 * 60);
    }

    /**
     * Method that start schedule call of run method in given period.
     * @param second of period
     */
    public void timeLapse(int second){
        timer.schedule(tt,Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()) ,second * 1000);
    }

    /**
     * Method that start schedule call of run method in given period.
     * @param second of period
     * @param minute of period
     */
    public void timeLapse(int second, int minute){
        timer.schedule(tt,Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()) ,(second * 1000) + (minute * 60 * 1000));
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
