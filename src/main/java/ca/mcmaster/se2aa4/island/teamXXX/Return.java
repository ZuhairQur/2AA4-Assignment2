package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class Return implements Action {
    @Override
    public JSONObject execute(Drone drone) {
        JSONObject instruction = new JSONObject();
        instruction.put("action", "stop");
        return instruction;
    }
}
