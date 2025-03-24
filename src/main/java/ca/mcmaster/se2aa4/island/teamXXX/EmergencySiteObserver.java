package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONArray;
import org.json.JSONObject;

public class EmergencySiteObserver implements ResponseObserver {
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
