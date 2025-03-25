/**
 * File: Drone.java
 * Authors: Nithika Karunamoorthy, Stella Liu, Zuhair Qureshi
 * Description: The Drone class represents a drone that navigates the exploration map, 
 * makes decisions based on its current state, and executes actions like flying or turning. 
 * It manages its battery level, coordinates, direction, and a sequence of actions. The class 
 * also handles responses to discovered emergency sites and adjusts its behavior accordingly.
 */

package ca.mcmaster.se2aa4.island.team51.navigation;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team51.action.Action;
import ca.mcmaster.se2aa4.island.team51.action.ActionSequence;
import ca.mcmaster.se2aa4.island.team51.action.ActionType;
import ca.mcmaster.se2aa4.island.team51.action.Return;

public class Drone {
    private Integer batteryLevel;
    private JSONObject decision;
    private Direction direction;
    private ActionSequence actionSequence = new ActionSequence();
    private Coordinates coordinates;
    private boolean detectedEmergencySite = false;


    public Drone(Integer batteryLevel) {
        this.batteryLevel = batteryLevel;
        this.decision = new JSONObject();
        this.direction = Direction.E;
        this.actionSequence.generate();
        this.coordinates = new Coordinates(1.0,1.0);
        this.detectedEmergencySite = false;
    }


    /**
     * Makes a decision for the drone based on its current state and the map.
     * The decision involves executing actions from a queue, handling battery
     * constraints, and reacting to discovered emergency sites. If the battery
     * level is low, the drone will return. The decision updates the drone's
     * coordinates if the action is a fly or turn.
     *
     * @return A JSONObject containing the decision to execute.
     */
    public JSONObject makeDecision(boolean discoveredEmergencySite) {
        boolean withinBounds = true;
        this.decision.clear();

        // Spiral emergency site when discovered
        if (discoveredEmergencySite && !this.detectedEmergencySite) {
            this.actionSequence.clear();
            this.actionSequence.spiralSearch();
            this.detectedEmergencySite = true;
        }

        Action currentAction = this.actionSequence.getNextAction();
        ActionType actionType = currentAction.getActionType();
        
        if (actionType == ActionType.FLY || actionType == ActionType.TURN) {
            withinBounds = coordinates.updateCoords(this.direction);
        }

        if (!withinBounds || this.batteryLevel <= 100) {
            return new Return().execute(this);
        }

        this.decision = currentAction.execute(this);

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
     * Updates the battery level of the drone by subtracting the given cost.
     * 
     * @param cost the amount to subtract from the battery level
     * @return the updated battery level
     */
    public int updateBattery(int cost) {
        this.batteryLevel -= cost;
        return this.batteryLevel;
    }

    /**
     * Sets the current direction the drone is facing.
     * 
     * @param direction a character representing the new direction ('N', 'E', 'S', or 'W').
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Gets the current coordinates of the drone.
     * 
     * @return the current coordinates of the drone.
     */
    public Coordinates getCoordinates() {
        return this.coordinates;
    }
    
}
