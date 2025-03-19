package ca.mcmaster.se2aa4.island.teamXXX;

import java.util.ArrayList;
import java.util.List;

public class Coordinates {
    private Direction prevDirection;
    private Direction direction;
    private Double x;
    private Double y;
    private List<Double> coordList;
    
    public Coordinates(Direction direction) {
        this.direction = direction;
        this.prevDirection = this.direction;
        this.x = 0.0; //PLACEHOLDER
        this.y = 0.0; //PLACEHOLDER
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

    public String getCoordinates() {
        String printCoordList = "(" + coordList.get(0) + coordList.get(1) + ")";
        return printCoordList;
    }
    
}
