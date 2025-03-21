package ca.mcmaster.se2aa4.island.teamXXX;

import java.util.ArrayList;
import java.util.List;

public class Coordinates {
    private Direction prevDirection;
    private Direction direction;
    private Double x;
    private Double y;
    private List<Double> coordList;
    
    public Coordinates(Double x, Double y) {
        this.direction = Direction.E;
        this.prevDirection = this.direction;
        this.x = x;
        this.y = y;
        this.coordList = new ArrayList<>();
        coordList.add(x);
        coordList.add(y);
    }

    public void updateCoordsFly(Direction direction) {
        this.direction = direction;
        if (direction == Direction.E || prevDirection == Direction.E) {
            x++;
        }
        if (direction == Direction.S || prevDirection == Direction.S) {
            y++;
        }
        if (direction == Direction.W || prevDirection == Direction.W) {
            x--;
        }
        if (direction == Direction.N || prevDirection == Direction.N) {
            y--;
        }

        coordList.set(0, x);
        coordList.set(1, y);

        prevDirection = direction;
    }

    public Double getX() {
        return this.x;
    }

    public Double getY() {
        return this.y;
    }

    public String toString() {
        String printCoordinate = "(" + this.x + "," + this.y + ")";
        return printCoordinate;
    }
    
    public List<Double> getCoordinates() {
        return coordList;
    }
}
