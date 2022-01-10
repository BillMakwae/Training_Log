package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {
    StrengthActivity testStrengthActivity;

    @BeforeEach
    void setUp() {
        testStrengthActivity = new StrengthActivity("20/20/20", "Showtime", "Had fun");
    }

    @Test
    void testGetSummaryOfActivity() {
        String expected = this.testStrengthActivity.getDate() + "\n" +
                this.testStrengthActivity.getTitle() + "\n" +
                "Description: " +
                this.testStrengthActivity.getDescription() + "\n";
        assertEquals(expected, this.testStrengthActivity.getSummaryOfActivity());
    }

}