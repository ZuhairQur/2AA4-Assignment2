package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONArray;
import org.json.JSONObject;

public class EmergencySiteObserver implements ResponseObserver {
    @Override
    public void update(JSONObject response, Drone drone) {
        if (response.has("extras") && response.getJSONObject("extras").has("sites")) {
            JSONArray emergencySites = response.getJSONObject("extras").getJSONArray("sites");
            for (int i = 0; i < emergencySites.length(); i++) {
                String siteId = emergencySites.getString(i);
                drone.addLocationToMap(siteId, LocationType.EMERGENCY_SITE);
            }
        }
    }
}
