package ca.mcmaster.se2aa4.island.team51.observers;

import org.json.JSONArray;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team51.navigation.LocationType;
import ca.mcmaster.se2aa4.island.team51.navigation.Coordinates;
import ca.mcmaster.se2aa4.island.team51.navigation.Drone;
import ca.mcmaster.se2aa4.island.team51.navigation.Map;

public class CreekObserver implements ResponseObserver {
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
