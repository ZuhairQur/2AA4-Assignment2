package ca.mcmaster.se2aa4.island.teamXXX;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class ResponseManager {
    private List<ResponseObserver> responseObservers = new ArrayList<>();

    public void add(ResponseObserver responseObserver) {
        this.responseObservers.add(responseObserver);
    }

    public void notifyObservers(JSONObject response, Drone drone) {
        for (ResponseObserver observer : this.responseObservers) {
            observer.update(response, drone);
        }
    }

}
