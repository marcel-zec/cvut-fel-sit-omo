package cz.cvut.fel.omo.hamrazec;

import java.util.List;

public class RepairPool {

    private static RepairPool instance;
    private List<Repairman> repairmen;

    private RepairPool(){
    }

    public static RepairPool getInstance() {
        if (instance == null) {
            instance = new RepairPool();
        }
        return instance;
    }

    public Repairman getRepairman(){

        if (repairmen.size() > 0){

        Repairman repairman = repairmen.get(0);
        repairmen.remove(0);
        return repairman;
        }
        else return null;
    }

    public void PutRepairman(Repairman repairman){
        repairmen.add(repairman);
    }


    public void setRepairmen(List<Repairman> repairmen) {
        this.repairmen = repairmen;
    }
}
