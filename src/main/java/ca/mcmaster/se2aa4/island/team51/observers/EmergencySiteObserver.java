package ca.mcmaster.se2aa4.island.team51.observers;

import org.json.JSONArray;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team51.navigation.LocationType;
import ca.mcmaster.se2aa4.island.team51.navigation.Coordinates;
import ca.mcmaster.se2aa4.island.team51.navigation.Drone;
import ca.mcmaster.se2aa4.island.team51.navigation.Map;

public class EmergencySiteObserver implements ResponseObserver {
    
    /**
     * Updates the map based on the given response. If the response contains
     * the emergency sites, then the method adds the emergency sites to the
     * map with the coordinates of the drone.
     *
     * @param response A JSONObject containing the response from the drone.
     * @param drone    The drone for which to update the map.
     * @param map      The map being used for this exploration.
     */
    @Override
    public void update(JSONObject response, Drone drone, Map map) {
        if (response.has("extras") && response.getJSONObject("extras").has("sites")) {
            JSONArray emergencySites = response.getJSONObject("extras").getJSONArray("sites");
            Coordinates droneCoordinates = drone.getCoordinates();
            for (int i = 0; i < emergencySites.length(); i++) {
                String siteId = emergencySites.getString(i);
                map.addLocation(new Coordinates(droneCoordinates.getX(), droneCoordinates.getY()), siteId, LocationType.EMERGENCY_SITE);
            }
        }
    }
}
