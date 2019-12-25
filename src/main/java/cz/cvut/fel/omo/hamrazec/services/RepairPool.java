package cz.cvut.fel.omo.hamrazec.services;

import cz.cvut.fel.omo.hamrazec.model.person.Repairman;

import java.util.List;

public class RepairPool {

    private static RepairPool instance;
    private List<Repairman> repairmen;

    private RepairPool() {
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
            repairmen.remove(0);
            return repairman;
            // myslim ze pri obycajnom Liste metoda remove vracia odstraneny prvom, takze staci asi return repairmen.remove(0);
            // pri arrayliste to uz ale nefunguje lebo tam remove vracia boolean
        } else return null;
    }

    public void putRepairman(Repairman repairman) {
        repairmen.add(repairman);
    }


    public void setRepairmen(List<Repairman> repairmen) {
        this.repairmen = repairmen;
    }
}
