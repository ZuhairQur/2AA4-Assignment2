/**
 * File: CreekObserver.java
 * Authors: Nithika Karunamoorthy, Stella Liu, Zuhair Qureshi
 * Description: The CreekObserver class implements the ResponseObserver interface and is 
 * responsible for detecting and recording creek locations discovered by the drone. 
 * When the response contains creek information, it updates the map with the creek's 
 * coordinates and assigns it the appropriate location type.
 */


package ca.mcmaster.se2aa4.island.team51.observers;

import org.json.JSONArray;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team51.navigation.LocationType;
import ca.mcmaster.se2aa4.island.team51.navigation.Coordinates;
import ca.mcmaster.se2aa4.island.team51.navigation.Drone;
import ca.mcmaster.se2aa4.island.team51.navigation.Map;

public class CreekObserver implements ResponseObserver {
    
    /**
     * Updates the map with creek locations based on the given response.
     * If the response contains creek information, the method adds the creeks
     * to the map at the coordinates of the drone.
     *
     * @param response A JSONObject containing the response from the drone.
     * @param drone    The drone for which to update the map.
     * @param map      The map being used for this exploration.
     */
    @Override
    public void update(JSONObject response, Drone drone, Map map) {
        if (response.has("extras") && response.getJSONObject("extras").has("creeks")) {
            JSONArray creeks = response.getJSONObject("extras").getJSONArray("creeks");
            Coordinates droneCoordinates = drone.getCoordinates();
            for (int i = 0; i < creeks.length(); i++) {
                String creekId = creeks.getString(i);
                map.addLocation(new Coordinates(droneCoordinates.getX(), droneCoordinates.getY()), creekId, LocationType.CREEK);
            }
        }
    }
}
