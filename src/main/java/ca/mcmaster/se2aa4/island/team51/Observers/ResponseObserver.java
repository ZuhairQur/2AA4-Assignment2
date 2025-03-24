package ca.mcmaster.se2aa4.island.team51.Observers;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team51.Navigation.Drone;
import ca.mcmaster.se2aa4.island.team51.Navigation.Map;

public interface ResponseObserver {
    public void update(JSONObject response, Drone drone, Map map);
}
