package model;

import org.json.JSONObject;
import persistence.Writable;

/**
 * Represents a piece of equipment that sustains wear, and needs to be tracked
 */

public class Equipment implements Writable {
    private String name;
    private double mileage;

    public Equipment(String name, double mileage) {
        this.name = name;
        this.mileage = mileage;
    }

    /*
    REQUIRES: None negative integer
    MODIFIES: this
    EFFECTS: adds integer amount to mileage
     */
    public void addMileage(double x) {
        if (x > 0) {
            this.mileage += x;
        }
    }

    public double getMileage() {
        return mileage;
    }

    public String getName() {
        return name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.name);
        json.put("mileage", this.mileage);
        return json;
    }
}
