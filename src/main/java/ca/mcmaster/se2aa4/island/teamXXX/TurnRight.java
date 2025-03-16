package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class TurnRight extends Turn {
    /**
     * Executes the turn right action for the drone. The drone's current
     * direction is checked, and it is turned 90 degrees to the right.
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
            dir = super.turnSouth();
            drone.setDirection(Direction.S);
        } else if (currentDirection == Direction.S) {
            dir = super.turnWest();
            drone.setDirection(Direction.W);
        } else if (currentDirection ==  Direction.W) {
            dir = super.turnNorth();
            drone.setDirection(Direction.N);
        } else if (currentDirection == Direction.N) {
            dir = super.turnEast();
            drone.setDirection(Direction.E);
        }

        return dir;
    }
}
