/**
 * File: ResponseObserver.java
 * Authors: Nithika Karunamoorthy, Stella Liu, Zuhair Qureshi
 * Description: The ResponseObserver interface defines a contract for classes that 
 * observe and process responses from the drone. Implementing classes must provide 
 * an update method to handle response data, update the drone's state, and modify 
 * the map as necessary.
 */


package ca.mcmaster.se2aa4.island.team51.observers;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team51.navigation.Drone;
import ca.mcmaster.se2aa4.island.team51.navigation.Map;

public interface ResponseObserver {
    void update(JSONObject response, Drone drone, Map map);
}
