/**
 * File: Map.java
 * Authors: Nithika Karunamoorthy, Stella Liu, Zuhair Qureshi
 * Description: The Map class maintains a record of discovered locations, including creeks 
 * and emergency sites, during the drone's exploration. It provides methods for adding new 
 * locations, retrieving lists of discovered locations, determining the nearest creek to an 
 * emergency site, and checking if any emergency sites have been found.
 */


package ca.mcmaster.se2aa4.island.team51.navigation;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Map {
    
    private HashMap<Coordinates, Location> creekLocations;
    private HashMap<Coordinates, Location> emergencySiteLocations;

    public Map() {
        this.creekLocations = new HashMap<>();
        this.emergencySiteLocations = new HashMap<>();
    }

    /**
     * Adds a location to the map if it is not already known.
     *
     * @param coordinates The coordinates of the location to be added.
     * @param id          The id of the location to be added.
     * @param type        The type of the location to be added.
     */
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

    /**
     * Returns a list of all creek ids in the map.
     *
     * @return A list of creek ids.
     */
    public List<String> getCreekIds() {
        return creekLocations.values().stream()
                .map(Location::getId)
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of all emergency site ids in the map.
     *
     * @return A list of emergency site ids.
     */
    public List<String> getEmergencySiteIds() {
        return emergencySiteLocations.values().stream()
                .map(Location::getId)
                .collect(Collectors.toList());
    }

    /**
     * Returns the id of the creek nearest to any emergency site on the map. If no
     * creek or emergency site has been found, the method returns "No creek found".
     * 
     * @return The id of the creek nearest to any emergency site, or "No creek found"
     *         if no creek or emergency site has been found.
     */
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

    /**
     * Returns true if any emergency site has been discovered, false otherwise.
     *
     * @return Whether any emergency site has been discovered.
     */
    public boolean discoveredEmergencySite() {
        return !emergencySiteLocations.isEmpty();
    }

    /**
     * Checks if a location with the specified id and type exists on the map.
     *
     * @param id   The id of the location to check for.
     * @param type The type of the location (either CREEK or EMERGENCY_SITE).
     * @return true if a location with the specified id and type exists, false otherwise.
     */
    private boolean containsLocation(String id, LocationType type) {
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
