package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StrengthActivityTest {
    StrengthActivity testStrengthActivity;

    @BeforeEach
    public void setup(){
        this.testStrengthActivity = new StrengthActivity("2021/01/06", "First Lift of the Year",
                "Had fun doing deadlifts!");
    }

    @Test
    public void testConstructor(){
        assertEquals(this.testStrengthActivity.getDate(),"2021/01/06");
        assertEquals(this.testStrengthActivity.getTitle(), "First Lift of the Year");
        assertEquals(this.testStrengthActivity.getDescription(), "Had fun doing deadlifts!");
    }

    @Test
    public void testGetShortSummary(){
        assertEquals("2021/01/06 First Lift of the Year" , this.testStrengthActivity.getShortSummary());
    }

    @Test
    public void testGetSummaryOfActivity() {
        String expected = this.testStrengthActivity.getDate() + "\n" +
                this.testStrengthActivity.getTitle() + "\n" +
                "Description: " +
                this.testStrengthActivity.getDescription() + "\n";
        assertEquals(expected,this.testStrengthActivity.getSummaryOfActivity());
    }

    @Test
    void testToJsonOfStrengthActivity() {
        JSONObject result = this.testStrengthActivity.toJson();
        assertEquals("{\"date\":\"2021/01/06\",\"description" +
                "\":\"Had fun doing deadlifts!\",\"title" +
                "\":\"First Lift of the Year\",\"type\":\"StrengthActivity" +
                "\"}", result.toString());
    }
}