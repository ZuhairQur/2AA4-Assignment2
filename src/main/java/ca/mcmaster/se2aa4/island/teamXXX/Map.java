package ca.mcmaster.se2aa4.island.teamXXX;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class Map {
    private static final Logger logger = LogManager.getLogger(Map.class);  // Add logger here
    
    private HashMap<Coordinates, Location> creekLocations;
    private HashMap<Coordinates, Location> emergencySiteLocations;

    public Map() {
        this.creekLocations = new HashMap<>();
        this.emergencySiteLocations = new HashMap<>();
    }

    public void addLocation(Coordinates coordinates, String id, LocationType type) {
        Location location = new Location(id, type);
        if (type == LocationType.CREEK) {
            creekLocations.put(coordinates, location);
            logger.info("Creek added: {}", id);  // Add logging for creek
        } else if (type == LocationType.EMERGENCY_SITE) {
            emergencySiteLocations.put(coordinates, location);
            logger.info("Emergency site added: {}", id);  // Add logging for emergency site
        }
    }

    public HashMap<Coordinates, Location> getCreekLocations() {
        return creekLocations;
    }

    public HashMap<Coordinates, Location> getEmergencySiteLocations() {
        return emergencySiteLocations;
    }

    public boolean discoveredEmergencySite() {
        return !emergencySiteLocations.isEmpty();
    }
}
