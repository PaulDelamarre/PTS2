/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafxapplication12.SpreadDisease;

/**
 *
 * @author paul
 */
public class SpreadDiseaseController implements Initializable {
    
    @FXML
    private Label ITransmissionRate;
    @FXML
    private Label IDaysInIncubation;
    @FXML
    private Label IDaysWithSymptoms;
    private SpreadDisease SpreadDisease;
    IntegerProperty a = new SimpleIntegerProperty(0);
    IntegerProperty b = new SimpleIntegerProperty(0);
    DoubleProperty c = new SimpleDoubleProperty(0.0);
    
    @FXML
    private void handleButtonActionDemarre(ActionEvent event) {
        TimerTask gameLoop = new TimerTask() {
            @Override
            public void run(){
                

            }
        };
        Timer itsTimer = new Timer();
        itsTimer.schedule(gameLoop, 1000,3);
    }
    private void handleButtonActionPas(ActionEvent event) {
        
    }
    private void handleButtonActionArrete(ActionEvent event) {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setMainApp(SpreadDisease uneMainApp){
        this.SpreadDisease = uneMainApp;
    }
}
