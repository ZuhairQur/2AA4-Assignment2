/**
 * File: ResponseManager.java
 * Authors: Nithika Karunamoorthy, Stella Liu, Zuhair Qureshi
 * Description: The ResponseManager class manages a collection of ResponseObservers 
 * and facilitates the observer pattern by notifying all registered observers whenever 
 * a new response is received from the drone. It ensures that each observer processes 
 * the response accordingly, updating the drone's state and map as needed.
 */


package ca.mcmaster.se2aa4.island.team51.observers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team51.navigation.Drone;
import ca.mcmaster.se2aa4.island.team51.navigation.Map;

public class ResponseManager {
    private List<ResponseObserver> responseObservers = new ArrayList<>();

    /**
     * Adds an observer to the list of observers that will be notified when a new response is received from the drone.
     *
     * @param responseObserver The ResponseObserver to be added.
     */
    public void add(ResponseObserver responseObserver) {
        this.responseObservers.add(responseObserver);
    }

    /**
     * Notifies all registered observers of the new response from the drone.
     *
     * @param response A JSONObject containing the response data to be passed to observers.
     * @param drone    The drone object associated with the response.
     * @param map      The map being used, which may be updated by the observers.
     */
    public void notifyObservers(JSONObject response, Drone drone, Map map) {
        for (ResponseObserver observer : this.responseObservers) {
            observer.update(response, drone, map);
        }
    }

}
