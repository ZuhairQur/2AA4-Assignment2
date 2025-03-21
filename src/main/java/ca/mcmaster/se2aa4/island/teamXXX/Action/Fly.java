package ca.mcmaster.se2aa4.island.teamXXX.Action;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;


public class Fly implements Action {
    /**
     * Instructs the drone to move forward one space. The drone will move in the
     * direction it is currently facing.
     * 
     * @param drone The drone object to send the instruction to.
     * @return A JSONObject containing the instruction to send to the drone.
     */

    //private final ActionEnum type = ActionEnum.FLY;

    @Override
    public JSONObject execute(Drone drone) {
        JSONObject instruction = new JSONObject();
        instruction.put("action", "fly");
        return instruction;
    }

    @Override
    public ActionType getActionType() {
        return ActionType.FLY;
    }

}
