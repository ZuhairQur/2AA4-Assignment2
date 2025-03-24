/**
 * File: Location.java
 * Authors: Nithika Karunamoorthy, Stella Liu, Zuhair Qureshi
 * Description: The Location class represents a specific location identified during exploration. 
 * Each location has a unique id and a type (either CREEK or EMERGENCY_SITE). The class provides 
 * a method to retrieve the location's id.
 */

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