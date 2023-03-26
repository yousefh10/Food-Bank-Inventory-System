package edu.ucalgary.ensf409;

/** 
 * @author Issam Akhtar, Yousef Hammad, Bilal Pasha
 * @version 1.6
 * @since 1.0
 * 
 * The GUI.java class is responsible for prompting the user for their first and last name, as well as the overall order.
 * This class includes error checking, in the case where the user has an invalid order.
 * After the user fills out their name, they will fill out the total people per family and the amount of hampers needed.
 * The order text file will then print, letting the user know the following information: Name, Date, Hamper Request made 
 * listing the total members per family and the total hamper items.
*/

//javac -cp .:lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/GUI.java
// java -cp .:lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/GUI

import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class GUI extends JFrame implements ActionListener, MouseListener {
    public static int male;
    public static int female;
    public static int childU8;
    public static int childO8;
    private static String firstName;
    private static String lastName;
    private Integer hamperNumber;
    private Hamper hamper;

    private static HashMap<Integer, Client> clientsMap;
    public static HashMap<Integer, Food> foodMap = new HashMap<>();
    private static Set<Integer> food;

    private Integer[] options = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    private JLabel instructions = new JLabel(
            "Please input the number and age of clients as well as how many additional hampers, ensure that the submit button is selected first");
    private JLabel maleLabel = new JLabel("Number of Adult Males:");
    private JLabel femaleLabel = new JLabel("Number of Adult Females:");
    private JLabel childU8Label = new JLabel("Number of Children under 8:");
    private JLabel childO8Label = new JLabel("Number of Children over 8:");
    private JLabel hamperLabel = new JLabel("How many more hampers? (A new prompt will appear)");
    private JLabel firstNameLabel = new JLabel("First Name:");
    private JLabel lastNameLabel = new JLabel("Last Name:");

    private JComboBox<Integer> maleInput = new JComboBox<>(options);
    private JComboBox<Integer> femaleInput = new JComboBox<>(options);
    private JComboBox<Integer> childU8Input = new JComboBox<>(options);
    private JComboBox<Integer> childO8Input = new JComboBox<>(options);
    private JComboBox<Integer> hamperInput = new JComboBox<Integer>(options);
    private JTextField firstNameInput = new JTextField("Ex: Yousef");
    private JTextField lastNameInput = new JTextField("Ex: Hammad");
    private JButton terminate = new JButton("Terminate Program"); // need to have terminate button as a field to refer
    // to later in actionPerformed
    private JButton addToHamper = new JButton("Add Hamper to Order");// make submit button
    private JButton submitInfo = new JButton("Submit");// make submit button

    public GUI() {
        super("Create an order");
        setupGUI();
        setSize(750, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public GUI(Integer hamper) {
        super(String.format("Additional Hamper %d", hamper));
        setupAdditionalGUI();
        setSize(700, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setupAdditionalGUI() {
        maleInput.addMouseListener(this);
        femaleInput.addMouseListener(this);
        childU8Input.addMouseListener(this);
        childO8Input.addMouseListener(this);
        hamperInput.addMouseListener(this);
        terminate.addActionListener(this); // add listener to termiante button, already made as a field
        addToHamper.addActionListener(this); // add listener to the submit button

        JPanel promptPanel = new JPanel();
        promptPanel.setLayout(new FlowLayout());

        JPanel headerPanel = new JPanel(); // create header panel
        headerPanel.setLayout(new FlowLayout()); // give header panel a layout

        JPanel clientPanel = new JPanel(); // create client panel
        clientPanel.setLayout(new FlowLayout()); // give panel a layout

        JPanel submitPanel = new JPanel(); // create panel for submit button
        submitPanel.setLayout(new FlowLayout());

        headerPanel.add(instructions);

        clientPanel.add(maleLabel);
        clientPanel.add(maleInput);

        clientPanel.add(femaleLabel);
        clientPanel.add(femaleInput);

        clientPanel.add(childU8Label);
        clientPanel.add(childU8Input);

        clientPanel.add(childO8Label);
        clientPanel.add(childO8Input);

        submitPanel.add(addToHamper);
        submitPanel.add(terminate);

        this.add(headerPanel, BorderLayout.NORTH);
        this.add(clientPanel, BorderLayout.CENTER);
        this.add(submitPanel, BorderLayout.PAGE_END);

    }

    public void setupGUI() {
        maleInput.addMouseListener(this);
        femaleInput.addMouseListener(this);
        childU8Input.addMouseListener(this);
        childO8Input.addMouseListener(this);
        hamperInput.addMouseListener(this);
        terminate.addActionListener(this); // add listener to termiante button, already made as a field
        submitInfo.addActionListener(this); // add listener to the submit button
        firstNameInput.addMouseListener(this);
        lastNameInput.addMouseListener(this);

        JPanel promptPanel = new JPanel();
        promptPanel.setLayout(new FlowLayout());

        JPanel headerPanel = new JPanel(); // create header panel
        headerPanel.setLayout(new FlowLayout()); // give header panel a layout

        JPanel clientPanel = new JPanel(); // create client panel
        clientPanel.setLayout(new FlowLayout()); // give panel a layout

        JPanel submitPanel = new JPanel(); // create panel for submit button
        submitPanel.setLayout(new FlowLayout());

        headerPanel.add(instructions);
        clientPanel.add(firstNameLabel);
        clientPanel.add(firstNameInput);

        clientPanel.add(lastNameLabel);
        clientPanel.add(lastNameInput);

        clientPanel.add(maleLabel);
        clientPanel.add(maleInput);

        clientPanel.add(femaleLabel);
        clientPanel.add(femaleInput);

        clientPanel.add(childU8Label);
        clientPanel.add(childU8Input);

        clientPanel.add(childO8Label);
        clientPanel.add(childO8Input);

        clientPanel.add(hamperLabel);
        clientPanel.add(hamperInput);

        submitPanel.add(submitInfo);
        submitPanel.add(terminate);

        this.add(headerPanel, BorderLayout.NORTH);
        this.add(clientPanel, BorderLayout.CENTER);
        this.add(submitPanel, BorderLayout.PAGE_END);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // button is clicked
        if (e.getSource().equals(terminate)) { // find if the source of the event is terminate
            if (JOptionPane.showConfirmDialog(this,
                    "Would you like to quit", "Cancel Order",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                System.exit(0);
            } // if the option selected is yes then quit

        }

        male = (Integer) maleInput.getSelectedItem();
        female = (Integer) femaleInput.getSelectedItem();
        childU8 = (Integer) childU8Input.getSelectedItem();
        childO8 = (Integer) childO8Input.getSelectedItem();
        hamperNumber = (Integer) hamperInput.getSelectedItem();
        firstName = firstNameInput.getText();
        lastName = lastNameInput.getText();
        if (hamperNumber >= 1) {
            for (int i = 0; i < hamperNumber; i++) {
                new GUI(i + 1).setVisible(true);

            }
        }
        if (e.getSource().equals(addToHamper)) {
            // add hamper info to the order already made
            try {
                System.out.println("Added to original order");
                setVisible(false);
                Family family;
                family = createFamily();
                createHamper(family);
                Order order = new Order(firstName, lastName, family, foodMap, food);
                order.printAdditionalHampers();
                // print();
                updateInventory();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }
        if (e.getSource().equals(submitInfo)) {
            try {
                System.out.println("Main Order");
                Family family = createFamily();
                createHamper(family);
                Order order = new Order(firstName, lastName, family, foodMap, food);
                order.printOrder();
                // print();
                updateInventory();

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

    }

    private void updateInventory() throws FoodShortageException {
        Inventory inventory = new Inventory("jdbc:mysql://localhost/food_inventory", "student", "ensf");
        inventory.createConnection();

        for (Integer id : food) {
            foodMap.remove(id);
            if (foodMap.size() < 15) {
                throw new FoodShortageException();
            }
            inventory.delete(id);
        }

    }

    public void createHamper(Family family) throws FoodShortageException {
        hamper = new Hamper(family, foodMap);
        food = hamper.hamperAlgorithm();

    }

    private static void connectDB() { // initializes the client needs as well
        Inventory inventory = new Inventory("jdbc:mysql://localhost/food_inventory", "student", "ensf");
        inventory.createConnection();
        clientsMap = inventory.selectClients(); // this hash map contains all the client needs
        foodMap = inventory.selectFood(); // this hash map contains all the food, remove food after hamper checks
    }// use inventory maps instead

    public Family createFamily() throws CloneNotSupportedException {// get number of each type and create object
        ArrayList<Client> family = new ArrayList<>();
        for (int i = 0; i < male; i++) {
            family.add(clientsMap.get(1).clone());// get the male on the hashmap and make a clone of it
        }
        for (int i = 0; i < female; i++) {
            family.add(clientsMap.get(2).clone());// get the female on the hashmap and make a clone of it
        }
        for (int i = 0; i < childO8; i++) {
            family.add(clientsMap.get(3).clone());// get the childo8 on the hashmap and make a clone of it
        }
        for (int i = 0; i < childU8; i++) {
            family.add(clientsMap.get(4).clone());// get the childu8 on the hashmap and make a clone of it
        }
        Family fam = new Family(family);
        return fam;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(firstNameInput)) {
            firstNameInput.setText("");
        }
        if (e.getSource().equals(lastNameInput)) {
            lastNameInput.setText("");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void print() {
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Number of males: " + male);
        System.out.println("Number of females: " + female);
        System.out.println("Number of children under 8: " + childU8);
        System.out.println("Number of children over 8: " + childO8);
        System.out.println("Number of additional hanper configurations: " + hamperNumber);

        food
                .forEach(s -> System.out.println(foodMap.get(s).getName() + " " + s));
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> { // Allows gui to continue running and continue listening for events, will
                                       // continue running until JFrame is closed
            new GUI().setVisible(true); // Can do this becaause it extends JFrame
        });
        connectDB();
    }

}