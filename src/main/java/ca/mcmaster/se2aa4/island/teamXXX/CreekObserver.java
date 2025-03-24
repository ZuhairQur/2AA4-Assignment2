package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONArray;
import org.json.JSONObject;

public class CreekObserver implements ResponseObserver {
    @Override
    public void update(JSONObject response, Drone drone) {
        if (response.has("extras") && response.getJSONObject("extras").has("creeks")) {
            JSONArray creeks = response.getJSONObject("extras").getJSONArray("creeks");
            for (int i = 0; i < creeks.length(); i++) {
                String creekId = creeks.getString(i);
                drone.addLocationToMap(creekId, LocationType.CREEK);
            }
        }
    }
}
