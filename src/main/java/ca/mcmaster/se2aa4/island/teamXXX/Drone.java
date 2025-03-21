package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.teamXXX.Action.Action;
import ca.mcmaster.se2aa4.island.teamXXX.Action.ActionType;
import ca.mcmaster.se2aa4.island.teamXXX.Action.ActionsQueue;
import ca.mcmaster.se2aa4.island.teamXXX.Action.Return;

public class Drone {
    private JSONObject decision;
    private Direction direction;
    private ActionsQueue actionsQueue = new ActionsQueue();
    private Coordinates coordinates;
    private String currentCoords;

    public Drone() {
        this.decision = new JSONObject();
        this.direction = Direction.E;
        this.actionsQueue.fillWithActions();
        this.coordinates = new Coordinates(1.0,1.0);
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

        if (batteryLevel <= 100) {
            return new Return().execute(this);
        }

        Action currentAction = this.actionsQueue.getNextAction();
        ActionType actionType = currentAction.getActionType();

        this.decision = currentAction.execute(this);

        if (actionType == ActionType.FLY || actionType == ActionType.TURN) {
            coordinates.updateCoordsFly(getDirection());
            System.out.print(coordinates.getCoordinates());
        }

        return this.decision;
    }

    /**
     * Returns the current direction the drone is facing.
     *
     * @return a character representing the current direction ('N', 'E', 'S', or 'W').
     */
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * Sets the current direction the drone is facing.
     * 
     * @param direction a character representing the new direction ('N', 'E', 'S', or 'W').
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getCoords() {
        currentCoords = coordinates.toString();
        return currentCoords;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }
}
