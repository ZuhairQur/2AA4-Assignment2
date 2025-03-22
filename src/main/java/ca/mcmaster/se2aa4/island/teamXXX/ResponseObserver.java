package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public interface ResponseObserver {
    public void update(JSONObject response, Drone drone);
}
