/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tronv2;

import javafx.scene.shape.Line;

/**
 *
 * @author verri
 */
public class Wall 
{
    private int posX;
    private int posY;
    private boolean isWall;
    private Line wallLine;

    public Wall(Line wallLine) 
    {
        this.wallLine = wallLine;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public boolean isIsWall() {
        return isWall;
    }

    public void setIsWall(boolean isWall) {
        this.isWall = isWall;
    }


    public Line getWallLine() {
        return wallLine;
    }
    
    
   
    public void draw()
    {
        this.wallLine.setStartX(posX);
        this.wallLine.setStartY(posY);
        this.wallLine.setEndX(posX-1000);
        this.wallLine.setEndY(posY);
    }
}
