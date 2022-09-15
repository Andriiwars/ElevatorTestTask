package service;

import java.util.List;

public interface BuildingService {
    void startCycle();

    int addPassengersToElevator();

    int removePassengersFromLift();

    void fillRandomPassengers();

    List<Integer> fillFloor(int currentFloor);

    int createRandomPassenger(int currentFloor);

    void createRandomPassengers(int count);

    boolean getElevatorDirectionByMajorPartOfPeople();

    void showInformation(int step, int removedPassengers,int addedPassengers);
}
