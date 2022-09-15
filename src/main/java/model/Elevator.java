package model;

public class Elevator {
    private int currentFloor = 1;
    private int maxFloor;
    private boolean direction = true;
    private int[] passengers = new int[5];

    public Elevator(int maxFloor) {
        this.maxFloor = maxFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    public void setMaxFloor(int maxFloor) {
        this.maxFloor = maxFloor;
    }

    public boolean isDirection() {
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public int[] getPassengers() {
        return passengers;
    }

    public void setPassengers(int[] passengers) {
        this.passengers = passengers;
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int passenger : passengers) {
            if (passenger != 0) {
                res.append(passenger + " ");
            }
        }
        if (res.length() > 0) {
            res.deleteCharAt(res.length() - 1);
        }
        return res.toString();
    }
}
