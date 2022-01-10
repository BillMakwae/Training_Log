package ui;

import model.Activity;
import model.Equipment;
import model.TrainingLog;
import ui.forms.ActivityForm;
import ui.forms.EquipmentForm;
import ui.forms.RunActivityForm;
import ui.panels.ActivityPanel;
import ui.panels.EquipmentPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A graphical user interface for a given trainingLog
 */

public class TrainingLogUserInterface extends JFrame implements ListSelectionListener, ActionListener {
    private TrainingLogApp trainingLogApp;
    private TrainingLog trainingLog;
    private JPanel overallPanel;
    private DefaultListModel activitiesList;
    private DefaultListModel equipmentList;
    private JPanel leftPanel;
    private JPanel middlePanel;
    private JPanel rightPanel;
    private JPanel middleSubPanelActivity;
    private JPanel middleSubPanelEquipment;
    private JScrollPane middleSubScrollPaneActivity;
    private JScrollPane middleSubScrollPaneEquipment;
    private JButton saveBtn = new JButton("SAVE");
    private JButton addRunActivityBtn = new JButton("ADD RUN");
    private JButton addActivityBtn = new JButton("ADD ACTIVITY");
    private JButton addGearBtn = new JButton("ADD GEAR");
    private JButton quitBtn = new JButton("QUIT");
    private final Dimension minSize = new Dimension(200, 100);
    private JList equipments;
    private JList activities;

    /*
    MODIFIES: This
    EFFECTS: initializes the graphical user interface
     */
    public TrainingLogUserInterface(TrainingLog trainingLog, TrainingLogApp trainingLogApp) {
        this.trainingLog = trainingLog;
        this.trainingLogApp = trainingLogApp;
        this.overallPanel = new JPanel();
        init();

    }

