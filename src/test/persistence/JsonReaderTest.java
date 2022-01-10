package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest {

    // sourced from JsonSerializationDemo
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            TrainingLog tl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTrainingLog() {
        try {
            TrainingLog tl = new TrainingLog();
            JsonWriter writer = new JsonWriter("./data/testReaderEmptyTrainingLog.json");
            writer.open();
            writer.write(tl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderEmptyTrainingLog.json");

            tl = reader.read();
            assertEquals(0, tl.activityListSize());
            assertEquals(0, tl.equipmentListSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    // sourced from JsonSerializationDemo
    @Test
    void testReaderGeneralTrainingLog() {
        try {
            TrainingLog tl = new TrainingLog();

            Equipment testShoe = new Equipment("Test Shoe", 10.0);
            Equipment extraTestShoe = new Equipment("Different Test Shoe", 20.0);

            Activity testActivityOne = new RunningActivity("00/00/0000", "Testing Running Activity",
                    "Having Fun.", 10.0, "1 hour",
                    testShoe, tl);
            Activity testActivityTwo = new StrengthActivity("01/01/1001", "Test Strength Activity",
                    "That was tough!");
            Activity testActivityThree = new RunningActivity("02/02/2002", "Testing Second Running Activity",
                    "Having Fun Again!", 11.0, "2 hours",
                    testShoe, tl);

            tl.addEquipment(extraTestShoe);
            tl.addEquipment(testShoe);

            tl.addActivity(testActivityOne);
            tl.addActivity(testActivityTwo);
            tl.addActivity(testActivityThree);


            JsonWriter writer = new JsonWriter("./data/testReaderGeneralTrainingLog.json");
            writer.open();
            writer.write(tl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderGeneralTrainingLog.json");
            tl = reader.read();

            // check if right amount of equipment and activities
            assertEquals(2, tl.equipmentListSize());
            assertEquals(3, tl.activityListSize());

            // check if activities are in right order, and remain unmodified
            assertEquals(testActivityOne.getSummaryOfActivity(), tl.getActivity(0).getSummaryOfActivity());
            assertEquals(testActivityTwo.getSummaryOfActivity(), tl.getActivity(1).getSummaryOfActivity());
            assertEquals(testActivityThree.getSummaryOfActivity(), tl.getActivity(2).getSummaryOfActivity());

            // check if equipment are in right order, and remain unchanged
            assertEquals(extraTestShoe.getName(), tl.getEquipment(0).getName());
            assertEquals(extraTestShoe.getMileage(), tl.getEquipment(0).getMileage());
            assertEquals(testShoe.getName(), tl.getEquipment(1).getName());
            assertEquals(testShoe.getMileage(), tl.getEquipment(1).getMileage());


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}