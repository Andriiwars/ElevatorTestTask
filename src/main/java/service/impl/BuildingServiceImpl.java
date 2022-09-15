package service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Building;
import model.Elevator;
import service.BuildingService;
import service.ElevatorService;

public class BuildingServiceImpl implements BuildingService {
    private static Random r = new Random();
    private static final int STEPS_TO_VIEW = 20;
    private ElevatorService elevatorService;
    private Elevator elevator;
    private Building building;

    public BuildingServiceImpl(ElevatorService elevatorService,
                               Elevator elevator,
                               Building building) {
        this.elevatorService = elevatorService;
        this.elevator = elevator;
        this.building = building;
        fillRandomPassengers();
    }

    @Override
    public void startCycle() {
        for (int i = 1; i <= STEPS_TO_VIEW; i++) {
            int removedPassengers = this.removePassengersFromLift();
            if (elevatorService.isEmpty()) {
                elevator.setDirection(this.getElevatorDirectionByMajorPartOfPeople());
            }
            int addedPassengers = this.addPassengersToElevator();
            if (removedPassengers == 0 && addedPassengers == 0) {
                i--;
            } else {
                createRandomPassengers(removedPassengers);
                this.showInformation(i, removedPassengers, addedPassengers);
            }
            elevatorService.move();
        }
    }

    @Override
    public int addPassengersToElevator() {
        elevatorService.correctDirection();
        ArrayList<Integer> indexesToDelete = new ArrayList<Integer>();
        for (int i = 0; i < building.getPassengersInFloor()[elevator.getCurrentFloor() - 1].size()
                && !elevatorService.isFull(); i++) {
            if (elevator.isDirection()) {
                if (building.getPassengersInFloor()[elevator.getCurrentFloor() - 1].get(i)
                        > elevator.getCurrentFloor()) {
                    indexesToDelete.add(i);
                    elevatorService.addPassenger(
                            building.getPassengersInFloor()[elevator.getCurrentFloor() - 1].get(i));
                }
            } else {
                if (building.getPassengersInFloor()[elevator.getCurrentFloor() - 1].get(i)
                        < elevator.getCurrentFloor()) {
                    indexesToDelete.add(i);
                    elevatorService.addPassenger(
                            building.getPassengersInFloor()[elevator.getCurrentFloor() - 1].get(i));
                }
            }
        }
        for (int i = indexesToDelete.size() - 1; i >= 0; i--) {
            building.getPassengersInFloor()[elevator.getCurrentFloor() - 1].remove(i);
        }
        return indexesToDelete.size();
    }

    @Override
    public int removePassengersFromLift() {
        return elevatorService.removePassengers();
    }

    @Override
    public void fillRandomPassengers() {
        for (int i = 0;i < building.getFloors();i++) {
            building.getPassengersInFloor()[i] = fillFloor(i + 1);
        }
    }

    @Override
    public List<Integer> fillFloor(int currentFloor) {
        ArrayList<Integer> floor = new ArrayList<Integer>();
        int passInTheFloor = r.nextInt(11);
        for (int j = 1; j < passInTheFloor; j++) {
            floor.add(createRandomPassenger(currentFloor));
        }
        return floor;
    }

    @Override
    public int createRandomPassenger(int currentFloor) {
        int passengerTargetFloor = currentFloor;
        while (passengerTargetFloor == currentFloor) {
            passengerTargetFloor = r.nextInt(building.getFloors()) + 1;
        }

        return passengerTargetFloor;
    }

    @Override
    public void createRandomPassengers(int count) {
        for (int j = 0; j < count; j++) {
            building.getPassengersInFloor()[elevator.getCurrentFloor() - 1].add(
                    createRandomPassenger(elevator.getCurrentFloor()));
        }
    }

    @Override
    public boolean getElevatorDirectionByMajorPartOfPeople() {
        if (elevator.getCurrentFloor() == 1) {
            return true;
        } else if (elevator.getCurrentFloor() == building.getFloors()) {
            return false;
        } else {
            int peoplesWhowantUp = 0;
            for (int i = 0;
                     i < building.getPassengersInFloor()[elevator.getCurrentFloor() - 1].size();
                     i++) {
                if (building.getPassengersInFloor()[elevator.getCurrentFloor() - 1].get(i)
                        > elevator.getCurrentFloor()) {
                    peoplesWhowantUp++;
                }
            }
            return building.getPassengersInFloor()[elevator.getCurrentFloor() - 1].size()
                    - peoplesWhowantUp
                    < peoplesWhowantUp;
        }
    }

    @Override
    public void showInformation(int step, int removedPassengers, int addedPassengers) {
        System.out.println("\t   *** Step " + step + " ***");
        System.out.print(this.toString());
        System.out.println("Leave: " + removedPassengers + " Entry: " + addedPassengers + "\n");
    }

    public String toString() {
        StringBuilder result = new StringBuilder("");
        for (int i = building.getFloors() - 1; i >= 0; i--) {
            if (elevator.getCurrentFloor() != i + 1) {
                result.append(""
                        + (i + 1)
                        + "\t |    "
                        + elevator.toString().replaceAll("\\d", " ")
                        + "\t  | " + building.getPassengersInFloor()[i].toString() + "\n");
            } else {
                if (elevator.isDirection()) {
                    result.append(""
                            + (i + 1) + "\t |^   "
                            + elevator + "\t ^| "
                            + building.getPassengersInFloor()[i].toString() + "\n");
                } else {
                    result.append(""
                            + (i + 1) + "\t |v   "
                            + elevator + "\t v| "
                            + building.getPassengersInFloor()[i].toString() + "\n");
                }
            }
        }
        return result.toString();
    }
}
