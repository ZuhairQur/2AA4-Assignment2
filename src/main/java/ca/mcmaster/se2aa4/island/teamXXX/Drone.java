package ca.mcmaster.se2aa4.island.teamXXX;

public class Drone { 

    private Direction direction; 
    private Integer batteryLevel; 
    private int batteryLimit; 

    public Drone(Integer batteryLevel) {
        this.direction = Direction.E;
        this.batteryLevel = batteryLevel;
        this.batteryLimit = 25;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getBatteryLevel() {
        return this.batteryLevel;
    }

    public void updateBattery(int cost){
        this.batteryLevel -= cost;
    }

}