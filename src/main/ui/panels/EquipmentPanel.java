package ui.panels;

import model.Equipment;

import javax.swing.*;
import java.awt.*;

/**
 * A JPanel that displays the name and mileage of an Equipment
 */
public class EquipmentPanel extends JPanel {

    /*
    EFFECTS: Produces JPanel with the name and activity in the form: name ( mileage )
     */
    public EquipmentPanel(Equipment equipment) {
        JLabel name = new JLabel(equipment.getName());
        JLabel mileage = new JLabel("( " + Double.toString(equipment.getMileage()) + " )");

        name.setFont(new Font("", Font.BOLD, 20));
        mileage.setFont(new Font("", Font.BOLD, 20));

        add(name);
        add(mileage);
    }
}
