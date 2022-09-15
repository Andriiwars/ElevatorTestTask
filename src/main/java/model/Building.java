package model;

import java.util.List;
import service.impl.BuildingServiceImpl;
import service.impl.ElevatorServiceImpl;

public class Building {
    private int floors;
    private ElevatorServiceImpl elevatorServiceImpl;
    private BuildingServiceImpl buildingServiceImpl;
    private List<Integer>[] passengersInFloor;

    public Building(int floors) {
        this.floors = floors;
        passengersInFloor = new List[floors];
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public List<Integer>[] getPassengersInFloor() {
        return passengersInFloor;
    }

    public void setPassengersInFloor(List<Integer>[] passengersInFloor) {
        this.passengersInFloor = passengersInFloor;
    }
}
