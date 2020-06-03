/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tronv2;

import java.util.ArrayList;

/**
 *
 * @author verri
 */
public class Collision {
    
    public boolean collisionEdge(Player player)
    {
        boolean collisionEdge = false;
        if (player.getPosX() <= 0) // LeftEdge
        {
            collisionEdge = true;
        }
        else if (player.getPosX() >= 1000) // RightEdge
        {
            collisionEdge = true;
        }
        else if (player.getPosY() <= 0) // UpEdge
        {
            collisionEdge = true;
        }
        else if (player.getPosY() >= 500) // DownEdge
        {
            collisionEdge = true;
        }
        return collisionEdge;
    }
    
    public boolean headOnCollision(Player player1, Player player2)
    {
        boolean headOnCollision = false;
        if( (int)player1.getPosX() == (int)player2.getPosX() && (int)player1.getPosY() == (int)player2.getPosY())
        {
            headOnCollision = true;
        }
        return headOnCollision;
    }
    
    /*
    * On aurait fini...
    * Color picker / utilistaion
    * Mur is true / problème de vérification de tous les murs
    * Pane ... mais utilition biaisée
    */
    public boolean blueCollision(ArrayList<Wall> redtab, Wall wall1)
    {
        boolean collision = false;
        for(int TheI = 0;TheI < redtab.size() && collision == false;TheI++)
        {
            if(wall1.getPosX() == redtab.get(TheI).getPosX() && wall1.getPosY() == redtab.get(TheI).getPosY()){
                collision = true;
                System.out.println("blue");
            }
    
        } 
        return collision;
    }
    
    public boolean redCollision(ArrayList<Wall> bluetab, Wall wall2)
    {
        boolean collision = false;
        for(int TheI = 0;TheI < bluetab.size() && collision == false;TheI++)
        {
            if(wall2.getPosX() == bluetab.get(TheI).getPosX() && wall2.getPosY() == bluetab.get(TheI).getPosY()){
                collision = true;
                System.out.println("red");
            }
        } 
        return collision;
    }
    
    public boolean collisions(Player player1, Player player2, Wall redWall,Wall blueWall,ArrayList<Wall> bluetab,ArrayList<Wall> redtab)
    {
        boolean ifOneIsTrue = false;
        if(headOnCollision(player1, player2))
        {
            ifOneIsTrue = true;
            System.out.println("Egalité");
        }
        
        else if(collisionEdge(player1))
        {
            ifOneIsTrue = true;
            System.out.println("Joueur Rouge à gagné");
        }
        
        else if(collisionEdge(player2))
        {
            ifOneIsTrue = true;
            System.out.println("Joueur Bleu à gagné");
        }
        
        else if(blueCollision(redtab, blueWall))
        {
            ifOneIsTrue = true;
            System.out.println("bluecollision");
        }
      
        else if(redCollision(bluetab, redWall))
        {
            ifOneIsTrue = true;
            System.out.println("redcollision");
        }
        

        return ifOneIsTrue;

    }
}
