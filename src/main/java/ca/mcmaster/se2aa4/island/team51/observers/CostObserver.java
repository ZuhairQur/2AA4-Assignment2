package ca.mcmaster.se2aa4.island.team51.observers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team51.navigation.Drone;
import ca.mcmaster.se2aa4.island.team51.navigation.Map;

public class CostObserver implements ResponseObserver {
    private static final Logger logger = LogManager.getLogger(CostObserver.class);

    @Override
    public void update(JSONObject response, Drone drone, Map map) {
        if (response.has("cost")) {
            int cost = response.getInt("cost");
            drone.updateBattery(cost);
            logger.info("The cost of the action was {}", cost);
        }
    }
}
