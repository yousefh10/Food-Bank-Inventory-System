package edu.ucalgary.ensf409;

/** 
 * @author Issam Akhtar, Yousef Hammad, Bilal Pasha
 * @version 1.3
 * @since 1.0
 * 
 * The Client.java class is responsible for calculating the percent values of the clients needs and saving the IDs of the clients.
 * The clone method is also included in this class, due to the client information not changing.
*/

public class Client implements Cloneable {
    private final Integer CLIENTID;
    private final Double WHOLEGRAINSPERCENT; // percents are all decimal values
    private final Double FRUITVEGGIEPERCENT;
    private final Double PROTEINPERCENT;
    private final Double OTHERPERCENT;
    private final Integer CALORIES;

    public Client(Integer clientID, Integer wholeGrainsPercent, Integer fruitVeggiePercent, Integer proteinPercent,
            Integer otherPercent, Integer calories)throws IllegalArgumentException{
        if (calories == 0){
            throw new IllegalArgumentException();
        }
        this.CLIENTID = clientID;
        this.WHOLEGRAINSPERCENT = (double) wholeGrainsPercent / 100;
        this.FRUITVEGGIEPERCENT = (double) fruitVeggiePercent / 100;
        this.PROTEINPERCENT = (double) proteinPercent / 100;
        this.OTHERPERCENT = (double) otherPercent / 100;
        this.CALORIES = calories;
    }

    public Integer getClientID() {
        return this.CLIENTID;
    }

    public Double getWholeGrainsPercent() {
        return this.WHOLEGRAINSPERCENT;
    }

    public Double getFruitVeggiePercent() {
        return this.FRUITVEGGIEPERCENT;
    }

    public Double getProteinPercent() {
        return this.PROTEINPERCENT;
    }

    public Double getOtherPercent() {
        return this.OTHERPERCENT;
    }

    public Integer getCalories() {
        return this.CALORIES;
    }

    public Client clone() throws CloneNotSupportedException {
        return (Client) super.clone();
    }
}
