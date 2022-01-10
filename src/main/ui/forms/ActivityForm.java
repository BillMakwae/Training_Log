package ui.forms;

import model.Activity;
import model.StrengthActivity;
import model.TrainingLog;
import ui.TrainingLogUserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A GUI form for creating a basic activity
 */

public class ActivityForm extends JFrame {
    private TrainingLog trainingLog;
    private TrainingLogUserInterface tlui;
    private JTextField titleField;
    private JTextField dateField;
    private JTextField descriptionField;

    /*
    MODIFIES: this
    EFFECTS: Creates new frame to input information to create a basic activity
     */
    public ActivityForm(TrainingLog trainingLog, TrainingLogUserInterface tlui) {
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
    MODIFIES:this
    EFFECTS: Lays out all the components for the JFrame
     */
    public void setLayout() {
        JLabel title = new JLabel("Title:");
        JLabel date = new JLabel("Date:");
        JLabel description = new JLabel("Description:");

        titleField = new JFormattedTextField("");
        dateField = new JFormattedTextField("");
        descriptionField = new JFormattedTextField("");

        title.setBounds(10, 10, 100, 10);
        date.setBounds(10, 50, 100, 10);
        description.setBounds(10, 90, 100, 10);

        titleField.setBounds(140, 10, 100, 20);
        dateField.setBounds(140, 50, 100, 20);
        descriptionField.setBounds(140, 90, 100, 20);

        this.add(title);
        this.add(date);
        this.add(description);

        this.add(titleField);
        this.add(dateField);
        this.add(descriptionField);

        addConfirmButton();

    }

    /*
    MODIFIES: this, tlui, trainingLog
    EFFECTS: Adds confirm button to frame and adds action listener that creates and inputs new strength activity
            adds the activity to the trainingLog and adds the title of the activity to the activitiesList
             of the tlui.
     */
    private void addConfirmButton() {
        JButton confirm = new JButton("CONFIRM");
        confirm.setBounds(50, 120, 100, 20);
        ActivityForm openedForm = this;

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String date = dateField.getText();
                String description = descriptionField.getText();

                Activity strengthActivity = new StrengthActivity(date, title, description);
                trainingLog.addActivity(strengthActivity);
                tlui.getActivitiesList().addElement(strengthActivity.getTitle());

                openedForm.dispose();
            }
        });

        this.add(confirm);
    }
}
