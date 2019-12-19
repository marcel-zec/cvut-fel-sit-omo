package cz.cvut.fel.omo.hamrazec;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileManager {

    private ObjectMapper mapper = new ObjectMapper();
    private Factory factory = Factory.getInstance();

    private void loading() {

        try {
            List<Repairman> repairmen =  Arrays.asList(mapper.readValue(new File("src/main/resources/repairmen.txt"), Repairman[].class));
            List<Worker> workers =  Arrays.asList(mapper.readValue(new File("src/main/resources/workers.txt"), Worker[].class));
            JsonNode jsonNode = mapper.readTree(new File("src/main/resources/machines.txt"));
            int numRobots = jsonNode.get("robots").asInt();
            int numMachines = jsonNode.get("machines").asInt();


            List<FactoryWorker> factoryWorkers = factory.getFactoryWorkers();
            factoryWorkers.addAll(workers);
            factoryWorkers.addAll(repairmen);

            for (int i = 0; i < numMachines; i++) {
                factoryWorkers.add(new Machine());
            }

            for (int i = 0; i < numRobots ; i++) {
                factoryWorkers.add(new Robot());
            }

            factory.setFactoryWorkers(factoryWorkers);
            factory.getPool().setRepairmen(repairmen);

        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public FileManager() throws IOException {
        loading();
    }
}
