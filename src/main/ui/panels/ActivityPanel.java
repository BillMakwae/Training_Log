package ui.panels;

import com.sun.org.apache.xpath.internal.operations.Bool;
import model.Activity;
import model.RunningActivity;

import javax.swing.*;
import java.awt.*;

/**
 * A JPanel that displays all the attributes of an activity
 */
public class ActivityPanel extends JPanel {
    private String title;
    private String date;
    private String description;
    private Activity activity;

    /*
    MODIFIES: this
    EFFECTS: creates a JPanel with all the attributes of an activity
     */
    public ActivityPanel(Activity activity) {
        title = activity.getTitle();
        date = activity.getDate();
        description = activity.getDescription();
        this.activity = activity;

        createViewPanel();
    }

    /*
    MODIFIES: this
    EFFECTS: Creates the panel with associated fields of activity
     */
    public void createViewPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addField(activity.getTitle(), 25, true);
        addField(activity.getDate(), 15, false);

        if (activity instanceof RunningActivity) {
            addField("Distance: " + Double.toString(((RunningActivity) activity).getDistance()),
                    15, false);
            addField("Time: " + ((RunningActivity) activity).getTime(),
                    15, false);
            addField("Using shoe: " + ((RunningActivity) activity).getRunningShoe().getName(),
                    15, false);
        }

        addField(activity.getDescription(), 20, false);
    }

    /*
    REQUIRES: fontSize must be > 1
    MODIFIES: this
    EFFECTS: Creates JLabel with the label string, font size and boolean bold, then adds to the panel
     */
    private void addField(String label, int fontSize, Boolean bold) {
        JLabel x = new JLabel(label);
        x.setAlignmentX(Component.LEFT_ALIGNMENT);

        int type;
        if (bold) {
            type = Font.BOLD;
        } else {
            type = Font.PLAIN;
        }

        x.setFont(new Font("", type, fontSize));
        this.add(x);
    }
}
