/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tronv2;

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
    public boolean collision(Player player, Wall wall)
    {
        boolean collision = false;
        if(player.getMoveUp())
        {
            //if(primaryStage.getRGB((int)player.getPosX(),(int)player.getPosY()-1))
            if(player.getPosX() == wall.getPosX() && player.getPosY() == wall.getPosY() && wall.isIsWall())
            {
                //collision = true;
            System.out.println("Go pas majorer");
            }
        }
        return collision;
    }
    
    public boolean collisions(Player player1, Player player2, Wall wall)
    {
        boolean ifOneIsTrue = false;
        if(headOnCollision(player1, player2))
        {
            ifOneIsTrue = true;
            System.out.println("Egalité");
        }
        
        if(collisionEdge(player1))
        {
            ifOneIsTrue = true;
            System.out.println("Joueur Rouge à gagné");
        }
        
        if(collisionEdge(player2))
        {
            ifOneIsTrue = true;
            System.out.println("Joueur Bleu à gagné");
        }
        
        if(collision(player2, wall))
        {
            ifOneIsTrue = true;
            System.out.println("Marche dans end Game J2");
        }
        
        if(collision(player1, wall))
        {
            ifOneIsTrue = true;
            System.out.println("Marche dans end Game J1");
        }
        return ifOneIsTrue;
    }
}
