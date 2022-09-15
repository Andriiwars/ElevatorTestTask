import java.util.Random;
import model.Building;
import model.Elevator;
import service.BuildingService;
import service.ElevatorService;
import service.impl.BuildingServiceImpl;
import service.impl.ElevatorServiceImpl;

public class Main {
    private static Random r = new Random();

    public static void main(String[]args) {
        int maxFloor = r.nextInt(16) + 5;
        Elevator elevator = new Elevator(maxFloor);
        Building building = new Building(maxFloor);
        ElevatorService elevatorService = new ElevatorServiceImpl(elevator);
        BuildingService buildingService = new BuildingServiceImpl(elevatorService,
                elevator,
                building);
        System.out.println("START");
        System.out.println(buildingService);
        buildingService.startCycle();
    }
}
