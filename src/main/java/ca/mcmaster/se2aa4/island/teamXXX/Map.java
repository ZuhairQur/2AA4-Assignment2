package ca.mcmaster.se2aa4.island.teamXXX;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Map {
    
    private HashMap<Coordinates, Location> creekLocations;
    private HashMap<Coordinates, Location> emergencySiteLocations;
    private static final Logger logger = LogManager.getLogger(Drone.class);


    public Map() {
        this.creekLocations = new HashMap<>();
        this.emergencySiteLocations = new HashMap<>();
    }

    public void addLocation(Coordinates coordinates, String id, LocationType type) {
        Location location = new Location(id, type);
        if (!containsLocation(id, type)) {
            if (type == LocationType.CREEK) {
            creekLocations.put(coordinates, location);
            } else if (type == LocationType.EMERGENCY_SITE) {
                emergencySiteLocations.put(coordinates, location);
            }
        }
       
    }

    public List<String> getCreekIds() {
        return creekLocations.values().stream()
                .map(Location::getId)
                .collect(Collectors.toList());
    }

    public List<String> getEmergencySiteIds() {
        return emergencySiteLocations.values().stream()
                .map(Location::getId)
                .collect(Collectors.toList());
    }

    public String nearestCreekToEmergencySite() {
        String nearestCreekId = null;
        double minDistance = Double.MAX_VALUE;
        
        for (Coordinates emergencyCoordinates : emergencySiteLocations.keySet()) {
            for (Coordinates creekCoordinates : creekLocations.keySet()) {
                double distance = emergencyCoordinates.distance(creekCoordinates);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestCreekId = creekLocations.get(creekCoordinates).getId();
                }
            }
        }
        
        return nearestCreekId != null ? nearestCreekId : "No creek found";
    }

    public boolean discoveredEmergencySite() {
        return !emergencySiteLocations.isEmpty();
    }

    public boolean containsLocation(String id, LocationType type) {
        if (type == LocationType.CREEK) {
            return creekLocations.values().stream()
                    .anyMatch(location -> location.getId().equals(id));
        } else if (type == LocationType.EMERGENCY_SITE) {
            return emergencySiteLocations.values().stream()
                    .anyMatch(location -> location.getId().equals(id));
        }
        return false;
    }

}
