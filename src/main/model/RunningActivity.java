package model;

import org.json.JSONObject;

/**
 * Represents a running activity with distance, time, and shoe used
 */

public class RunningActivity extends Activity {

    private final TrainingLog activityLocation;
    private String time;
    private double distance;
    private Equipment runningShoe;

    public RunningActivity(String date, String title, String description,
                           double distance, String time, Equipment runningShoe, TrainingLog location) {
        super(date, title, description);
        this.distance = distance;
        this.time = time;
        this.runningShoe = runningShoe;
        this.activityLocation = location;
    }

    /*
    EFFECTS: Produces string with all fields of the running activity along with the
            time, distance and gear.
     */
    @Override
    public String getSummaryOfActivity() {
        return String.format("%s\n%s\nDescription: %s\nDistance: %s\nTime: %s\nGear: %s",
                this.date, this.title, this.description, this.distance, this.time, this.runningShoe.getName());
    }

    @Override
    public JSONObject toJson() {
        JSONObject result = super.toJson();
        result =  new JSONObject(result, "date", "title", "description");
        result.put("time", this.time);
        result.put("distance", this.distance);
        result.put("runningShoe", this.activityLocation.getIndexOfEquipment(this.runningShoe));
        result.put("type", "RunningActivity");

        return result;
    }

    /*
    EFFECTS: Produces string with date, title, distance, and time
    * */
    @Override
    public String getShortSummary() {
        return super.getShortSummary() + " " + this.distance + " " + this.time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Equipment getRunningShoe() {
        return runningShoe;
    }

    public void setRunningShoe(Equipment runningShoe) {
        this.runningShoe = runningShoe;
    }
}
