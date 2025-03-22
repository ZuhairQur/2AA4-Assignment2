package ca.mcmaster.se2aa4.island.teamXXX;

import java.io.StringReader;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener; 

import eu.ace_design.island.bot.IExplorerRaid; 

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    private Integer batteryLevel; 
    private String direction;
    private Drone drone;

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

        drone = new Drone(batteryLevel);

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
        JSONObject decision = drone.makeDecision();
        logger.info("** Decision: {}",decision.toString());
        return decision.toString();
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));

        JsonParser parser = new JsonParser(List.of(
            new CostJsonAdapter(),
            new StatusJsonAdapter(),
            new CreekJsonAdapter(),
            new EmergencySiteJsonAdapter()
        ));

        parser.parseAcknowledgment(response, drone); 
    } 

    @Override
    public String deliverFinalReport() {
        logger.info("Nearest creek: {}", drone.getMap().nearestCreekToEmergencySite());
        logger.info("Creek locations: {}", drone.getMap().getCreekIds());
        logger.info("Emergency sites: {}", drone.getMap().getEmergencySiteId());
        return "no creek found";
    }

}
