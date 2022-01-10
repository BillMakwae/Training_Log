package ui.forms;

import model.Equipment;
import model.TrainingLog;
import ui.TrainingLogUserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A GUI form for creating a piece of equipment
 */

public class EquipmentForm extends JFrame {
    private TrainingLog trainingLog;
    private TrainingLogUserInterface tlui;
    private JTextField nameField;
    private JTextField mileageField;

    /*
    MODIFIES: this, trainingLog, tlui
    EFFECTS: Creates new frame to input information to create a piece of equipment
     */
    public EquipmentForm(TrainingLog trainingLog, TrainingLogUserInterface tlui) {
        this.trainingLog = trainingLog;
        this.tlui = tlui;

        setLayout();
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Create Activity");
        this.setPreferredSize(new Dimension(400, 300));
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    /*
    MODIFIES: this
    EFFECTS: Lays out all the components for the JFrame
     */
    private void setLayout() {
        JLabel name = new JLabel("Name:");
        JLabel mileage = new JLabel("Mileage:");

        nameField = new JFormattedTextField("");
        mileageField = new JFormattedTextField("");

        name.setBounds(10, 10, 100, 10);
        mileage.setBounds(10, 50, 100, 10);

        nameField.setBounds(140, 10, 100, 20);
        mileageField.setBounds(140, 50, 100, 20);

        this.add(name);
        this.add(mileage);

        this.add(nameField);
        this.add(mileageField);

        addConfirmButton();

    }

    /*
    MODIFIES: this, tlui, trainingLog
    EFFECTS: Adds confirm button to frame and adds action listener that creates and inputs new Equipment
            adds the equipment to the trainingLog and adds the title of the equipment to the equipmentList
             of the tlui
     */
    private void addConfirmButton() {
        JButton confirm = new JButton("CONFIRM");
        confirm.setBounds(100, 120, 100, 20);
        EquipmentForm openedForm = this;

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                Double mileage;

                try {
                    mileage = Double.parseDouble(mileageField.getText());
                } catch (Exception a) {
                    mileage = 0.0;
                }


                Equipment equipment = new Equipment(name, mileage);
                trainingLog.addEquipment(equipment);
                tlui.getEquipmentList().addElement(equipment.getName());

                openedForm.dispose();
            }
        });

        this.add(confirm);
    }
}
