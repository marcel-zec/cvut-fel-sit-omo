package cz.cvut.fel.omo.hamrazec.test.services;

import cz.cvut.fel.omo.hamrazec.model.person.Repairman;
import cz.cvut.fel.omo.hamrazec.services.RepairPool;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

public class RepairmanPoolTest {
    RepairPool repairPool = RepairPool.getInstance();
    Repairman repairman = new Repairman("Jozef","Blazko", 200);

    @Before
    public void init() {
        repairPool.putRepairman(repairman);
    }

    @Test
    public void getRepairman_worksCorrect(){
        assertEquals("Get repairman not working correctly",repairman,repairPool.getRepairman());
    }

    @Test
    public void getNoRepairman_worksCorrect(){
        repairPool.getRepairman();
        assertEquals("Get repairman not working correctly",null,repairPool.getRepairman());
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
        assertEquals("Put repairman not working correctly",null,repairPool.getRepairman());
    }

}
