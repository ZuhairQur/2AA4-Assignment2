package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;
import ca.mcmaster.se2aa4.island.teamXXX.Drone;

public interface JsonAdapter { 
    public void parse(JSONObject response, Drone drone); 
}