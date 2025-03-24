package ca.mcmaster.se2aa4.island.team51.Action;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team51.Drone;

public interface Action {
    public JSONObject execute(Drone drone);
    public ActionType getActionType();

}



