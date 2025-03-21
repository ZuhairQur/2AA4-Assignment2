package ca.mcmaster.se2aa4.island.teamXXX.Action;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;

public class Return implements Action {
    @Override
    public JSONObject execute(Drone drone) {
        JSONObject instruction = new JSONObject();
        instruction.put("action", "stop");
        return instruction;
    }

    @Override
    public ActionType getActionType() {
        return ActionType.RETURN;
    }
}
