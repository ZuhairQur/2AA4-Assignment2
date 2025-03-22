package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JsonParser {
    private static final Logger logger = LogManager.getLogger(JsonParser.class);
    private final List<JsonAdapter> adapters;

    public JsonParser(List<JsonAdapter> adapters) {
        this.adapters = adapters;
    }

    public void parseAcknowledgment(JSONObject response, Drone drone) {
        logger.info("** Response received:\n{}", response.toString(2));
        logger.info("Drone coordinates: {}", drone.getCoordinates());

        for (JsonAdapter adapter : adapters) {
            adapter.parse(response, drone);
        }
    }
}
