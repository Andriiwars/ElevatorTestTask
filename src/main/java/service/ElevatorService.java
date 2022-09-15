package service;

public interface ElevatorService {
    int move();

    boolean isEmpty();

    boolean isFull();

    void correctDirection();

    void addPassenger(int passengerFloor);

    int removePassengers();
}
