/**
 * File: CostObserver.java
 * Authors: Nithika Karunamoorthy, Stella Liu, Zuhair Qureshi
 * Description: The CostObserver class implements the ResponseObserver interface and is 
 * responsible for monitoring and updating the drone's battery level based on the 
 * response received. If the response contains a cost, the observer subtracts it from 
 * the drone's battery and logs the action cost.
 */

package ca.mcmaster.se2aa4.island.team51.observers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team51.navigation.Drone;
import ca.mcmaster.se2aa4.island.team51.navigation.Map;

public class CostObserver implements ResponseObserver {
    private static final Logger logger = LogManager.getLogger(CostObserver.class);

    /**
     * Updates the battery level of the drone based on the given response.
     * If the response contains a cost, the method subtracts the cost from
     * the battery level.
     *
     * @param response A JSONObject containing the response from the drone.
     * @param drone    The drone for which to update the battery level.
     * @param map      The map being used for this exploration.
     */
    @Override
    public void update(JSONObject response, Drone drone, Map map) {
        if (response.has("cost")) {
            int cost = response.getInt("cost");
            drone.updateBattery(cost);
            logger.info("The cost of the action was {}", cost);
        }
    }
}
