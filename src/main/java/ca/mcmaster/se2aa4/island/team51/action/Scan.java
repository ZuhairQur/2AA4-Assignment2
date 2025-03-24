/**
 * File: Scan.java
 * Authors: Nithika Karunamoorthy, Stella Liu, Zuhair Qureshi
 * Description: The Scan class implements the Action interface and represents the 
 * action of scanning the current space. The execute method creates a JSONObject 
 * containing the "scan" action, which is sent to the drone. This instructs the drone 
 * to stop moving and perform a scan. The getActionType method returns the SCAN 
 * action type associated with this class.
 */

package ca.mcmaster.se2aa4.island.team51.action;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team51.navigation.Drone;

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

    /**
     * Gets the action type for the scan action.
     *
     * @return The action type for the scan action.
     */
    @Override
    public ActionType getActionType() {
        return ActionType.SCAN;
    }
}
