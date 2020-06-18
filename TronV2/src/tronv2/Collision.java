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
    
    /*
    * Méthode collisionEdge
    * Paramètre : Un joueur de type Player
    * Méthode qui gère les collision d'un joueur passé en paramètre avec les différents bords de la carte.
    * Renvoie : Un boolean vrai si un des bords est dépassé par le joueur rentré en paramètre 
    */
    public boolean collisionEdge(Player player)
    {
        boolean collisionEdge = false;
        
        if (player.getPosX() <= 0) // LeftEdge
        {
            collisionEdge = true;
        }
        else if (player.getPosX() >= 750) // RightEdge
        {
            collisionEdge = true;
        }
        else if (player.getPosY() <= 0) // UpEdge
        {
            collisionEdge = true;
        }
        else if (player.getPosY() >= 750) // DownEdge
        {
            collisionEdge = true;
        }
        return collisionEdge;
    }
    
    /*
    * Méthode headOnCollision   /!\ A gérer /!\
    * Paramètres : Les deux murs qui sont à l'avant des joueurs Wall
    * Méthode qui gère les collision entre les deux joueurs lorsqu'une égalité est ammenée à se produire.
    * Renvoie : Un boolean vrai si les deux joueurs se retrouvent face à face
    */
    public boolean headOnCollision(Wall wall1, Wall wall2)
    {
        boolean headOnCollision = false;
        
        if(wall2.getWallLine().intersects(wall1.getWallLine().getBoundsInLocal()) )
        {
            headOnCollision = true;
        }
                
        return headOnCollision;
    }
    
    /*
    * Méthode blueCollision
    * Paramètres : Un tableau de murs formés par le joueur rouge (Array de Wall), ainsi qu'un mur qui est l'avant du joueur bleu 
    * Méthode qui gère les collision du joueur bleu.
    * Renvoie : Un boolean vrai si le joueur bleu se retrouve face à un mur que ce soit le sien ou celui de l'adversaire
    */
    public boolean player1Collision(ArrayList<Wall> redtab,ArrayList<Wall> bluetab, Wall wall1)
    {
        boolean collision = false;
        for(int TheI = 0;TheI < redtab.size() -1 && collision == false;TheI++)
        {
            if(wall1.getWallLine().intersects(redtab.get(TheI).getWallLine().getBoundsInLocal()) )
            {
                collision = true;
            }
        } 
        for(int TheI = bluetab.size()-1;TheI > 0 && collision == false;TheI--)
        {
            if(TheI > 60){
                if(wall1.getWallLine().intersects(bluetab.get(TheI-55).getWallLine().getBoundsInLocal()) )
                {
                    collision = true;
                }
            }
        } 
        return collision;
    }
    
    /*
    * Méthode redCollision
    * Paramètres : Un tableau de murs formés par le joueur bleu (Array de Wall), ainsi qu'un mur qui est l'avant du joueur rouge 
    * Méthode qui gère les collision du joueur rouge.
    * Renvoie : Un boolean vrai si le joueur rouge se retrouve face à un mur que ce soit le sien ou celui de l'adversaire
    */
    public boolean player2Collision(ArrayList<Wall> redtab,ArrayList<Wall> bluetab, Wall wall2)
    {
        boolean collision = false;
        for(int TheI = 0;TheI < bluetab.size()-1 && collision == false;TheI++)
        {
            if(wall2.getWallLine().intersects(bluetab.get(TheI).getWallLine().getBoundsInLocal()))
            {
                collision = true;
            }
        } 
        for(int TheI = bluetab.size()-1;TheI > 0 && collision == false;TheI--)
        {
            if(TheI > 60)
            {
                if(wall2.getWallLine().intersects(redtab.get(TheI-55).getWallLine().getBoundsInLocal()) )
                {
                    collision = true;
                }
            }
        } 
        
        return collision;
    }
    
    /*
    * Méthode collisions
    * Paramètres : Les paramètres sont ceux vus précédemment pour toutes les méthodes de la classe Collision
    * Méthode qui gère la fin de la partie.
    * Cette méthode regroupe toutes les autres afin de savoir quand la partie doit être arrêtée du à la défaite d'un des deux joueurs.
    * Renvoie : Un boolean vrai si l'un des deux joueurs à activé une des conditions de défaite vues précédément
    * Si ce boolean est vrai on arrête la partie.
    */
    public boolean collisions(Player player1, Player player2, Wall player2Wall,Wall player1Wall,ArrayList<Wall> player1tab,ArrayList<Wall> player2tab)
    {
        boolean ifOneIsTrue = false;
        if(headOnCollision(player1Wall, player2Wall))
        {
            ifOneIsTrue = true;
            System.out.println("Egalité");
        }
        
        else if(collisionEdge(player1))
        {
            ifOneIsTrue = true;
            System.out.println("Le joueur 2 à gagné");
        }
        
        else if(collisionEdge(player2))
        {
            ifOneIsTrue = true;
            System.out.println("Le joueur 1 à gagné");
        }
        
        else if(player1Collision(player2tab, player1tab, player1Wall))
        {
            ifOneIsTrue = true;
            System.out.println("Le joueur 1 à gagné");
        }
      
        else if(player2Collision(player2tab, player1tab, player2Wall))
        {
            ifOneIsTrue = true;
            System.out.println("Le joueur 2 à gagné");
        }
        
        return ifOneIsTrue;
    }
}
