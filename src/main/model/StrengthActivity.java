package model;

import org.json.JSONObject;

/**
 * Represents a lifting session in a gym
 */

public class StrengthActivity extends Activity {
    public StrengthActivity(String date, String title, String description) {
        super(date, title, description);
    }

    @Override
    public String getSummaryOfActivity() {
        return String.format("%s\n%s\nDescription: %s\n",
                this.date, this.title, this.description);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("type", "StrengthActivity");

        return json;
    }
}
