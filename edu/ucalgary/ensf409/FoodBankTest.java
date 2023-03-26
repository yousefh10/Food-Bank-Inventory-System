package edu.ucalgary.ensf409;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;
import java.util.*;

//make inventory an array list

public class FoodBankTest { // test client, food≤ inventory≤ order≤ form order, hamper, family
    private int clientNum = 1; // adult male
    private static HashMap<Integer, Food> foodMap = GUI.foodMap;
    private GUI gui = new GUI();

    @Test
    public void testClientCreation() {
        Client person = new Client(clientNum, 80, 5, 12, 3, 2000);
        Double expGrain = 0.80;
        Double expFruitVeg = 0.05;
        Double expProtein = 0.12;
        Double expOther = 0.03;
        Integer expCalories = 2000;
        assertEquals("Incorrect grain value for Adult Male", expGrain, person.getWholeGrainsPercent());
        assertEquals("Incorrect fruit, veggie value for Adult Male", expFruitVeg, person.getFruitVeggiePercent());
        assertEquals("Incorrect protein value for Adult Male", expProtein, person.getProteinPercent());
        assertEquals("Incorrect other value for Adult Male", expOther, person.getOtherPercent());
        assertEquals("Incorrect calorie value for Adult Male", expCalories, person.getCalories());

    }

    @Test
    public void testClientException() {
        boolean thrown = false;
        try {
            Client person = new Client(5, 4, 3, 2, 1, 0);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testFoodCreation() {
        Food test = new Food("Watermelon", 231, 10, 15, 55, 20, 100);
        int expGrain = 10;
        int expFruitVeg = 15;
        int expProtein = 55;
        int expOther = 20;
        int expCalories = 100;
        String expName = "Watermelon";
        assertEquals("Incorrect grain value for food item with itemID = 9", expGrain, test.getGrainContent());
        assertEquals("Incorrect fruit, veggie value for food item with itemID = 9", expFruitVeg, test.getFvContent());
        assertEquals("Incorrect protein value for food item with itemID = 9", expProtein, test.getProteinContent());
        assertEquals("Incorrect other value for food item with itemID = 9", expOther, test.getOtherContent());
        assertEquals("Incorrect calorie value for food item with itemID = 9", expCalories, test.getCalories());
        assertEquals("Incorrect name for food item with itemID = 9", expName, test.getName());

    }

    @Test
    public void testFamilyClientArrayList() { // test array list of client to make up a family
        ArrayList<Client> client = new ArrayList<Client>();
        Client person = new Client(clientNum, 80, 5, 12, 3, 2000);
        client.add(person);
        Family expected = new Family(client);
        assertEquals("Incorrect Client Array List", client, expected.getFamily());
    }

    @Test
    public void testInventoryException() { // test array list of food in inventory
        boolean thrown = false;
        try {
            Inventory test = new Inventory("jdbc:mysql://localhost/food_inventory", "student", "abcdefghighkljlkj");
            test.createConnection();
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testDatabaseConnection() {
        boolean thrown = true;
        try {
            Inventory test = new Inventory("jdbc:mysql://localhost/food_inventory", "student", "ensf");
            test.createConnection();
        } catch (IllegalArgumentException e) {
            thrown = false;
        }
        assertTrue(thrown);
    }

    @Test
    public void testFoodShortageException() {
        boolean thrown = false;
        foodMap.clear();
        ArrayList<Client> client = new ArrayList<Client>();
        Client person = new Client(clientNum, 80, 5, 12, 3, 2000);
        client.add(person);
        Family expected = new Family(client);
        try {
            gui.createHamper(expected);
        } catch (FoodShortageException e) {
            thrown = true;
        }
        assertTrue("Food Shortage Exception was not thrown", thrown);
    }

}
