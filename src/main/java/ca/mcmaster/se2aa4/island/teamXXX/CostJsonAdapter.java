package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ca.mcmaster.se2aa4.island.teamXXX.Drone;

public class CostJsonAdapter implements JsonAdapter {
    private static final Logger logger = LogManager.getLogger(CostJsonAdapter.class);

    @Override
    public void parse(JSONObject response, Drone drone) {
        if (response.has("cost")) {
            int cost = response.getInt("cost");
            drone.updateBattery(cost);
            logger.info("The cost of the action was {}", cost);
        }
    }
}
