package edu.ucalgary.ensf409;

/** 
 * @author Issam Akhtar, Yousef Hammad, Bilal Pasha
 * @version 1.6
 * @since 1.0
 * 
 * The Order.java class is responsible for the creation of the order form. The order form lets the user know the name, date, the list of items fullfiling the request
 * made by the client. The form will also list the amount of members per hamper (ie. 1 Adult Male, 1 Child Over 8). This class also implements an interface called 
 * FormOrder.java.
*/
import java.io.*;
import java.time.*;
import java.util.*;

public class Order implements FormOrder {
    private String firstName, lastName;
    private Family family;
    private Set<Integer> food = new HashSet<Integer>();
    private static HashMap<Integer, Food> foodMap;
    private Integer count = 1;

    public Order(String firstName, String lastName, Family family, HashMap<Integer, Food> foodMap, Set<Integer> food) {

        this.family = family;
        this.firstName = firstName;
        this.lastName = lastName;
        this.foodMap = foodMap;
        this.food = food;
    }
    public Order(){}
    public void printOrder() {
        try {
            FileWriter filewriter = new FileWriter("Order.txt");

            filewriter.write("Hamper Order Form" + "\n");
            filewriter.write("\n");
            filewriter.write("Name: " + firstName + " " + lastName + "\n"); // print name
            filewriter.write("Date: " + LocalDate.now() + "\n"); // print date
            filewriter.write("\n");
            filewriter.write("Original Request: \n");
            filewriter.write(family.toString() + "\n \n");

            for (Integer integer : food) { // loop responsible for printing food id and list of foods
                filewriter.write(integer + " " + foodMap.get(integer).getName() + "\n");
            }
            filewriter.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    // using RandomAccessFile, access the txt file to add additional hamper(s) based on user request
    public void printAdditionalHampers() {
        try {
            RandomAccessFile filewriter = new RandomAccessFile("Order.txt", "rw");
            filewriter.seek(filewriter.length()); // will print new hamper at the end of txt file to ensure original request isn't replaced
            filewriter.writeBytes("\n");
            filewriter.writeBytes("Additional Hamper " + count + " items:\n");
            filewriter.writeBytes(family.toString() + "\n \n");

            for (Integer integer : food) { // loop responsible for printing food id and list of foods
                filewriter.writeBytes(integer + " " + foodMap.get(integer).getName() + "\n");
            }
            filewriter.close();
            count++;
        } catch (IOException e) {
            System.err.println("Could not open file");
            System.exit(1);
        }
    }
}