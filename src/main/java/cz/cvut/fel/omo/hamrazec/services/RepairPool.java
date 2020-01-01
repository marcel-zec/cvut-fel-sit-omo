package cz.cvut.fel.omo.hamrazec.services;

import cz.cvut.fel.omo.hamrazec.model.person.Repairman;

import java.util.ArrayList;
import java.util.List;

public class RepairPool {

    private static RepairPool instance;
    private List<Repairman> repairmen;

    private RepairPool() {
        repairmen = new ArrayList<>();
    }

    public static RepairPool getInstance() {
        if (instance == null) {
            instance = new RepairPool();
        }
        return instance;
    }

    public Repairman getRepairman() {

        if (repairmen.size() > 0) {
            Repairman repairman = repairmen.get(0);
            repairmen.remove(repairman);
            return repairman;
        } else return null;
    }

    public List<Repairman> getAllRepairman() {
        return repairmen;
    }

    public void putRepairman(Repairman repairman) {
        if (repairmen.contains(repairman))return;
        repairmen.add(repairman);
    }

    public void putRepairman(List<Repairman> repairmen) {
        this.repairmen.addAll(repairmen);
    }

    public void removeRepairmen(List<Repairman> repairmen){
        repairmen.removeAll(repairmen);
    }

    public void removeRepairmen(Repairman repairman){
        repairmen.remove(repairman);
    }

    public void setRepairmen(List<Repairman> repairmen) {
        this.repairmen = repairmen;
    }
}
