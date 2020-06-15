/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author rchampag
 */
public class Person {
    private PersonState itsState;
    private int itsUpdate;
    private Rectangle itsAvatar;
    public Person(){
        itsState = PersonState.SUSCEPTIBLE;
        itsUpdate = 0;
    }
    public void updateState(PersonState aNewState, int aDate){
        itsState = aNewState;
        itsUpdate = aDate;
    }
    public int getUpdate(){
        return itsUpdate;
    }
    public PersonState getState(){
        return itsState;
    }
    public void draw(){
        // TODO add your code here
    }
    public void setAvatar(Rectangle anAvatar){
        // TODO add your code here
    }
    
    public boolean isInIncubation(){
        return itsState == PersonState.INCUBATION;
    }
}
