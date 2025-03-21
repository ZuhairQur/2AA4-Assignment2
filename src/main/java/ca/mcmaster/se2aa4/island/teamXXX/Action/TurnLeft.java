package ca.mcmaster.se2aa4.island.teamXXX.Action;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.teamXXX.Direction;
import ca.mcmaster.se2aa4.island.teamXXX.Drone;


public class TurnLeft extends Turn {
    /**
     * Executes the turn left action for the drone. The drone's current
     * direction is checked, and it is turned 90 degrees to the left.
     * The drone's direction is updated accordingly.
     *
     * @param drone The drone object whose direction will be changed.
     * @return A JSONObject containing the instruction to change the
     *         drone's heading.
     */
    @Override
    public JSONObject execute(Drone drone) {
        Direction currentDirection = drone.getDirection();
        JSONObject dir = new JSONObject();

        if (currentDirection == Direction.E) {
            dir = super.turnNorth();
            drone.setDirection(Direction.N);
        } else if (currentDirection == Direction.S) {
            dir = super.turnEast();
            drone.setDirection(Direction.E);
        } else if (currentDirection == Direction.W) {
            dir = super.turnSouth();
            drone.setDirection(Direction.S);
        } else if (currentDirection == Direction.N) {
            dir = super.turnWest();
            drone.setDirection(Direction.W);
        }

        return dir;
    }
}
