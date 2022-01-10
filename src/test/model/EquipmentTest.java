package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentTest {
    Equipment testShoe;

    @BeforeEach
    void setUp() {
        this.testShoe = new Equipment("Adidas Boston 9", 50);
    }

    @Test
    void addMileage() {
        this.testShoe.addMileage(30);
        assertEquals(this.testShoe.getMileage(), 80);
    }
}