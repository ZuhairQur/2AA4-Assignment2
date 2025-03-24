package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StatusObserver implements ResponseObserver {
    private static final Logger logger = LogManager.getLogger(StatusObserver.class);

    @Override
    public void update(JSONObject response, Drone drone, Map map) {
        if (response.has("status")) {
            String status = response.getString("status");
            logger.info("The status of the drone is {}", status);
        }
    }
}
