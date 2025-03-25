/**
 * File: Explorer.java
 * Authors: Nithika Karunamoorthy, Stella Liu, Zuhair Qureshi
 * Description: The Explorer class implements the IExplorerRaid interface and serves as the 
 * central control system for the drone's exploration mission. It initializes the drone with 
 * the given parameters, processes decisions based on the current state of the mission, 
 * acknowledges and processes results, and delivers a final exploration report containing 
 * information about creeks and emergency sites.
 */

package ca.mcmaster.se2aa4.island.team51;

import java.io.StringReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;

import ca.mcmaster.se2aa4.island.team51.navigation.Drone;
import ca.mcmaster.se2aa4.island.team51.navigation.Map;
import ca.mcmaster.se2aa4.island.team51.observers.CostObserver;
import ca.mcmaster.se2aa4.island.team51.observers.CreekObserver;
import ca.mcmaster.se2aa4.island.team51.observers.EmergencySiteObserver;
import ca.mcmaster.se2aa4.island.team51.observers.ResponseManager;
import ca.mcmaster.se2aa4.island.team51.observers.StatusObserver;
import eu.ace_design.island.bot.IExplorerRaid; 

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    private Drone drone;
    private ResponseManager responseManager;
    private Map map = new Map(); 

    /**
     * Initializes the Exploration Command Center with the given information.
     *
     * @param s Initialization information, including the heading the drone is
     *           facing and the available battery budget.
     */
    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        String direction = info.getString("heading");
        Integer batteryLevel = info.getInt("budget");

        drone = new Drone(batteryLevel);

        responseManager = new ResponseManager();

        responseManager.add(new CostObserver());
        responseManager.add(new StatusObserver());
        responseManager.add(new CreekObserver());
        responseManager.add(new EmergencySiteObserver());

        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);
    }

    /**
     * Makes a decision based on the current state of the drone. The decision
     * will be one of "fly", "heading", or "scan", and will be influenced by the
     * drone's current battery level and the result of the last action taken.
     *
     * @return A JSONObject containing the decision to send to the drone.
     */
    @Override
    public String takeDecision() {
        JSONObject decision = drone.makeDecision(map.discoveredEmergencySite());
        logger.info("** Decision: {}",decision.toString());
        return decision.toString();
    }

    /**
     * Called after the drone has taken an action. The given string is a
     * JSONObject containing information about the results of the action.
     *
     * @param s a JSONObject containing the results of the action.
     */
    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        responseManager.notifyObservers(response, drone, map);
    } 

    /**
     * Delivers the final exploration report by logging and returning information
     * about the nearest creek, all creek locations, and emergency sites.
     *
     * @return A string containing details of the nearest creek, creeks, and
     *         emergency sites.
     */
    @Override
    public String deliverFinalReport() {
        logger.info("--------------------------------------------------------------------");
        logger.info("Nearest creek: {}", map.nearestCreekToEmergencySite());
        logger.info("Creeks: {}", map.getCreekIds());
        logger.info("Emergency sites: {}", map.getEmergencySiteIds());
        logger.info("--------------------------------------------------------------------");
        
        return "Nearest creek: " + map.nearestCreekToEmergencySite() + "\n" +
                "Creeks: " + map.getCreekIds() + "\n" +
                "Emergency sites: " + map.getEmergencySiteIds();
    }

}
