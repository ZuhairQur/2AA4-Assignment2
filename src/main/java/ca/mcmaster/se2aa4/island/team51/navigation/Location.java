package ca.mcmaster.se2aa4.island.team51.navigation;

import ca.mcmaster.se2aa4.island.team51.navigation.LocationType;

public class Location { 
    private String id; 
    private LocationType type; 

    public Location(String id, LocationType type) { 
        this.id = id; 
        this.type = type; 
    } 

    /**
     * Gets the id of the location.
     *
     * @return The id of the location.
     */
    public String getId() { 
        return id; 
    }
}