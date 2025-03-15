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

    @Override
    public String takeDecision() {
        JSONObject decision = drone.makeDecision(batteryLevel);
        // JSONObject dir = new JSONObject();
        // //decision.put("action", "fly"); // we stop the exploration immediately
        //  if (batteryLevel < 100) {
        //     decision.put("action", "stop");
        // } else if (lastActionWasFly) {
        //     decision.put("action", "scan");
        //     lastActionWasFly = false;
        // } else {
        //     lastActionWasFly = true;
        //     stepsMoved++;
        //     if (stepsMoved >= 40) {  // Turn every 5 steps
        //         decision.put("action", "heading");
        //         if ("SOUTH".equals(direction) || "S".equals(direction)) {
        //             dir.put("direction", "E");
        //         } 
        //         else{
        //             dir.put("direction", "S");
        //         }
        //         decision.put("parameters", dir);  // Change direction (Example: Turn SOUTH)
        //         direction = dir.getString("direction");
        //         stepsMoved = 0;
        //         lastActionWasFly = true;
        //     } else {
        //         decision.put("action", "fly");
        //     }
        // }
        
        
        // } else {
        //     if (stepsMoved >= 5) {  // Turn every 5 steps
        //         decision.put("action", "heading");
        //         if ("SOUTH".equals(direction) || "S".equals(direction)) {
        //             dir.put("direction", "E");
        //         } 
        //         else{
        //             logger.info(direction);
        //             dir.put("direction", "S");
        //         }
        //         decision.put("parameters", dir);  // Change direction (Example: Turn SOUTH)
        //         direction = dir.getString("direction");
        //         stepsMoved = 0;
        //         lastActionWasFly = true;
        //     } else {
        //         decision.put("action", "fly");
        //         stepsMoved++;
        //     }



        logger.info("** Decision: {}",decision.toString());
        return decision.toString();
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Response received:\n"+response.toString(2));
        Integer cost = response.getInt("cost");

        batteryLevel -= cost;

        logger.info("The cost of the action was {}", cost);
        String status = response.getString("status");
        logger.info("The status of the drone is {}", status);
        JSONObject extraInfo = response.getJSONObject("extras");
        logger.info("Additional information received: {}", extraInfo);
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

}
