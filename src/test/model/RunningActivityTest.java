package model;


import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class RunningActivityTest {
    private TrainingLog trainingLog;
    private RunningActivity testRunningActivity;
    private Equipment runningShoe;
    private Equipment extraRunningShoe;

    @BeforeEach
    void setup(){
        this.trainingLog = new TrainingLog();
        this.extraRunningShoe = new Equipment("Test", 10);
        this.runningShoe = new Equipment("New Balance 1080", 0);

        this.testRunningActivity = new RunningActivity("2021/01/05", "First Run of the Year",
                "Ran with the team today", 10, "60 mins", this.runningShoe, this.trainingLog);
        this.trainingLog.addEquipment(extraRunningShoe);
        this.trainingLog.addEquipment(runningShoe);
    }

    @Test
    void testConstructor(){
        assertEquals(this.testRunningActivity.getDate(), "2021/01/05");
        assertEquals(this.testRunningActivity.getTitle(), "First Run of the Year");
        assertEquals(this.testRunningActivity.getDescription(), "Ran with the team today");
        assertEquals(this.testRunningActivity.getDistance(), 10.0);
        assertEquals(this.testRunningActivity.getTime(), "60 mins");
    }
    @Test
    void testGetSummaryOfActivity() {
        assertEquals(this.testRunningActivity.getSummaryOfActivity(),
                "2021/01/05\nFirst Run of the Year\nDescription: Ran with the team today\n" +
                        "Distance: 10.0\nTime: 60 mins\nGear: New Balance 1080");
    }

    @Test
    void testUpdatingFieldsOfActivity() {
        String newTitle = "Running with the fam";
        String newDate = "2021/01/05";
        String newDescription = "Rolled my ankle a little bit :(";
        double newDistance = 9.50;
        String newTime = "45 mins";
        Equipment newShoes = new Equipment("Nike Infinity", 0);

        this.testRunningActivity.setTitle(newTitle);
        this.testRunningActivity.setDate(newDate);
        this.testRunningActivity.setDescription(newDescription);
        this.testRunningActivity.setDistance(newDistance);
        this.testRunningActivity.setTime(newTime);
        this.testRunningActivity.setRunningShoe(newShoes);

        assertEquals(this.testRunningActivity.getTitle(), newTitle);
        assertEquals(this.testRunningActivity.getDate(), newDate);
        assertEquals(this.testRunningActivity.getDescription(), newDescription);
        assertEquals(this.testRunningActivity.getDistance(), newDistance);
        assertEquals(this.testRunningActivity.getTime(), newTime);
        assertEquals(this.testRunningActivity.getRunningShoe(), newShoes);
    }


    @Test
    void testGetShortSummary() {
        RunningActivity ra = this.testRunningActivity;
        String result = ra.getDate() + " " + ra.getTitle() + " " + ra.getDistance() + " " + ra.getTime();
        assertEquals(result, this.testRunningActivity.getShortSummary());
    }

    @Test
    void testToJson() {
        JSONObject result = this.testRunningActivity.toJson();
        assertEquals(result.get("date"), this.testRunningActivity.getDate());
        assertEquals(result.get("distance"), this.testRunningActivity.getDistance());
        assertEquals(result.get("description"), this.testRunningActivity.getDescription());
        assertEquals(result.get("time"), this.testRunningActivity.getTime());
        assertEquals(result.get("title"), this.testRunningActivity.getTitle());
        assertEquals(result.get("type"), "RunningActivity");
        assertEquals(result.get("runningShoe"), 1);

    }
}
