package persistence;

import model.Equipment;
import model.RunningActivity;
import model.StrengthActivity;
import model.TrainingLog;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// sourced from JsonSerializationDemo
// Represents a reader that reads a TrainingLog from JSON data store in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TrainingLog read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTrainingLog(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses TrainingLog from JSON object and returns it
    private TrainingLog parseTrainingLog(JSONObject jsonObject) {
        TrainingLog tl = new TrainingLog();
        addEquipments(tl, jsonObject);
        addActivities(tl, jsonObject);

        return tl;
    }

    // MODIFIES: tl
    // EFFECTS: parses Activities from JSON object and adds them to TrainingLog
    private void addActivities(TrainingLog tl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Activities");
        for (Object json : jsonArray) {
            JSONObject next = (JSONObject) json;
            addActivity(tl, next);
        }
    }

    // MODIFIES: tl
    // EFFECTS: parses Activity from JSON object and adds it to TrainingLog
    private void addActivity(TrainingLog tl, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String date = jsonObject.getString("date");
        String description = jsonObject.getString("description");

        if (jsonObject.getString("type").equals("StrengthActivity")) {
            tl.addActivity(new StrengthActivity(date, title, description));

        } else if (jsonObject.getString("type").equals("RunningActivity")) {

            String time = jsonObject.getString("time");
            Double distance = jsonObject.getDouble("distance");
            int shoeObject = jsonObject.getInt("runningShoe");
            Equipment shoe = tl.getEquipment(shoeObject);

            tl.addActivity(new RunningActivity(date, title, description, distance, time, shoe, tl));
        }


    }

    // MODIFIES: tl
    // EFFECTS: parses Equipments from JSON object and adds them to trainingLog
    private void addEquipments(TrainingLog tl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Equipment");
        for (Object json : jsonArray) {
            JSONObject next = (JSONObject) json;
            addEquipment(tl, next);
        }
    }

    // MODIFIES: tl
    // EFFECTS: parses Equipment form JSON object and adds it to TrainingLog
    private void addEquipment(TrainingLog tl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Double mileage = jsonObject.getDouble("mileage");

        tl.addEquipment(new Equipment(name, mileage));
    }
}
