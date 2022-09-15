package service.impl;

import model.Elevator;
import service.ElevatorService;

public class ElevatorServiceImpl implements ElevatorService {
    private Elevator elevator;
    private Integer maxFloor;

    public ElevatorServiceImpl(Elevator elevator) {
        this.maxFloor = elevator.getMaxFloor();
        this.elevator = elevator;
    }

    @Override
    public int move() {
        this.correctDirection();
        int nextFloor;
        if (!this.isFull()) {
            nextFloor = elevator.isDirection()
                    ? elevator.getCurrentFloor()
                    + 1 : elevator.getCurrentFloor() - 1;
        } else {
            nextFloor = findClosestPassengerFloorIfElevatorFull();
        }
        elevator.setCurrentFloor(nextFloor);
        return nextFloor;
    }

    private int findClosestPassengerFloorIfElevatorFull() {
        int result = 0;
        if (elevator.isDirection()) {
            int min = elevator.getMaxFloor() + 1;
            for (int i: elevator.getPassengers()) {
                if (i != 0 && i < min) {
                    min = i;
                }
            }
            result = (min != elevator.getMaxFloor() + 1) ? min : 0;
        } else {
            int max = 0;
            for (int i: elevator.getPassengers()) {
                if (i > max) {
                    max = i;
                }
                result = max;
            }
        }
        if (result == 0) {
            throw new IllegalStateException("Method can`t find next floor!");
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        for (int i: elevator.getPassengers()) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isFull() {
        boolean isElevatorFull = true;
        for (int i = 0; i < 5; i++) {
            if (elevator.getPassengers()[i] == 0) {
                isElevatorFull = false;
                break;
            }
        }
        return isElevatorFull;
    }

    @Override
    public void correctDirection() {
        if (elevator.getCurrentFloor() == 1) {
            elevator.setDirection(true);
        } else {
            if (elevator.getCurrentFloor() == maxFloor) {
                elevator.setDirection(false);
            }
        }
    }

    @Override
    public void addPassenger(int passengerFloor) {
        for (int i = 0;i < 5;i++) {
            if (elevator.getPassengers()[i] == 0) {
                elevator.getPassengers()[i] = passengerFloor;
                return;
            }
        }
    }

    @Override
    public int removePassengers() {
        int removedPassengersCount = 0;
        for (int i = 0;i < 5;i++) {
            if (elevator.getPassengers()[i] == elevator.getCurrentFloor()) {
                elevator.getPassengers()[i] = 0;
                removedPassengersCount++;
            }
        }
        return removedPassengersCount;
    }
}
