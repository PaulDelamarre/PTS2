/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author rchampag
 */
public class Disease {
    private SimpleDoubleProperty itsTransmissionRate;
    private SimpleIntegerProperty itsDaysInIncubation;
    private SimpleIntegerProperty itsDaysWithSymptoms;
    private Person [][] itsPopulation;
    public Disease(){
        itsTransmissionRate = new SimpleDoubleProperty(0.);
        itsDaysInIncubation = new SimpleIntegerProperty(0);
        itsDaysWithSymptoms = new SimpleIntegerProperty(1);       
        itsPopulation = new Person[54][54];
        for (int row = 0; row < itsPopulation.length; row++){
            for (int column = 0; column < itsPopulation[row].length; column++){
                itsPopulation[row][column] = new Person();
            }
        }
        
        itsPopulation[26][26].updateState(PersonState.INCUBATION, 0);
        itsPopulation[25][26].updateState(PersonState.INCUBATION, 0);
        itsPopulation[27][26].updateState(PersonState.INCUBATION, 0);
        itsPopulation[26][25].updateState(PersonState.INCUBATION, 0);
        itsPopulation[26][27].updateState(PersonState.INCUBATION, 0);
    }
    public void update(int aDate){
        Person [][] population = new Person[54][54];       
        for (int row = 0; row < population.length; row++){
            for (int column = 0; column < population[row].length; column++){
                population[row][column] = new Person();
                population[row][column].updateState(itsPopulation[row][column].getState(), itsPopulation[row][column].getUpdate());
            }
        }
        
        for (int row = 1; row < itsPopulation.length-1; row++){
            for (int column = 1; column < itsPopulation[row].length-1; column++){
                if (itsPopulation[row][column].getState() == PersonState.SUSCEPTIBLE){
                    if (isNeighbourInIncubation(row, column, aDate) && isInfected()){
                        population[row][column].updateState(PersonState.INCUBATION, aDate);
                    }
                } else if (itsPopulation[row][column].getState() == PersonState.INCUBATION){
                    int duration = aDate - itsPopulation[row][column].getUpdate();
                    if (duration == itsDaysInIncubation.getValue()){
                        population[row][column].updateState(PersonState.INFECTED, aDate);
                    }
                } else if (itsPopulation[row][column].getState() == PersonState.INFECTED){
                    int duration = aDate - itsPopulation[row][column].getUpdate();
                    if (duration == itsDaysWithSymptoms.getValue()){
                        population[row][column].updateState(PersonState.RECOVER, aDate);
                    }
                }
            }
        }
        for (int row = 0; row < population.length; row++){
            for (int column = 0; column < population[row].length; column++){
                itsPopulation[row][column].updateState(population[row][column].getState(), population[row][column].getUpdate());
            }
        }
    }
    private boolean isNeighbourInIncubation(int aRow, int aColumn, int aDate){
        if (itsPopulation[aRow-1][aColumn].isInIncubation()){
            return true;
        }
        if (itsPopulation[aRow+1][aColumn].isInIncubation()){
            return true;
        }
        if (itsPopulation[aRow][aColumn-1].isInIncubation()){
            return true;
        }
        if (itsPopulation[aRow][aColumn+1].isInIncubation()){
            return true;
        }
        return false;
    }
    private boolean isInfected(){
        return Math.random() <= itsTransmissionRate.getValue();
    }
    public SimpleDoubleProperty getTransmissionRate(){
        return itsTransmissionRate;
    }
    public SimpleIntegerProperty getDaysInIncubation(){
        return itsDaysInIncubation;
    }
    public SimpleIntegerProperty getDaysWithSymptoms(){
        return itsDaysWithSymptoms;
    }
    public void draw(){
    	// TODO add your code here
    }
    public void setAvatar(int aRow, int aColumn, Rectangle anAvatar){
    	// TODO add your code here
    }
}
