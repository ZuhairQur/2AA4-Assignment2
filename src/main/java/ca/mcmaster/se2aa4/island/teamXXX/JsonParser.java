package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONArray;
import org.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JsonParser { 
    // Make the logger static to match the static context
    private static final Logger logger = LogManager.getLogger(JsonParser.class);
    
    public static void parseAcknowledgment(JSONObject response, Map map) {
        if (response.has("data")) {
            JSONObject data = response.getJSONObject("data");
            if (data.has("extras")) {
                JSONObject extras = data.getJSONObject("extras");
                
                // Extracting creeks
                if (extras.has("creeks")) {
                    JSONArray creeks = extras.getJSONArray("creeks");
                    logger.debug("Found creeks: {}", creeks.toString());  // Debug log
                    for (int i = 0; i < creeks.length(); i++) {
                        String creekId = creeks.getString(i);
                        map.addLocation(creekId, LocationType.CREEK);
                    }
                }

                // Extracting emergency sites
                if (extras.has("sites")) {
                    JSONArray emergencySites = extras.getJSONArray("sites");
                    for (int i = 0; i < emergencySites.length(); i++) {
                        String siteId = emergencySites.getString(i);
                        map.addLocation(siteId, LocationType.EMERGENCY_SITE);
                    }
                }
            }
        }
    } 
}
