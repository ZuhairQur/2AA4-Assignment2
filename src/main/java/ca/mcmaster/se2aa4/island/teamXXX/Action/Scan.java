package ca.mcmaster.se2aa4.island.teamXXX;
import org.json.JSONObject;

public class Scan implements Action {
    /**
     * Instructs the drone to scan the current space. The drone will stop moving
     * and scan the space it is currently in. The drone will not perform any
     * further actions until it is given a new instruction.
     *
     * @param drone The drone object to send the instruction to.
     * @return A JSONObject containing the instruction to send to the drone.
     */
    @Override
    public JSONObject execute(Drone drone) {
        JSONObject instruction = new JSONObject();
        instruction.put("action", "scan");
        return instruction;
    }
}
