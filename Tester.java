import java.io.FileNotFoundException;
/*
 * Tester class used to test ZoneCalculator.java. 
 * @authors:
 *          Kevin Joseph
 *          Reese Garcia
 *          Sunny Patel
 *          Arjun Tomar
 */

public class Tester {
    //Fare used when calculating the amount the person must pay for Test Zone.
    public static final String[] fares = {"1.60", "2.50", "3.15", "3.80"};

    /*
     * Tester method to test the ZoneCalculator.java file.
     * @return Void
     */
    public static void main(String[] args) throws FileNotFoundException{
        ZoneCalculator zC = new ZoneCalculator();
        int zones = zC.zoneNumber(args[0]);
        System.out.println();
        System.out.println("------------------------------");
        System.out.println("You must purchase " + zones + " zones.");
        System.out.println("------------------------------");
        System.out.println("You need to pay $" + fares[zones - 1] + ".");
    }
}
