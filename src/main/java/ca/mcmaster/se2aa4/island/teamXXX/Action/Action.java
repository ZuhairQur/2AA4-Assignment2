package ca.mcmaster.se2aa4.island.teamXXX.Action;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;

public interface Action {
    public JSONObject execute(Drone drone);
    public ActionType getActionType();

}



