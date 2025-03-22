package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONArray;
import org.json.JSONObject;

public class EmergencySiteJsonAdapter implements JsonAdapter {
    @Override
    public void parse(JSONObject response, Drone drone) {
        if (response.has("extras") && response.getJSONObject("extras").has("sites")) {
            JSONArray emergencySites = response.getJSONObject("extras").getJSONArray("sites");
            Coordinates droneCoordinates = drone.getCoordinates();
            for (int i = 0; i < emergencySites.length(); i++) {
                String siteId = emergencySites.getString(i);
                drone.getMap().addLocation(new Coordinates(droneCoordinates.getX(), droneCoordinates.getY()), siteId, LocationType.EMERGENCY_SITE);
            }
        }
    }
}
