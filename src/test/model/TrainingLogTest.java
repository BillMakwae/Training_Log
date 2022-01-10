package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainingLogTest {
    TrainingLog testTrainingLog;
    Equipment testEquipment;

    @BeforeEach
    void setUp() {
        this.testTrainingLog = new TrainingLog();
        this.testEquipment = new Equipment("default", 10);
        this.testTrainingLog.addEquipment(testEquipment);
        this.testTrainingLog.addActivity(new RunningActivity("00/00/00", "Example",
                "Demonstrating the class", 10.0, "30 mins",
                this.testTrainingLog.getEquipment(0), this.testTrainingLog));
    }

    @Test
    void testToJson() {
        JSONObject result = testTrainingLog.toJson();
        assertEquals(testTrainingLog.activityListSize(), result.getJSONArray("Activities").length());
        assertEquals(testTrainingLog.equipmentListSize(), result.getJSONArray("Equipment").length());

    }

    @Test
    void testGetIndexOfEquipment() {
        assertEquals(0, this.testTrainingLog.getIndexOfEquipment(testEquipment));
        assertEquals(-1 , this.testTrainingLog.getIndexOfEquipment(new Equipment("None", 0)));
    }
}