package ca.mcmaster.se2aa4.island.team51.action;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team51.navigation.Drone;

public interface Action {
    JSONObject execute(Drone drone);
    ActionType getActionType();

}



