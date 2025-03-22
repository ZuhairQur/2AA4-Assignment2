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
    private boolean lastActionWasFly = false;  // Tracks if last action was "fly"
    private int stepsMoved = 0;  // Counts steps before turning
    private String direction;
    private Drone drone = new Drone();
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
        direction = info.getString("heading");
        batteryLevel = info.getInt("budget");
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
        JSONObject decision = drone.makeDecision(batteryLevel);
        logger.info("** Decision: {}",decision.toString());
        return decision.toString();
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Response received:\n"+response.toString(2));
        logger.info(drone.getCoords());
        Integer cost = response.getInt("cost");

        batteryLevel -= cost;

        logger.info("The cost of the action was {}", cost);
        String status = response.getString("status");
        logger.info("The status of the drone is {}", status);
        JSONObject extraInfo = response.getJSONObject("extras");
        logger.info("Additional information received: {}", extraInfo);

        JsonParser parser = new JsonParser();
        parser.parseAcknowledgment(response, map, drone.getCoordinates());
        drone.updateMemory(response);
        logger.info("Creek locations: {}", map.getCreekLocations());
    }

    @Override
    public String deliverFinalReport() {
        logger.info("Nearest creek: {}", map.nearestCreekToEmergencySite());
        return "no creek found";
    }

}
