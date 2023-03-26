package edu.ucalgary.ensf409;

/**
 * @author Issam Akhtar, Yousef Hammad, Bilal Pasha
 * @version 1.1
 * @since 1.0
 * 
 *        The FoodShortageException.java class is a custom exception class which
 *        is thrown when the food amounts are insufficient/inaccurate.
 */
public class FoodShortageException extends Exception {
    public FoodShortageException() {
        super("There is not enough food in the databse for this request"); 
    }
}
