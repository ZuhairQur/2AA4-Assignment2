package ca.mcmaster.se2aa4.island.teamXXX;
import org.json.JSONObject;

public class Fly implements Action {
    /**
     * Instructs the drone to move forward one space. The drone will move in the
     * direction it is currently facing.
     * 
     * @param drone The drone object to send the instruction to.
     * @return A JSONObject containing the instruction to send to the drone.
     */
    @Override
    public JSONObject execute(Drone drone) {
        JSONObject instruction = new JSONObject();
        instruction.put("action", "fly");
        return instruction;
    }
}
