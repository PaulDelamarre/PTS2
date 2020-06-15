/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication12;

import controller.SpreadDiseaseController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author paul
 */
public class SpreadDisease extends Application {
    private Spinner ITransmissionRate;
    private Spinner sDaysInIncubation;
    private Spinner sDaysWithSymptoms; 
    private Stage sPrimaryStage;
    
    public Spinner getITransmissionRate() {
        return ITransmissionRate;
    }

    public Spinner getsDaysInIncubation() {
        return sDaysInIncubation;
    }

    public Spinner getsDaysWithSymptoms() {
        return sDaysWithSymptoms;
    }
    
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader leLoader = new FXMLLoader(getClass().getResource("/src/view/SpreadDisease.fxml"));
        AnchorPane root = (AnchorPane) leLoader.load();
        this.ITransmissionRate = new Spinner<Integer>(0, 20, 0, 1);
        this.ITransmissionRate.setLayoutX(70);
        this.ITransmissionRate.setLayoutY(192);
        this.ITransmissionRate.setPrefSize(60, 27);
        this.ITransmissionRate.maxWidth(60);
        this.ITransmissionRate.setEditable(true);
        
        this.sDaysInIncubation = new Spinner<Integer>(0, 20, 0, 1);
        this.sDaysInIncubation.setLayoutX(145);
        this.sDaysInIncubation.setLayoutY(192);
        this.sDaysInIncubation.setPrefSize(60, 27);
        this.sDaysInIncubation.maxWidth(60);
        this.sDaysInIncubation.setEditable(true);
        
        this.sDaysWithSymptoms = new Spinner<Integer>(0, 20, 0, 1);
        this.sDaysWithSymptoms.setLayoutX(220);
        this.sDaysWithSymptoms.setLayoutY(192);
        this.sDaysWithSymptoms.setPrefSize(60, 27);
        this.sDaysWithSymptoms.maxWidth(60);
        this.sDaysWithSymptoms.setEditable(true);
        AnchorPane theAnch = new AnchorPane();
        theAnch.setPrefSize(208, 208);
        for(int theI=0;theI<52;theI++)
        {
            for(int theJ=0;theJ<52;theJ++)
            {
              Rectangle theRect = new Rectangle();
              theRect.setX(theI);
              theRect.setY(theJ);
              theRect.setWidth(1);
              theRect.setHeight(1);
              theRect.setStroke(Color.WHITE);
              theAnch.getChildren().add(theRect);
            }
        }
        root.getChildren().add(this.ITransmissionRate);
        root.getChildren().add(this.sDaysInIncubation);
        root.getChildren().add(this.sDaysWithSymptoms);
        
        this.sPrimaryStage = stage;
        
        SpreadDiseaseController leControleur = leLoader.getController();
        if (leControleur == null){
            System.out.println("Pas de contrôleur");
        }
        
        
        leControleur.setMainApp(this);
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
