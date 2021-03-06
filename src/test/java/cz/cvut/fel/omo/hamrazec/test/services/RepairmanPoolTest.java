package cz.cvut.fel.omo.hamrazec.test.services;

import cz.cvut.fel.omo.hamrazec.model.person.Repairman;
import cz.cvut.fel.omo.hamrazec.services.RepairPool;
import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RepairmanPoolTest {
    private RepairPool repairPool;
    private Repairman repairman = new Repairman("Jozef","Blazko", 200);

    @Before
    public void init() {
        repairPool = RepairPool.getInstance();
        repairPool.setRepairmen(new ArrayList<>());
        repairPool.putRepairman(repairman);
    }

    @Test
    public void getRepairman_worksCorrect(){
        assertEquals("Get repairman not working correctly",repairman,repairPool.getRepairman());
    }

    @Test
    public void getNoRepairman_worksCorrect(){
        repairPool.getRepairman();
        assertNull("Get repairman not working correctly", repairPool.getRepairman());
    }


    @Test
    public void putRepairman_worksCorrect(){
        Repairman repairman1 = new Repairman("Milan", "Zednicek", 300);
        repairPool.putRepairman(repairman1);
        assertEquals("Put repairman not working correctly",repairman,repairPool.getRepairman());
    }

    @Test
    public void putExistingRepairman_worksCorrect(){
        repairPool.putRepairman(repairman);
        repairPool.getRepairman();
        assertNull("Put repairman not working correctly", repairPool.getRepairman());
    }

}
