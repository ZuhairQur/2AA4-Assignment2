package ca.mcmaster.se2aa4.island.team51.navigation;

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

    /**
     * Updates the coordinates of the drone based on the given direction. If the given direction is the same as the previous direction, the coordinates are incremented/decremented accordingly. The coordinates are then updated in the coordList.
     * 
     * @param direction the direction to update the coordinates with
     */
    public void updateCoords(Direction direction) {
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

    /**
     * Returns the x-coordinate of the coordinates.
     * @return The x-coordinate.
     */
    public Double getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate of the coordinates.
     * @return The y-coordinate.
     */
    public Double getY() {
        return this.y;
    }

    /**
     * Returns a string representation of the coordinates, in the format (x,y).
     * @return A string containing the coordinates.
     */
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
    
    /**
     * Retrieves the current coordinates as a list.
     *
     * @return A list containing the x and y coordinates.
     */
    public List<Double> getCoordinates() {
        return coordList;
    }

    /**
     * Compares two sets of coordinates for equality.
     * 
     * @param obj The other set of coordinates to compare to.
     * @return True if the two sets of coordinates are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj){ 
            return true; 
        } 
        if (obj == null || getClass() != obj.getClass()){
            return false;
        } 
        Coordinates that = (Coordinates) obj;
        return this.x.equals(that.x) && this.y.equals(that.y);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + x.hashCode();
        result = 31 * result + y.hashCode();
        return result;
     }

    /**
     * Calculates the Euclidean distance between two coordinates.
     * 
     * @param other The other set of coordinates to calculate the distance to.
     * @return The distance between the two coordinates.
     */
    public double distance(Coordinates other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

}
