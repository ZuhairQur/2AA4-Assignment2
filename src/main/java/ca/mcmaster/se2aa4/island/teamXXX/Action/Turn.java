package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public abstract class Turn implements Action {
    private final JSONObject instruction = new JSONObject();
    private final JSONObject dir = new JSONObject();

    /**
     * Instructs the drone to turn. The drone will not move
     * forward after turning, and will not perform any further actions until
     * it is given a new instruction.
     *
     * @param drone The drone object to send the instruction to.
     * @return A JSONObject containing the instruction to send to the drone.
     */
    @Override
    public abstract JSONObject execute(Drone drone);

    /**
     * Instructs the drone to turn and face South. The drone will not move
     * forward after turning, and will not perform any further actions until
     * it is given a new instruction.
     */
     public JSONObject turnSouth() {
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
    public JSONObject turnWest() {
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
    public JSONObject turnEast() {
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
    public JSONObject turnNorth() {
        this.instruction.clear();
        this.dir.clear();

        this.dir.put("direction", "N");

        this.instruction.put("action", "heading");
        this.instruction.put("parameters", this.dir);

        return this.instruction;
    }
}
