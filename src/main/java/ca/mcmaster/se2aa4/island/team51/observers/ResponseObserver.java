package ca.mcmaster.se2aa4.island.team51.observers;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team51.navigation.Drone;
import ca.mcmaster.se2aa4.island.team51.navigation.Map;

public interface ResponseObserver {
    void update(JSONObject response, Drone drone, Map map);
}
