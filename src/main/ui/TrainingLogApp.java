package ui;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;
import persistence.Writable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A simple training log client
 */


public class TrainingLogApp implements ActionListener {
    private static String JSON_STORE = "./data/traininglog.json";
    private TrainingLog trainingLog;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JFrame welcomePage;

    /*
    EFFECTS: Initializes the TrainingLogApp fields and starts the application
    */
    public TrainingLogApp() {

        initWelcomePanel();

    }

    /*
    EFFECTS: Loads the training log and starts the GUI for the application
     */
    private void openTrainingLogGUI() {
        this.trainingLog = new TrainingLog();
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        loadTrainingLog();

        // to revert back to text editor, uncomment the next line
        // runTrainingLog();
        new TrainingLogUserInterface(this.trainingLog, this);
    }

    /*
        MODIFIES: this
        EFFECTS: Creates a new frame where the user can continue to the main training log
                or load in from a different file
     */
    private void initWelcomePanel() {
        welcomePage = new JFrame("Training Log");
        welcomePage.setLayout(null);

        addLogoToWelcomePanel();
        addOpenButton();
        addLoadButton();

        welcomePage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        welcomePage.setTitle("Training Log");
        welcomePage.setPreferredSize(new Dimension(300,400));
        welcomePage.setLocationRelativeTo(null);
        welcomePage.pack();
        welcomePage.setVisible(true);
    }

