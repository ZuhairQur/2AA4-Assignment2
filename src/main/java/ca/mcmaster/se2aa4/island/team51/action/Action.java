/**
 * File: Action.java
 * Authors: Nithika Karunamoorthy, Stella Liu, Zuhair Qureshi
 * Description: The Action interface defines the structure for actions that can be performed by the drone. 
 * It contains methods for executing an action on a given drone and retrieving the type of the action. 
 * Classes implementing this interface will define specific actions that the drone can take, such as flying or turning.
 */

package ca.mcmaster.se2aa4.island.team51.action;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team51.navigation.Drone;

public interface Action {
    JSONObject execute(Drone drone);
    ActionType getActionType();

}



