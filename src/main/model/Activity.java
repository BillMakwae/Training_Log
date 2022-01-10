package model;

import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalTime;

/**
 * Abstract class that holds basic information about the class
 */

public abstract class Activity implements Writable {
    protected String date;
    protected String title;
    protected String description;

    public Activity(String date, String title, String description) {
        this.date = date;
        this.title = title;
        this.description = description;
    }

    /*
     * EFFECTS: returns formated string of the fields of the activity
     * */
    public abstract String getSummaryOfActivity();

    /*
    EFFECTS : returns fields of activity in the form of JSONObject
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("description", this.description);
        json.put("date", this.date);
        json.put("title", this.title);
        return json;
    }

    /*
    * EFFECTS: returns short summary of activity
    * */
    public String getShortSummary() {
        return (this.date + " " + this.title);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }







}
