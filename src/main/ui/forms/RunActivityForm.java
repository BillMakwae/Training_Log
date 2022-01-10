package ui.forms;

import model.*;
import ui.TrainingLogUserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *  A GUI form for creating a RunningActivity
 */

public class RunActivityForm extends JFrame implements ActionListener {
    private TrainingLogUserInterface tlui;
    private TrainingLog trainingLog;
    private JLabel title = new JLabel("Title:");
    private JLabel date = new JLabel("Date:");
    private JLabel description = new JLabel("Description:");
    private JLabel time = new JLabel("Time:");
    private JLabel distance = new JLabel("Distance:");

    private JTextField titleField = new JFormattedTextField("");
    private JTextField dateField = new JFormattedTextField("");
    private JTextField descriptionField = new JFormattedTextField("");
    private JTextField timeField = new JFormattedTextField("");
    private JTextField distanceField = new JFormattedTextField("");

    private JList equipmentList;
    private JScrollPane equipmentChoice;

    /*
    REQUIRES: equipments must be non empty JList
    EFFECTS: Produces an interface to create a RunningActivity, as well as adds that
             new activity to the trainingLog and tlui.
     */
    public RunActivityForm(TrainingLog trainingLog, TrainingLogUserInterface tlui, JList equipments) {
        this.trainingLog = trainingLog;
        this.tlui = tlui;
        this.equipmentList = createJListCopy(equipments);
        this.equipmentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.equipmentList.setSelectedIndex(0);

        equipmentChoice = new JScrollPane(equipmentList);

        setLayout();
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Create Activity");
        this.setPreferredSize(new Dimension(400, 400));
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    /*
    MODIFIES: this
    EFFECTS: Lays out all the JLabels and textFields for the frame
     */
    public void setLayout() {
        title.setBounds(10, 10, 100, 10);
        date.setBounds(10, 50, 100, 10);
        description.setBounds(10, 90, 100, 10);
        time.setBounds(10, 130, 100, 10);
        distance.setBounds(10, 170, 100, 10);

        titleField.setBounds(140, 10, 100, 20);
        dateField.setBounds(140, 50, 100, 20);
        descriptionField.setBounds(140, 90, 100, 20);
        timeField.setBounds(140, 130, 100, 20);
        distanceField.setBounds(140, 170, 100, 20);

        equipmentChoice.setBounds(140, 200, 100, 100);

        this.add(title);
        this.add(date);
        this.add(description);
        this.add(time);
        this.add(distance);

        this.add(titleField);
        this.add(dateField);
        this.add(descriptionField);
        this.add(timeField);
        this.add(distanceField);

        this.add(equipmentChoice);

        addConfirmButton();

    }

    /*
    MODIFIES: this
    EFFECTS: adds confirm button
     */
    private void addConfirmButton() {
        JButton confirm = new JButton("CONFIRM");
        confirm.setBounds(10, 250, 100, 20);

        confirm.addActionListener(this);

        this.add(confirm);
    }

    /*
    EFFECTS: Creates a copy of the inputted JList and returns it
     */
    private JList createJListCopy(JList toBeCopied) {

        DefaultListModel listModel = new DefaultListModel();
        for (int i = 0; i < toBeCopied.getModel().getSize(); i++) {
            listModel.addElement(toBeCopied.getModel().getElementAt(i));
        }
        JList newJList = new JList(listModel);
        return newJList;
    }

    /*
    REQUIRES: trainingLog has non empty equipmentList
    MODIFIES: trainingLog, tlui
    EFFECTS: Gets values from textFields and index of equipment chosen,
            creates a new RunningActivity and adds it to trainingLog
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String title = titleField.getText();
        String date = dateField.getText();
        String description = descriptionField.getText();
        String time = timeField.getText();
        int equipmentIndex = equipmentList.getSelectedIndex();

        Double distance;
        try {
            distance = Double.parseDouble(distanceField.getText());
        } catch (Exception a) {
            distance = 0.0;
        }

        Equipment equipmentUsed = trainingLog.getEquipment(equipmentIndex);

        Activity runActivity = new RunningActivity(date, title, description, distance,
                time, equipmentUsed, trainingLog);

        equipmentUsed.addMileage(distance);

        trainingLog.addActivity(runActivity);
        tlui.getActivitiesList().addElement(runActivity.getTitle());

        this.dispose();
    }
}