    /*
    MODIFIES: this
    EFFECTS: adds UBC logo to welcome panel (Sourced from gothunderbirds.ca)
     */
    private void addLogoToWelcomePanel() {
        String imagePath = "./data/thunderbirdsLogo.png";
        File file = new File(imagePath);
        BufferedImage logo;
        try {
            logo = ImageIO.read(file);
            JLabel label = new JLabel(new ImageIcon(logo));
            label.setBounds(25, 0, logo.getWidth(), logo.getHeight());
            welcomePage.add(label);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
    MODIFIES: this
    EFFECTS: creates an open button
     */
    private void addOpenButton() {
        JButton openButton = new JButton("LETS GO");
        openButton.setBounds(80, 250, 100, 20);
        openButton.setActionCommand("open");
        openButton.addActionListener(this);

        welcomePage.add(openButton);
    }

    /*
    MODIFIES: this
    EFFECTS: creates a load button
     */
    private void addLoadButton() {
        JButton openButton = new JButton("LOAD");
        openButton.setBounds(80, 270, 100, 20);
        openButton.setActionCommand("load");
        openButton.addActionListener(this);

        welcomePage.add(openButton);
    }

    /*
     MODIFIES: this
     EFFECTS: processes user input and load the training log
     */
    private void runTrainingLog() {
        System.out.println("Welcome to your training log!");
        Boolean running = true;


        while (running) {
            System.out.println("Activities");
            System.out.println("_________________");

            // print out activities
            printOutActivityList();
            System.out.println("_________________");

            // print choice options and process the user input
            running = printChoiceOptions();
            saveTrainingLog();
        }

    }

    /*
    EFFECTS: Prints out each activity's date and title as well as its number in the list
    */
    private void printOutActivityList() {
        for (int i = 0; i < trainingLog.activityListSize(); i++) {
            Activity at = trainingLog.getActivity(i);
            System.out.printf("[%s] %s%n", i, at.getShortSummary());
        }
    }

    /*
    MODIFIES: this
    EFFECTS: Prints out the choices available and sends to processOptionsChoice to
            execute their function
     */
    private boolean printChoiceOptions() {
        System.out.println("Options:");
        System.out.println("[#] View Activity by Number\n" + "[g] To View Equipment\n"
                + "[a] To Add An Activity\n" + "[b] To Add Gear\n" + "[q] To Quit\n");

        // processing character options
        String option = input.nextLine();
        option = option.toLowerCase();
        return processOptionChoice(option);
    }

    /*
    EFFECTS: Takes String choice and executes their associated functions
     */
    private boolean processOptionChoice(String option) {
        switch (option) {
            case "g":
                viewEquipmentList();
                return true;
            case "a":
                addNewActivity();
                return true;
            case "b":
                addNewGear();
                return true;
            case "q": {
                System.out.println("Bye!");
                return false;
            }
        }
        // processing numerical options
        try {
            viewActivityByIndex(Integer.parseInt(option));
        } catch (Exception e) {
            System.out.println("That input is not supported, please try again");
            printChoiceOptions();
        }

        return true;
    }

    /*
    REQUIRES: Non-negative integer
    EFFECTS: Prints the full summary of the activity at index x in trainingLog
     */
    private void viewActivityByIndex(int index) {
        Activity at = trainingLog.getActivity(index);
        System.out.println(at.getSummaryOfActivity());
        System.out.println("Press enter to continue.");
        input.nextLine();
    }

    /*
    MODIFIES: this
    EFFECTS: Creates new piece of equipment with name and mileage;
    */
    private void addNewGear() {
        System.out.println("Name of Equipment:");
        String name = input.nextLine();

        int mileage;
        System.out.println("How much mileage is on it?");
        mileage = Integer.parseInt(input.nextLine());

        this.trainingLog.addEquipment(new Equipment(name, mileage));
    }

    /*
    MODIFIES: this
    EFFECTS: creates new activity based on user input and adds it to the trainingLog. If
            the activity was a running activity, add mileage to the selected shoe in
            the equipment list.
    */
    private void addNewActivity() {
        System.out.println("[a] Run activity or [b] strength activity?");
        String type = input.nextLine();

        System.out.println("Date: ");
        String date = input.nextLine();

        System.out.println("Title: ");
        String title = input.nextLine();

        System.out.println("Description: ");
        String description = input.nextLine();

        if (type.equals("a")) {
            System.out.println("Distance: ");
            double distance = Double.parseDouble(input.nextLine());

            System.out.println("Time: ");
            String time = input.nextLine();

            viewEquipmentList();
            System.out.println("Which shoe?");
            int shoeIndex = Integer.parseInt(input.nextLine());

            this.trainingLog.addActivity(new RunningActivity(date, title, description,
                    distance, time, this.trainingLog.getEquipment(shoeIndex), this.trainingLog));

            this.trainingLog.getEquipment(shoeIndex).addMileage(distance);
        } else if (type.equals("b")) {
            this.trainingLog.addActivity(new StrengthActivity(date, title, description));
        }
    }

    /*
    EFFECTS: prints the equipment list with index number, name and mileage
    */
    private void viewEquipmentList() {
        System.out.println("Equipment list:");
        for (int i = 0; i < this.trainingLog.equipmentListSize(); i++) {
            Equipment tempShoe = this.trainingLog.getEquipment(i);
            System.out.printf("[%s] %s (%s)%n", i, tempShoe.getName(),
                    tempShoe.getMileage());
        }

        System.out.println(" ");
    }

    // EFFECTS: saves the trainingLog to file
    public void saveTrainingLog() {
        try {
            jsonWriter.open();
            jsonWriter.write(this.trainingLog);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads trainingLog from file
    private void loadTrainingLog() {
        try {
            trainingLog = jsonReader.read();
            System.out.println("Loaded training log from " + JSON_STORE);

        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        } finally {
            if (trainingLog.equipmentListSize() == 0) {
                trainingLog.addEquipment(new Equipment("Default Training Shoe", 0));
            }
        }
    }

    /*
    EFFECTS: Processes button input and disposes the welcomePage if necessary
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // loads original training log or other selected
        if (e.getActionCommand().equals("open")) {
            openTrainingLogGUI();
            welcomePage.dispose();
        } else if (e.getActionCommand().equals("load")) {
            JFileChooser jfileChooser = new JFileChooser("./data");

            int resultVal = jfileChooser.showOpenDialog(welcomePage);

            if (resultVal == JFileChooser.APPROVE_OPTION) {
                String newPath = jfileChooser.getSelectedFile().getPath();
                JSON_STORE = newPath;
                openTrainingLogGUI();
                welcomePage.dispose();
            }

        }
    }
}
