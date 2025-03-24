package ca.mcmaster.se2aa4.island.team51.Observers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team51.Navigation.Drone;
import ca.mcmaster.se2aa4.island.team51.Navigation.Map;

public class ResponseManager {
    private List<ResponseObserver> responseObservers = new ArrayList<>();

    public void add(ResponseObserver responseObserver) {
        this.responseObservers.add(responseObserver);
    }

    public void notifyObservers(JSONObject response, Drone drone, Map map) {
        for (ResponseObserver observer : this.responseObservers) {
            observer.update(response, drone, map);
        }
    }

}
