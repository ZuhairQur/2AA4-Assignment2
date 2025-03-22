package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StatusJsonAdapter implements JsonAdapter {
    private static final Logger logger = LogManager.getLogger(StatusJsonAdapter.class);

    @Override
    public void parse(JSONObject response, Drone drone) {
        if (response.has("status")) {
            String status = response.getString("status");
            logger.info("The status of the drone is {}", status);
        }
    }
}
