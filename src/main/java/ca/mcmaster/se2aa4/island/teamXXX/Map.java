package ca.mcmaster.se2aa4.island.teamXXX;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class Map {
    private static final Logger logger = LogManager.getLogger(Map.class);  // Add logger here
    
    private HashMap<String, Location> creekLocations;
    private HashMap<String, Location> emergencySiteLocations;

    public Map() {
        this.creekLocations = new HashMap<>();
        this.emergencySiteLocations = new HashMap<>();
    }

    public void addLocation(String id, LocationType type) {
        Location location = new Location(id, type);
        if (type == LocationType.CREEK) {
            creekLocations.put(id, location);
            logger.info("Creek added: {}", id);  // Add logging for creek
        } else if (type == LocationType.EMERGENCY_SITE) {
            emergencySiteLocations.put(id, location);
            logger.info("Emergency site added: {}", id);  // Add logging for emergency site
        }
    }

    public HashMap<String, Location> getCreekLocations() {
        return creekLocations;
    }

    public HashMap<String, Location> getEmergencySiteLocations() {
        return emergencySiteLocations;
    }

    public boolean discoveredEmergencySite() {
        return !emergencySiteLocations.isEmpty();
    }
}
