package ca.mcmaster.se2aa4.island.teamXXX;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.teamXXX.Action.Action;
import ca.mcmaster.se2aa4.island.teamXXX.Action.ActionType;
import ca.mcmaster.se2aa4.island.teamXXX.Action.ActionsQueue;
import ca.mcmaster.se2aa4.island.teamXXX.Action.Return;

public class Drone {
    private JSONObject decision;
    private Direction direction;
    private Integer batteryLevel;
    private ActionsQueue actionsQueue = new ActionsQueue();
    private Coordinates coordinates;
    private boolean detectedEmergencySite = false;
    private Map map; 
    private static final Logger logger = LogManager.getLogger(Drone.class);

    public Drone(Integer batteryLevel) {
        this.batteryLevel = batteryLevel;
        this.decision = new JSONObject();
        this.direction = Direction.E;
        this.actionsQueue.fillWithActions();
        this.coordinates = new Coordinates(1.0,1.0);
        this.map = new Map();
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
    public JSONObject makeDecision() {
        this.decision.clear();

        if (batteryLevel <= 100) {
            return new Return().execute(this);
        }

        if (map.discoveredEmergencySite() && !this.detectedEmergencySite) {
            this.actionsQueue.clear();
            this.actionsQueue.spiralSearch();
            this.detectedEmergencySite = true;
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

    public int updateBattery(int cost) {
        this.batteryLevel -= cost;
        return batteryLevel;
    }

    /**
     * Sets the current direction the drone is facing.
     * 
     * @param direction a character representing the new direction ('N', 'E', 'S', or 'W').
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    //public void updateMemory(JSONObject response) {
    //    JsonParser.parseAcknowledgment(response, map, this);
    //}
    
    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public Map getMap() {
        return this.map;
        
    }
}
