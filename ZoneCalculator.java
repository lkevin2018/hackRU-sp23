import java.io.*;
import java.util.*;
import java.lang.Math.*;

/*The user is prompted to select the route that they wish to take, and the user then selects where they are getting on and off of the bus, and finally, it asks 
the user if they are going inbound or outbound. With this information, the system gives the user the number of zones that they are passing through.
*/

public class ZoneCalculator{

    private int zone; //The number of zones that the user needs to travel through after the data is taken in
    private String origin;//where the user is planning on getting on the bus
    private String destination; //where the user is planning on leaving the bus
    private String direction; //if the user is heading inbound or outbound
    private int route; //route number the user is taking
    private int originID; // orgin stop number
    private int destID; // destination stop number
    private Map<Integer, Boolean> routeMap = new HashMap<>(); //Hashmap used to track stops imported from data sheet + whether each stop is a zone boundary or not
    /*
     * This method has a void return and is designed to take in the user input to fill private instance variables used to run the ZoneCalculator.
     */
    public void userFill(){
        Scanner user = new Scanner(System.in);
        
        System.out.println("Enter origin: ");
        origin = user.nextLine();
        
        System.out.println("Enter destination: ");
        destination = user.nextLine();
        
        System.out.println("Enter direction:");
        direction = user.nextLine();

        System.out.println("Enter route number:");
        route = user.nextInt();
        
        user.close();

    }
    private void fillMap(String f) throws FileNotFoundException{
        String fileName = f;
        String line;
        try(BufferedReader bR = new BufferedReader(new FileReader(fileName))){
            while((line = bR.readLine()) != null){
                String[] lineDetails = line.split(",");
                if(lineDetails[0].equals(origin)){
                    originID = Integer.parseInt(lineDetails[1]);
                }
                if(lineDetails[0].equals(destination)){
                    destID = Integer.parseInt(lineDetails[1]);
                }
                routeMap.put(Integer.parseInt(lineDetails[1]), Boolean.parseBoolean(lineDetails[2]));
            }
        } catch (Exception e){
            System.out.println("Exception caught: " + e);
        }
    }
    
    /*
     * This method is designed to return the number of zones that the user would have to go through (put at the very end of the class)
     */
    public int zoneNumber(String filename) throws FileNotFoundException{
        fillMap(filename);
        zone = 0;
        int start = -1;
        int stop = -1;
        for(Integer key: routeMap.keySet()){
            if(key.equals(originID)){
                start = key;
            }
            if(key.equals(destID)){
                stop = key;
            }
        }
        for(int idx = start; idx <= stop; idx++){
            if(routeMap.get(idx)){
                zone++;
            }
        }
        return zone;
    }
}






















































































