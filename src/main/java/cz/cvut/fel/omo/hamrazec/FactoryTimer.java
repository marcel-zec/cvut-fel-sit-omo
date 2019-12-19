package cz.cvut.fel.omo.hamrazec;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class FactoryTimer {

    private Timer timer = new Timer();
    private LocalDate date = LocalDate.now();
    private static FactoryTimer instance;


    private FactoryTimer() {
    }

    private TimerTask tt = new TimerTask() {
        @Override
        public void run() {
        }
    };

    public void timeLapse(){
        timer.schedule(tt,Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()) ,60*60*1000);
    }


    public static FactoryTimer getInstance() {
        if (instance == null) {
            instance = new FactoryTimer();
        }
        return instance;
    }
}
