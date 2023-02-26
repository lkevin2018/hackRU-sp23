package com.example.zonecalculatorhackru;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class Controller {
    @FXML ChoiceBox<String> origin;
    @FXML ChoiceBox<String> destination;
    @FXML ChoiceBox<String> direction;
    @FXML ChoiceBox<Integer> route;
    @FXML Button calculate;

    private int zone; //The number of zones that the user needs to travel through after the data is taken in
    private String originCalc;//where the user is planning on getting on the bus
    private String destinationCalc; //where the user is planning on leaving the bus
    private String directionCalc; //if the user is heading inbound or outbound
    private int routeCalc; //route number the user is taking
    private int originID; // origin stop number
    private int destID; // destination stop number
    private Map<Integer, Boolean> routeMap = new HashMap<>(); //Hashmap used to track stops imported from data sheet + whether each stop is a zone boundary or not

    public ObservableList<String> stops = FXCollections.observableArrayList();
    public List<String> stopsList = new ArrayList<String>();

    public ObservableList<Integer> routesList= FXCollections.observableArrayList();
    public ObservableList<String> directionsList = FXCollections.observableArrayList();
    public static final String[] fares = {"1.60", "2.50", "3.15", "3.80"};

    public void start(Stage stage){
        //TODO: Populate ChoiceBoxes with stop options DONE
        String line;
        try(BufferedReader bR = new BufferedReader(new FileReader("src/main/resources/com/example/zonecalculatorhackru/Stops.csv"))){ //reads CSV and stores stop names
            while((line = bR.readLine()) != null){
                String[] lineDetails = line.split(",");
                stopsList.add(lineDetails[0]);
            }
        } catch (Exception e){
            System.out.println("Exception caught: " + e);
        }

        stops = FXCollections.observableArrayList(stopsList); //sets observablelist to stoplist
        routesList.add(815);
        directionsList.add("INBOUND");

        origin.setItems(stops);
        destination.setItems(stops);
        route.setItems(routesList);
        direction.setItems(directionsList);

    }

    /**
     * method to calculate fare cost
     */
    @FXML public void calculate() throws IOException {
    //TODO: Call zoneNumber method and change scene to show fare total
        originCalc = origin.getSelectionModel().getSelectedItem();
        destinationCalc = destination.getSelectionModel().getSelectedItem();
        directionCalc = direction.getSelectionModel().getSelectedItem();
        routeCalc = route.getSelectionModel().getSelectedItem();
        int numZones = zoneNumber("src/main/resources/com/example/zonecalculatorhackru/Stops.csv");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Calculated Fare");
        alert.setHeaderText("You will need to pay for "+ numZones + " zones");
        alert.setContentText("The cost of your fare is: $" + fares[numZones - 1] + ".");
        System.out.println("hello");

        alert.showAndWait();


    }

    /**
     * @param f File that contains stops on route in order.
     *          Method populates map used by zoneNumber() to calculate the zone
     * @throws FileNotFoundException
     */
    private void fillMap(String f) throws FileNotFoundException{
        String fileName = f;
        String line;
        try(BufferedReader bR = new BufferedReader(new FileReader(fileName))){
            while((line = bR.readLine()) != null){
                String[] lineDetails = line.split(",");
                if(lineDetails[0].equals(originCalc)){
                    originID = Integer.parseInt(lineDetails[1]);
                }
                if(lineDetails[0].equals(destinationCalc)){
                    destID = Integer.parseInt(lineDetails[1]);
                }
                routeMap.put(Integer.parseInt(lineDetails[1]), Boolean.parseBoolean(lineDetails[2]));
            }
        } catch (Exception e){
            System.out.println("Exception caught: " + e);
        }
    }

    /*
     * This method is designed to return the number of zones that the user would have to go through.
     * @return int (zones)
     */
    public int zoneNumber(String filename) throws FileNotFoundException{
        fillMap(filename);
        zone = 1;
        for(int idx = originID; idx <= destID; idx++){
            if(routeMap.get(idx)){
                zone++;
            }
        }
        return zone;
    }
}
