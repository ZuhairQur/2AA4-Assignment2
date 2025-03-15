package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class Drone {
    private int stepsMoved;
    private final JSONObject decision;
    private char direction;
    private boolean turning;
    private boolean scanning;
    private int stepsToMiddle;
    private int spiralSize;
    private int spiralStepsDone;

    public Drone() {
        this.stepsMoved = 0;
        this.spiralSize = 1;
        this.spiralStepsDone = 0;
        this.stepsToMiddle = 52; 
        this.decision = new JSONObject();
        this.direction = 'E';
        this.turning = false;
        this.scanning = false;
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
        this.stepsMoved++;

        if (this.stepsMoved <= this.stepsToMiddle) {
            this.moveToMiddle();
        } else {
            this.spiralSearch();
        }

        return this.decision;
    }

    /**
     * Moves the drone to the middle of the map by moving it in a zig-zag
     * pattern. The drone will move forward and then turn right, then move
     * forward and turn right again, and so on. The drone will also scan
     * every 26 steps. The drone will stop when it reaches the middle of the
     * map.
     */
    public void moveToMiddle() {
        if (this.turning) {
            this.turnRight();
            this.turning = false;
        }
        else if (this.stepsMoved % (this.stepsToMiddle/2) == 0) {
            this.scan();
            this.turning = true;
        } else {
            this.fly();
            this.turning = false;
        }
    }

    /**
     * Resets the number of steps moved by the drone to 0.
     */
    public void resetSteps() {
        this.stepsMoved = 0;
    }

    /**
     * Spiral search algorithm for the drone. The drone will move in a
     * spiral pattern, scanning and flying on each step. The spiral size
     * will increase by one after each complete spiral. The drone will
     * stop when it reaches the edge of the map or runs out of battery.
     */
    public void spiralSearch() {
        this.spiralStepsDone += 1;

        if (this.spiralStepsDone <= 2* this.spiralSize) {
            if (this.spiralStepsDone % 2 == 0) {
                this.scan();
            } else {
                this.fly();
            }
        } else {
            this.turnRight();
            this.spiralSize += 1;
            this.spiralStepsDone = 0;
        }
    }

    /**
     * Instructs the drone to fly forward one space.
     */
    public void fly() {
        this.decision.put("action", "fly");
    }

    /**
     * Instructs the drone to return to base and stop moving. The drone will
     * not perform any further actions after this instruction is given.
     */
    public void returnToBase() {
        this.decision.put("action", "stop");
    }

    /**
     * Instructs the drone to scan the current space. The drone will stop moving
     * and scan the space it is currently in. The drone will not perform any
     * further actions until it is given a new instruction.
     */
    public void scan() {
        this.decision.put("action", "scan");
    }


    /**
     * Instructs the drone to turn right 90 degrees. The drone will not move
     * forward after turning, and will not perform any further actions until
     * it is given a new instruction. The drone will keep track of its current
     * direction and will turn right relative to that direction.
     */
    public void turnRight() {
        if (this.direction == 'E') {
            this.turnSouth();
            this.direction = 'S';
        } else if (this.direction == 'S') {
            this.turnWest();
            this.direction = 'W';
        } else if (this.direction == 'W') {
            this.turnNorth();
            this.direction = 'N';
        } else if (this.direction == 'N') {
            this.turnEast();
            this.direction = 'E';
        }
    }

    /**
     * Instructs the drone to turn left 90 degrees. The drone will not move
     * forward after turning, and will not perform any further actions until
     * it is given a new instruction. The drone will keep track of its current
     * direction and will turn left relative to that direction.
     */
    public void turnLeft() {
        if (this.direction == 'E') {
            this.turnNorth();
            this.direction = 'N';
        } else if (this.direction == 'S') {
            this.turnEast();
            this.direction = 'E';
        } else if (this.direction == 'W') {
            this.turnSouth();
            this.direction = 'S';
        } else if (this.direction == 'N') {
            this.turnWest();
            this.direction = 'W';
        }
    }

    /**
     * Instructs the drone to turn and face East. The drone will not move
     * forward after turning, and will not perform any further actions until
     * it is given a new instruction.
     */
    public void turnEast() {
        JSONObject dir = new JSONObject();
        dir.put("direction", "E");

        this.decision.put("action", "heading");
        this.decision.put("parameters", dir);
    }


    /**
     * Instructs the drone to turn and face South. The drone will not move
     * forward after turning, and will not perform any further actions until
     * it is given a new instruction.
     */

    public void turnSouth() {
        JSONObject dir = new JSONObject();
        dir.put("direction", "S");

        this.decision.put("action", "heading");
        this.decision.put("parameters", dir);
    }


    /**
     * Instructs the drone to turn and face West. The drone will not move
     * forward after turning, and will not perform any further actions until
     * it is given a new instruction.
     */

    public void turnWest() {
        JSONObject dir = new JSONObject();
        dir.put("direction", "W");

        this.decision.put("action", "heading");
        this.decision.put("parameters", dir);
    }

    /**
     * Instructs the drone to turn and face North. The drone will not move
     * forward after turning, and will not perform any further actions until
     * it is given a new instruction.
     */
    public void turnNorth() {
        JSONObject dir = new JSONObject();
        dir.put("direction", "N");

        this.decision.put("action", "heading");
        this.decision.put("parameters", dir);
    }
}
