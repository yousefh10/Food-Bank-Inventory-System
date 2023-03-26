package edu.ucalgary.ensf409;

/**
 * @author Issam Akhtar, Yousef Hammad, Bilal Pasha
 * @version 1.2
 * @since 1.0
 * 
 *        The Food.java class is responsible for constructing the food types,
 *        while accessing this information through getters.
 */

public class Food {
    private final String NAME;
    private final int IDNUMBER;
    private final int GRAINCONTENT;
    private final int FVCONTENT;
    private final int PROTEINCONTENT;
    private final int OTHERCONTENT;
    private final int CALORIES;

    public Food(String name, int idNumber, int grainContent, int fvContent, int proteinContent, int otherContent,
            int calories) {
        this.NAME = name;
        this.IDNUMBER = idNumber;
        this.GRAINCONTENT = grainContent;
        this.FVCONTENT = fvContent;
        this.PROTEINCONTENT = proteinContent;
        this.OTHERCONTENT = otherContent;
        this.CALORIES = calories;
    }

    public String getName() {
        return this.NAME;
    }

    public int getIdNumber() {
        return this.IDNUMBER;
    }

    public int getGrainContent() {
        return this.GRAINCONTENT;
    }

    public int getFvContent() {
        return this.FVCONTENT;
    }

    public int getProteinContent() {
        return this.PROTEINCONTENT;
    }

    public int getOtherContent() {
        return this.OTHERCONTENT;
    }

    public int getCalories() {
        return this.CALORIES;
    }

    public double getGrainContentCal() {
        return (this.GRAINCONTENT * this.CALORIES) / 100;
    }

    public double getFvContentCal() {
        return (this.FVCONTENT * this.CALORIES) / 100;
    }

    public double getProteinContentCal() {
        return (this.PROTEINCONTENT * this.CALORIES) / 100;
    }

    public double getOtherContentCal() {
        return (this.OTHERCONTENT * this.CALORIES) / 100;
    }

}