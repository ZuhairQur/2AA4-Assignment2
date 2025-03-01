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
    private boolean stepTaken = false;
    private boolean started = false;
    private boolean scanned = false;
    private boolean shouldTurn = true;
    private int directionIndex = 2;

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        String direction = info.getString("heading");
        batteryLevel = info.getInt("budget");
        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);
    }

    @Override
    public String takeDecision() {
        JSONObject decision = new JSONObject();

        if (!this.started) {
            JSONObject direction = new JSONObject();


            direction.put("direction", "S");
            decision.put("action", "heading");
            decision.put("parameters", direction);
            decision.put("action", "fly");    
            decision.put("action", "scan");
            this.started = true;
        } 

        else {
            //decision.put("action", "fly"); // we stop the exploration immediately
            if (this.batteryLevel < 100) {
                decision.put("action", "stop");
            } else if (this.lastActionWasFly) {
                JSONObject direction = new JSONObject();
                decision.put("action", "scan");
                this.scanned = true;

                // decision.put("parameters", "direction");
                // decision.put("direction", "S");
            
                this.lastActionWasFly = false;
            }
            // } else if (this.scanned) {
            //     JSONObject direction = new JSONObject();
            //     direction.put("direction", "S");
            //     decision.put("action", "heading");
            //     decision.put("parameters", direction);
            //     this.scanned = false;
            // } 
            else {
                if (this.stepsMoved >= 20) {  // Turn every 5 steps
                    decision.put("action", "heading");
                    // decision.put("action", "SOUTH");  // Change direction (Example: Turn SOUTH)
                    this.stepsMoved = 0;
                    this.scanned = true;
                } else {
                    decision.put("action", "fly");
                    this.stepsMoved++;
                    this.lastActionWasFly = true;
                }
            }
        }



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
