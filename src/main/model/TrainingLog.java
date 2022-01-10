package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

/**
 * A training log that stores activities and equipment
 */
public class TrainingLog implements Writable {
    private ArrayList<Activity> activityList;
    private ArrayList<Equipment> equipmentList;

    // EFFECTS: Initializes the training log
    public TrainingLog() {
        this.activityList = new ArrayList<>();
        this.equipmentList = new ArrayList<>();

    }

    // MODIFIES: this
    // EFFECTS: Adds activity to activityList
    public void addActivity(Activity activity) {
        this.activityList.add(activity);
    }

    // MODIFIES: this
    // EFFECTS: Adds equipment to equipmentList
    public void addEquipment(Equipment equipment) {
        this.equipmentList.add(equipment);
    }

    // REQUIRES: Positive integer i, within the size of the activityList
    // EFFECTS: returns activity at index i of activityList
    public Activity getActivity(int i) {
        return this.activityList.get(i);
    }

    // REQUIRES: Positive integer i, within the size of equipmentList
    // EFFECTS: returns equipment at index i of equipmentList
    public Equipment getEquipment(int i) {
        return this.equipmentList.get(i);
    }

    // EFFECTS: returns activityList size
    public int activityListSize() {
        return activityList.size();
    }

    // EFFECTS: returns equipmentList size
    public int equipmentListSize() {
        return equipmentList.size();
    }

    // Sourced from JsonSerializationDemo
    // EFFECTS: returns activityList as a JSON array
    private JSONArray activityListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Activity a : this.activityList) {
            jsonArray.put(a.toJson());
        }

        return jsonArray;
    }

    // Sourced from JsonSerializationDemo
    // EFFECTS: returns equipmentList as a JSON array
    private JSONArray equipmentListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Equipment e : this.equipmentList) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Activities", activityListToJson());
        json.put("Equipment", equipmentListToJson());

        return json;
    }

    // EFFECTS: returns index of equipment item in equipmentList if present
    //          otherwise returns -1 if can not be found
    public int getIndexOfEquipment(Equipment e) {
        if (this.equipmentList.contains(e)) {
            return this.equipmentList.indexOf(e);
        } else {
            return -1;
        }
    }
}
