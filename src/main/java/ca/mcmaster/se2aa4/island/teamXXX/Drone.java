package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class Drone {
    private JSONObject decision;
    private char direction;
    private ActionsQueue actionsQueue = new ActionsQueue();

    public Drone() {
        this.decision = new JSONObject();
        this.direction = 'E';
        this.actionsQueue.fillWithActions();
    }

    /**
     * Generates a decision for the drone based on the current step count and
     * battery level. The decision is either to move forward, turn right, or
     * scan. The drone will move to the middle of the map in a zig-zag pattern
     * and then perform a spiral search after that.
     *
     * @param batteryLevel The current battery level of the drone.
     * @return A JSONObject containing the action to take, which is one of
     *         "fly", "heading", or "scan", and any additional parameters
     *         required for the action.
     */
    public JSONObject makeDecision(int batteryLevel) {
        this.decision.clear();

        Action currentAction = this.actionsQueue.getNextAction();

        this.decision = currentAction.execute(this);
        return this.decision;
    }

    /**
     * Returns the current direction the drone is facing.
     *
     * @return a character representing the current direction ('N', 'E', 'S', or 'W').
     */
    public char getDirection() {
        return this.direction;
    }

    /**
     * Sets the current direction the drone is facing.
     * 
     * @param direction a character representing the new direction ('N', 'E', 'S', or 'W').
     */
    public void setDirection(char direction) {
        this.direction = direction;
    }
}
