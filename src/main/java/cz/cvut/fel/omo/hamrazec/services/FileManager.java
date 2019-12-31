package cz.cvut.fel.omo.hamrazec.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.omo.hamrazec.controller.Factory;
import cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import cz.cvut.fel.omo.hamrazec.model.Visitable;
import cz.cvut.fel.omo.hamrazec.model.machine.LineMachine;
import cz.cvut.fel.omo.hamrazec.model.machine.LineRobot;
import cz.cvut.fel.omo.hamrazec.model.machine.Machine;
import cz.cvut.fel.omo.hamrazec.model.person.Repairman;
import cz.cvut.fel.omo.hamrazec.model.person.Worker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FileManager {

    private ObjectMapper mapper = new ObjectMapper();
    private Factory factory = Factory.getInstance();
    private Random random = new Random();

    private void loading() {

        try {
            List<Repairman> repairmen =  Arrays.asList(mapper.readValue(new File("src/main/resources/repairmen.txt"), Repairman[].class));
            List<Worker> workers =  Arrays.asList(mapper.readValue(new File("src/main/resources/workers.txt"), Worker[].class));
            JsonNode jsonNode = mapper.readTree(new File("src/main/resources/machines.txt"));
            int numRobots = jsonNode.get("robots").asInt();
            int numMachines = jsonNode.get("machines").asInt();


            List<FactoryWorker> factoryWorkers = new ArrayList<>();
            factoryWorkers.addAll(workers);
            factoryWorkers.addAll(repairmen);

            for (int i = 0; i < numMachines; i++) {
                int rand = random.nextInt(20);
                factoryWorkers.add(new LineMachine(2000,rand));
            }

            for (int i = 0; i < numRobots ; i++) {
                int rand = random.nextInt(20);
                factoryWorkers.add(new LineRobot(2010, rand) );
            }

            factory.putFactoryWorkersToFactory(factoryWorkers);
            factory.getPool().setRepairmen(repairmen);

        } catch (IOException e){
            System.out.println(e);
        }

    }

    public FileManager() throws IOException {
        loading();
    }
}
