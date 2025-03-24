/**
 * File: Fly.java
 * Authors: Nithika Karunamoorthy, Stella Liu, Zuhair Qureshi
 * Description: The Fly class implements the Action interface and represents the action
 * of moving the drone forward by one space in the current direction. The execute method
 * creates a JSONObject containing the "fly" action, which is sent to the drone. The 
 * getActionType method returns the FLY action type associated with this class.
 */

package ca.mcmaster.se2aa4.island.team51.action;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team51.navigation.Drone;


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

    /**
     * Returns the type of action this instance represents, which is "fly".
     *
     * @return The ActionType associated with this action.
     */
    @Override
    public ActionType getActionType() {
        return ActionType.FLY;
    }

}
