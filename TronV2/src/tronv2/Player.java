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
public class Player{
    private final int idPlayer;
    private float posX;
    private float posY;
    private float speedX;
    private float speedY;
    private boolean moveRight;
    private boolean moveLeft;
    private boolean moveUp;
    private boolean moveDown;
    private Line playerLine;

    public Player(int idPlayer, float posX, float posY, boolean moveRight, boolean moveLeft, Line line) {
        this.idPlayer = idPlayer;
        this.speedX = 0;
        this.speedY = 0;
        this.posX = posX;
        this.posY = posY;
        this.moveRight = moveRight;
        this.moveLeft = moveLeft;
        this.playerLine = line;
    }

    public boolean getMoveRight() {
        return moveRight;
    }

    public boolean getMoveLeft() {
        return moveLeft;
    }

    public boolean getMoveUp() {
        return moveUp;
    }

    public boolean getMoveDown() {
        return moveDown;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public void setMoveUp(boolean moveUp) {
        this.moveUp = moveUp;
    }

    public void setMoveDown(boolean moveDown) {
        this.moveDown = moveDown;
    }

    public int getIdPlayer() {
        return idPlayer;
    }
    
    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }
    
    public void move()
    {
        posX += speedX;
        posY += speedY;
    }  
    
    public void draw()
    {
        move();
        
        this.playerLine.setStartX(posX);
        this.playerLine.setStartY(posY);
        this.playerLine.setEndX(posX);
        this.playerLine.setEndY(posY);
        
    }
    
    public void moveRight(Player player)
    {
        if(!player.getMoveLeft())
        {
            if(player.getMoveRight())
            {
                speedX = (float) 0.5;
                speedY = (float) 0;
                player.setMoveDown(false);
                player.setMoveUp(false);
            }
            else
            {
                speedX = (float) 0;
            }
        }
        
    }
    
    public void moveLeft(Player player)
    {
        if(!player.getMoveRight())
        {
            if(player.getMoveLeft())
            {
                speedX = (float) -0.5;
                speedY = (float) 0;
                player.setMoveDown(false);
                player.setMoveUp(false);
            }
            else
            {
                speedX = (float) 0;
            }
        }
    }
    
    public void moveUp(Player player)
    {
        if(!player.getMoveDown())
        {
            if(player.getMoveUp())
            {
                speedY = (float) -0.5;
                speedX = (float) 0;
                player.setMoveRight(false);
                player.setMoveLeft(false);
            }
            else
            {
                speedX = (float) 0;
            }
        }
    }
    
    public void moveDown(Player player)
    {
        if(!player.getMoveUp())
        {
            if(player.getMoveDown())
            {
                speedY = (float) 0.5;
                speedX = (float) 0;
                player.setMoveRight(false);
                player.setMoveLeft(false);
            }
            else
            {
                speedX = (float) 0;
            }
        }
    }
}
