package cz.cvut.fel.omo.hamrazec.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.omo.hamrazec.controller.Factory;
import cz.cvut.fel.omo.hamrazec.model.FactoryWorker;
import cz.cvut.fel.omo.hamrazec.model.LineWorker;
import cz.cvut.fel.omo.hamrazec.model.Visitable;
import cz.cvut.fel.omo.hamrazec.model.machine.ControllingRobot;
import cz.cvut.fel.omo.hamrazec.model.machine.LineMachine;
import cz.cvut.fel.omo.hamrazec.model.machine.LineRobot;
import cz.cvut.fel.omo.hamrazec.model.machine.Machine;
import cz.cvut.fel.omo.hamrazec.model.person.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FileManager {

    private ObjectMapper mapper;
    private Factory factory;
    private MachineGenerator generator;

    public FileManager(Factory factory) {
        mapper = new ObjectMapper();
        this.factory = factory;
        generator = new MachineGenerator();
        loading();
    }

    private void loading() {

        try {
            List<Repairman> repairmen =  Arrays.asList(mapper.readValue(new File("src/main/resources/repairmen.txt"), Repairman[].class));
            List<Worker> workers =  Arrays.asList(mapper.readValue(new File("src/main/resources/workers.txt"), Worker[].class));
            Director director =  mapper.readValue(new File("src/main/resources/director.txt"), Director.class);
            Inspector inspector =  mapper.readValue(new File("src/main/resources/inspector.txt"), Inspector.class);
            JsonNode jsonNode = mapper.readTree(new File("src/main/resources/machines.txt"));
            int numRobots = jsonNode.get("robots").asInt();
            int numMachines = jsonNode.get("machines").asInt();
            int numControllingRobots = jsonNode.get("controlRobots").asInt();

            List<Repairman> repairmenList = new ArrayList<>();
            List<Worker> workerList = new ArrayList<>();
            repairmenList.addAll(repairmen);
            workerList.addAll(workers);


            List<FactoryWorker> factoryWorkers = new ArrayList<>();
            List<LineWorker> lineWorkers = new ArrayList<>();
            factoryWorkers.addAll(workers);
            factoryWorkers.addAll(repairmen);
            lineWorkers.addAll(workers);

            for (int i = 0; i < numMachines; i++) {
                LineMachine lineMachine = generator.generateMachine();
                factoryWorkers.add(lineMachine);
                lineWorkers.add(lineMachine);
            }

            for (int i = 0; i < numRobots ; i++) {
                LineRobot lineRobot = generator.generateRobot();
                factoryWorkers.add( lineRobot);
                lineWorkers.add(lineRobot);
            }

            for (int i = 0; i < numControllingRobots ; i++) {
                ControllingRobot controllingRobot = generator.generateControlRobot();
                lineWorkers.add(controllingRobot);
                factoryWorkers.add(controllingRobot);
            }

            factory.getPool().setRepairmen(repairmenList);
            factory.setLineWorkers(lineWorkers);
            factory.setDirector(director);
            factory.setInspector(inspector);

        } catch (IOException e){
            System.out.println(e);
        }

    }
}
