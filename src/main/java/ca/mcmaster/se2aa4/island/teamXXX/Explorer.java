package ca.mcmaster.se2aa4.island.teamXXX;

import java.io.StringReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener; 

import eu.ace_design.island.bot.IExplorerRaid; 

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    private Integer batteryLevel; 
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
        batteryLevel = info.getInt("budget");

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

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        responseManager.notifyObservers(response, drone, map);
    } 

    @Override
    public String deliverFinalReport() {
        logger.info("Nearest creek: {}", map.nearestCreekToEmergencySite());
        logger.info("Creeks: {}", map.getCreekIds());
        logger.info("Emergency sites: {}", map.getEmergencySiteIds());
        
        return "Nearest creek: " + map.nearestCreekToEmergencySite() + "\n" +
                "Creeks: " + map.getCreekIds() + "\n" +
                "Emergency sites: " + map.getEmergencySiteIds();
    }

}
