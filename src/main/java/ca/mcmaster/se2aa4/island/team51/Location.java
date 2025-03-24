package ca.mcmaster.se2aa4.island.team51;

public class Location { 
    private String id; 
    private LocationType type; 

    public Location(String id, LocationType type) { 
        this.id = id; 
        this.type = type; 
    } 

    public String getId() { 
        return id; 
    }
}