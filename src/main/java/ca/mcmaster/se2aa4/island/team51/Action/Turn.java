package ca.mcmaster.se2aa4.island.team51.Action;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team51.Navigation.Direction;
import ca.mcmaster.se2aa4.island.team51.Navigation.Drone;

public class Turn implements Action {
    private final JSONObject instruction = new JSONObject();
    private final JSONObject dir = new JSONObject();
    private final TurnSide turnSide;


    public Turn (TurnSide turnSide) {
        this.turnSide = turnSide;
    }

    /**
     * Instructs the drone to turn. The drone will not move
     * forward after turning, and will not perform any further actions until
     * it is given a new instruction.
     *
     * @param drone The drone object to send the instruction to.
     * @return A JSONObject containing the instruction to send to the drone.
     */
    @Override
    public JSONObject execute(Drone drone) {
        if (this.turnSide == TurnSide.LEFT) {
            return this.turnLeft(drone);
        }

        return this.turnRight(drone);
    }

    private JSONObject turnLeft(Drone drone) {
        Direction currentDirection = drone.getDirection();
        JSONObject dir = new JSONObject();

        if (currentDirection == Direction.E) {
            dir = this.turnNorth();
            drone.setDirection(Direction.N);
        } else if (currentDirection == Direction.S) {
            dir = this.turnEast();
            drone.setDirection(Direction.E);
        } else if (currentDirection == Direction.W) {
            dir = this.turnSouth();
            drone.setDirection(Direction.S);
        } else if (currentDirection == Direction.N) {
            dir = this.turnWest();
            drone.setDirection(Direction.W);
        }

        return dir;
    }

    private JSONObject turnRight(Drone drone) {
        Direction currentDirection = drone.getDirection();
        JSONObject dir = new JSONObject();

        if (currentDirection == Direction.E) {
            dir = this.turnSouth();
            drone.setDirection(Direction.S);
        } else if (currentDirection == Direction.S) {
            dir = this.turnWest();
            drone.setDirection(Direction.W);
        } else if (currentDirection ==  Direction.W) {
            dir = this.turnNorth();
            drone.setDirection(Direction.N);
        } else if (currentDirection == Direction.N) {
            dir = this.turnEast();
            drone.setDirection(Direction.E);
        }

        return dir;
    }

    /**
     * Gets the action type for the turn action.
     *
     * @return The action type for the turn action.
     */
    @Override
    public ActionType getActionType() {
        return ActionType.TURN;
    }

    /**
     * Instructs the drone to turn and face South. The drone will not move
     * forward after turning, and will not perform any further actions until
     * it is given a new instruction.
     */
     private JSONObject turnSouth() {
        this.instruction.clear();
        this.dir.clear();

        this.dir.put("direction", "S");

        this.instruction.put("action", "heading");
        this.instruction.put("parameters", this.dir);

        return this.instruction;
    }

    /**
     * Instructs the drone to turn and face West. The drone will not move
     * forward after turning, and will not perform any further actions until
     * it is given a new instruction.
     */
    private JSONObject turnWest() {
        this.instruction.clear();
        this.dir.clear();

        this.dir.put("direction", "W");
        this.instruction.put("action", "heading");
        this.instruction.put("parameters", this.dir);

        return this.instruction;
    }


    /**
     * Instructs the drone to turn and face East. The drone will not move
     * forward after turning, and will not perform any further actions until
     * it is given a new instruction.
     */
    private JSONObject turnEast() {
        this.instruction.clear();
        this.dir.clear();

        this.dir.put("direction", "E");

        this.instruction.put("action", "heading");
        this.instruction.put("parameters", this.dir);

        return this.instruction;
    }

    /**
     * Instructs the drone to turn and face North. The drone will not move
     * forward after turning, and will not perform any further actions until
     * it is given a new instruction.
     */
    private JSONObject turnNorth() {
        this.instruction.clear();
        this.dir.clear();

        this.dir.put("direction", "N");

        this.instruction.put("action", "heading");
        this.instruction.put("parameters", this.dir);

        return this.instruction;
    }
}
