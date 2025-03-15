package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

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
        char currentDirection = drone.getDirection();
        JSONObject dir = new JSONObject();

        if (currentDirection == 'E') {
            dir = super.turnNorth();
            drone.setDirection('N');
        } else if (currentDirection == 'S') {
            dir = super.turnEast();
            drone.setDirection('E');
        } else if (currentDirection == 'W') {
            dir = super.turnSouth();
            drone.setDirection('S');
        } else if (currentDirection == 'N') {
            dir = super.turnWest();
            drone.setDirection('W');
        }

        return dir;
    }
}