    /*
    MODIFIES: this
    EFFECTS: initializes the JFrame
     */
    private void init() {
        loadActivitiesAndEquipments();
        createLeftActivitiesAndGearPanel();
        createMiddleViewPanel();
        createRightButtonsPanel();

        this.getContentPane().add(overallPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Training Log");
        this.setSize(500,400);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }




    /*
    MODIFIES: this
    EFFECTS: loads the activities and equipment from the training log
     */
    public void loadActivitiesAndEquipments() {
        activitiesList = new DefaultListModel();

        for (int i = 0; i < trainingLog.activityListSize(); i++) {
            activitiesList.addElement(trainingLog.getActivity(i).getTitle());
        }

        equipmentList = new DefaultListModel();

        for (int i = 0; i < trainingLog.equipmentListSize(); i++) {
            equipmentList.addElement(trainingLog.getEquipment(i).getName());
        }
    }

    /*
    MODIFIES: this
    EFFECTS: creates the left activities and gear panels
     */
    private void createLeftActivitiesAndGearPanel() {
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        // creating the activity and gear scroll panes with a minimum size
        activities = new JList(activitiesList);
        activities.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        activities.setSelectedIndex(0);
        activities.addListSelectionListener(this);
        JScrollPane activityScrollPane = new JScrollPane(activities);

        equipments = new JList(equipmentList);
        equipments.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //equipments.setSelectedIndex(0);
        equipments.addListSelectionListener(this);
        JScrollPane equipmentScrollPane = new JScrollPane(equipments);

        activityScrollPane.setMinimumSize(minSize);
        equipmentScrollPane.setMinimumSize(minSize);

        // creating layout of: Title->ScrollPane->Title->ScrollPane

        leftPanel.add(new JLabel("Activities"));
        leftPanel.add(activityScrollPane);
        leftPanel.add(new JLabel("Equipment"));
        leftPanel.add(equipmentScrollPane);

        overallPanel.add(leftPanel);

    }

    /*
    MODIFIES: This
    EFFECTS: Produces two panels within the middle panel: one to display the
            activity(middleSubPanelActivity), and one to display the
            equipment(middleSubPanelEquipment)
     */
    private void createMiddleViewPanel() {
        middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        middlePanel.setPreferredSize(new Dimension(300, 400));

        middleSubPanelActivity = new JPanel();
        middleSubPanelEquipment = new JPanel();

        middleSubPanelActivity.setMinimumSize(minSize);
        middleSubPanelEquipment.setMinimumSize(minSize);

        middleSubPanelActivity.setPreferredSize(new Dimension(200, -1));
        middleSubPanelEquipment.setPreferredSize(minSize);

        //middlePanel.add(middleSubPanelActivity);
        //middlePanel.add(middleSubPanelEquipment);

        middleSubScrollPaneActivity = new JScrollPane(middleSubPanelActivity);
        middleSubScrollPaneEquipment = new JScrollPane(middleSubPanelEquipment);

        middleSubScrollPaneActivity.setPreferredSize(new Dimension(150, 200));
        middleSubScrollPaneEquipment.setPreferredSize(new Dimension(150, 200));

        middlePanel.add(middleSubScrollPaneActivity);
        middlePanel.add(middleSubScrollPaneEquipment);

        overallPanel.add(middlePanel);
    }

    /*
    MODIFIES: This
    EFFECTS: Creates right panel with the buttons SAVE, ADD ACTIVITY, ADD GEAR,
            and QUIT, and adds their associated actionListener
     */
    private void createRightButtonsPanel() {
        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        TrainingLogUserInterface tlui = this;

        addActivityBtn.setActionCommand("addActivity");
        addActivityBtn.addActionListener(this);

        saveBtn.setActionCommand("save");
        saveBtn.addActionListener(this);

        addRunActivityBtn.setActionCommand("addRunActivity");
        addRunActivityBtn.addActionListener(this);

        addGearBtn.setActionCommand("addGear");
        addGearBtn.addActionListener(this);

        quitBtn.setActionCommand("quit");
        quitBtn.addActionListener(this);
//        addActivityBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new ActivityForm(trainingLog, tlui);
//            }
//        });
//
//        saveBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                trainingLogApp.saveTrainingLog();
//            }
//        });
//
//        addRunActivityBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new RunActivityForm(trainingLog, tlui, equipments);
//            }
//        });
//
//        addGearBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new EquipmentForm(trainingLog, tlui);
//            }
//        });
//
//        quitBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                tlui.dispose();
//            }
//        });

        addButtonsToRightPanel();

    }

    /*
    MODIFIES: this
    EFFECTS: Adds buttons to right panel and then adds the right panel to overall
            panel
     */
    private void addButtonsToRightPanel() {
        rightPanel.add(saveBtn);
        rightPanel.add(addRunActivityBtn);
        rightPanel.add(addActivityBtn);
        rightPanel.add(addGearBtn);
        rightPanel.add(quitBtn);

        overallPanel.add(rightPanel);
    }

    /*
    MODIFIES: this
    EFFECTS: updates the information panels in the middle panel when change is
            detected
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList list = (JList)e.getSource();
        if (list.equals(activities)) {
            updateMiddleActivityPanel(trainingLog.getActivity(list.getSelectedIndex()));

        } else if (list.equals(equipments)) {
            updateMiddleEquipmentPanel(trainingLog.getEquipment(list.getSelectedIndex()));
        }


    }

    /*
    MODIFIES: this
    EFFECTS: Creates new Activity panel that reflects inputted activity and then
            updates the activity scroll information panel in the middle panel
     */
    private void updateMiddleActivityPanel(Activity activity) {
        middleSubPanelActivity = new ActivityPanel(activity);
        middleSubScrollPaneActivity.getViewport().setView(middleSubPanelActivity);

    }

    /*
    MODIFIES: this
    EFFECTS: Creates new EquipmentPanel that reflects inputted equipment and then
            updates the equipment scroll information panel in the middle panel
     */
    private void updateMiddleEquipmentPanel(Equipment equipment) {
        middleSubPanelEquipment = new EquipmentPanel(equipment);
        middleSubScrollPaneEquipment.getViewport().setView(middleSubPanelEquipment);
    }

    public DefaultListModel getActivitiesList() {
        return activitiesList;
    }

    public DefaultListModel getEquipmentList() {
        return equipmentList;
    }

    /*
    MODIFIES: this, trainingLogApp
    EFFECTS: Processes button input and does corresponding action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String event = e.getActionCommand();
        if (event.equals("addActivity")) {
            new ActivityForm(trainingLog, this);

        } else if (event.equals("save")) {
            trainingLogApp.saveTrainingLog();

        } else if (event.equals("addRunActivity")) {
            new RunActivityForm(trainingLog, this, equipments);

        } else if (event.equals("addGear")) {
            new EquipmentForm(trainingLog, this);

        } else if (event.equals("quit")) {
            this.dispose();
        }

    }
}
