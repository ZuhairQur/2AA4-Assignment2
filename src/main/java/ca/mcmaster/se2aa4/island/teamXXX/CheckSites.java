/*package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class CheckSites {
    private Scan scan = new Scan();
    private JSONObject instruction;
    private HashMap<String, Object> scannedLandMarks = new HashMap();
    
    public void CheckSites(Scan scan, JSONObject instruction, Drone drone) {
        this.scan = scan;
        this.instruction = scan.execute(drone);

    }

    public parseJSONObject() {
        JSONParser parser = new JSONParser;
        JSONObject instruction = new JSONObject();
    }

    public HashMap getScannedResults() {

    }


    public void storeLandMarkInfo() {
        scannedLandMarks = getScannedResults();
        getLandMarkInfo();
        for (HashMap landMark : landMarks) {
            if (scannedLandMarks.get("uid") == landMark.get("creek")[0]) {

            }
        }
    }

}


/*if creek has an id,
 *      then call store id and coordinates method
 * 
 * same implementation with emergency site
 * 
 * first need to figure out how to get coordinate -- from json file?
 */