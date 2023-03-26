package edu.ucalgary.ensf409;

/** 
 * @author Issam Akhtar, Yousef Hammad, Bilal Pasha
 * @version 1.1
 * @since 1.0
 * 
 * The Family.java class is responsible for ensuring the Client class functions correctly. 
 * It also has a toString() method, responsible for printing the number of users in each hamper.
 * (ie. 1 Adult Male, 1 Child Over 8)
*/
import java.util.*;

public class Family {
    private ArrayList<Client> family;

    public Family(ArrayList<Client> family) {
        this.family = family;
    }

    public ArrayList<Client> getFamily() {
        return this.family;
    }

    @Override
    public String toString() {
        StringBuilder famBuilder = new StringBuilder();
        if (GUI.male > 0) {
            famBuilder.append(String.format("%d Males ", GUI.male));
        }
        if (GUI.female > 0) {
            famBuilder.append(String.format("%d Females ", GUI.female));
        }
        if (GUI.childO8 > 0) {
            famBuilder.append(String.format("%d Children Over 8 ", GUI.childO8));
        }
        if (GUI.childU8 > 0) {
            famBuilder.append(String.format("%d Children Under 8 ", GUI.childU8));
        }
        return famBuilder.toString();

    }

}
