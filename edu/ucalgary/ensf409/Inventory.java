package edu.ucalgary.ensf409;

/** 
 * @author Issam Akhtar, Yousef Hammad, Bilal Pasha
 * @version 1.4
 * @since 1.0
 * 
 * The Inventory.java class is responsible for collecting the information from the database. This is an essential class
 * as it is needed in order for the hamper to function properly.
*/
import java.sql.*;
import java.util.*;

public class Inventory {
    public final String DBURL;
    public final String USER;
    public final String PASS;
    private HashMap<Integer, Client> clientMap = new HashMap<>(5);
    private HashMap<Integer, Food> foodMap = new HashMap<>();

    private Connection dbConnect;
    private ResultSet results;

    public Inventory(String DBURL, String USER, String PASS)throws IllegalArgumentException {
        if (PASS.length() >= 10){
            throw new IllegalArgumentException();
        }
        this.DBURL = DBURL;
        this.USER = USER;
        this.PASS = PASS;
    }

    public void createConnection() {
        try {
            dbConnect = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public HashMap<Integer, Client> selectClients() {
        try {
            Statement state = dbConnect.createStatement();
            results = state.executeQuery("SELECT * FROM DAILY_CLIENT_NEEDS");

            while (results.next()) {
                Integer clientID = results.getInt("ClientID");
                Integer grains = results.getInt("WholeGrains");
                Integer fruits = results.getInt("FruitVeggies");
                Integer protein = results.getInt("Protein");
                Integer other = results.getInt("Other");
                Integer calories = results.getInt("Calories");
                clientMap.put(clientID, new Client(clientID, grains, fruits, protein, other, calories));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientMap;
    }

    public HashMap<Integer, Food> selectFood() {
        try {
            Statement state = dbConnect.createStatement();
            results = state.executeQuery("SELECT * FROM AVAILABLE_FOOD");

            while (results.next()) {
                Integer itemID = results.getInt("ItemID");
                String name = results.getString("Name");
                int grain = results.getInt("GrainContent");
                int fruits = results.getInt("FVContent");
                int protein = results.getInt("ProContent");
                int other = results.getInt("Other");
                int calories = results.getInt("Calories");

                foodMap.put(itemID, new Food(name, itemID, grain, fruits, protein, other, calories));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foodMap;
    }

    public void delete(int itemID) {
        try {
            String query = "DELETE FROM AVAILABLE_FOOD WHERE ItemID = ?";
            PreparedStatement pStatement = dbConnect.prepareStatement(query);
            pStatement.setInt(1, itemID);
            pStatement.executeUpdate(); // excecute to the database
            pStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertNewItem(int itemID, String name, int grainContent, int fruits, int protein, int other,
            int calories) {
        try {
            boolean exists = false;
            String query = "INSERT INTO AVAILABLE_FOOD (ItemID, Name, GrainContent, FVContent, ProContent, Other, Calories) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pStatement = dbConnect.prepareStatement(query);
            results = pStatement.executeQuery("SELECT * FROM AVAILABLE_FOOD");
            while (results.next()) {
                if (results.getInt("ItemID") == itemID) {
                    itemID++;
                    exists = true;
                }
            }
            if (exists == true) {
                System.out.println(
                        "This value is already in the database, the new item is assigned the ID: " + itemID);
            }
            pStatement.setInt(1, itemID);
            pStatement.setString(2, name);
            pStatement.setInt(3, grainContent);
            pStatement.setInt(4, fruits);
            pStatement.setInt(5, protein);
            pStatement.setInt(6, other);
            pStatement.setInt(7, calories);
            pStatement.executeUpdate();
            pStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            dbConnect.close();
            results.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
