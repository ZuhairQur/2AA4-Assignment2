package ca.mcmaster.se2aa4.island.team51.Observers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team51.Navigation.Drone;
import ca.mcmaster.se2aa4.island.team51.Navigation.Map;

public class StatusObserver implements ResponseObserver {
    private static final Logger logger = LogManager.getLogger(StatusObserver.class);

    /**
     * Updates the status of the drone based on the given response.
     *
     * @param response A JSONObject containing the response from the drone.
     * @param drone    The drone for which to update the status.
     * @param map      The map being used for this exploration.
     */
    @Override
    public void update(JSONObject response, Drone drone, Map map) {
        if (response.has("status")) {
            String status = response.getString("status");
            logger.info("The status of the drone is {}", status);
        }
    }
}
