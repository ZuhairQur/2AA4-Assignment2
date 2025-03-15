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
        char currentDirection = drone.getDirection();
        JSONObject dir = new JSONObject();

        if (currentDirection == 'E') {
            dir = super.turnSouth();
            drone.setDirection('S');
        } else if (currentDirection == 'S') {
            dir = super.turnWest();
            drone.setDirection('W');
        } else if (currentDirection == 'W') {
            dir = super.turnNorth();
            drone.setDirection('N');
        } else if (currentDirection == 'N') {
            dir = super.turnEast();
            drone.setDirection('E');
        }

        return dir;
    }
}
