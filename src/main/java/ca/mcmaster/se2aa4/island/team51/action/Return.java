/**
 * File: Return.java
 * Authors: Nithika Karunamoorthy, Stella Liu, Zuhair Qureshi
 * Description: The Return class implements the Action interface and represents the 
 * action of stopping the drone, typically when it needs to return to the starting point.
 * The execute method creates a JSONObject containing the "stop" action, which is sent 
 * to the drone. The getActionType method returns the RETURN action type associated 
 * with this class.
 */


package ca.mcmaster.se2aa4.island.team51.action;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team51.navigation.Drone;

public class Return implements Action {
    @Override
    public JSONObject execute(Drone drone) {
        JSONObject instruction = new JSONObject();
        instruction.put("action", "stop");
        return instruction;
    }

    /**
     * Gets the action type for the return action.
     *
     * @return The action type for the return action.
     */
    @Override
    public ActionType getActionType() {
        return ActionType.RETURN;
    }
}
