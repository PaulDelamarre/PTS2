/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tronv2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 *
 * @author verri
 */
public class TronV2 extends Application 
{
    Timer itsTimer;
    private ArrayList blueTab = new ArrayList<Wall>();
    private ArrayList redTab = new ArrayList<Wall>();
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException
    {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: Gainsboro;-fx-border-color: blue;");
        Scene scene2 = new Scene(root, 1000, 500);
        
        primaryStage.setTitle("Tron");
        primaryStage.setScene(scene2);
        Button btn = new Button();
        
        btn.setMaxHeight(80);
        btn.setMaxWidth(290);
        btn.setTranslateY(180);
        btn.setTranslateX(-10);
        btn.setStyle("-fx-background-color: #ff0000; ");
        btn.setStyle("-fx-background-color: transparent;"); 
        
        FileInputStream Menu = new FileInputStream("src/tronv2/Menu_princip_750.jpg");
        Image imageMenu = new Image(Menu, 750, 750, false, false);
        ImageView MenuPrincip = new ImageView(imageMenu);
        root.getChildren().add(MenuPrincip);
        root.getChildren().add(btn);
                        
        FileInputStream inputB = new FileInputStream("src/tronv2/moto.png");
        Image imageMotoB = new Image(inputB, 32, 32, false, false);
        ImageView motoB = new ImageView(imageMotoB);
                
        FileInputStream inputR = new FileInputStream("src/tronv2/moto2.png");
        Image imageMotoR = new Image(inputR, 32, 32, false, false);
        ImageView motoR = new ImageView(imageMotoR);
        
        primaryStage.show();
            
        btn.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                Group root = new Group();
            
                Scene scene = new Scene(root, 1000, 500);
                primaryStage.setScene(scene);


                primaryStage.show();
                
                
                Line linePlayer1 = new Line(50, 250, 50, 250); 
                linePlayer1.setStroke(Color.BLUE);
                linePlayer1.setStrokeWidth(5);

                Line linePlayer2 = new Line(950, 250, 950, 250); 
                linePlayer2.setStroke(Color.RED);
                linePlayer2.setStrokeWidth(5);
 
                Player player1 = new Player(1, 50, 250, true, false, linePlayer1);
                Player player2 = new Player(2, 950, 250, false, true, linePlayer2);
                
                Collision collision = new Collision();
                
                root.getChildren().add(linePlayer1);
                root.getChildren().add(linePlayer2);
                
                root.getChildren().add(motoB);
                root.getChildren().add(motoR);
                
                Button btn2 = new Button();
                    btn2.setText("Quit");
                    btn2.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("Menu");
                            primaryStage.setScene(scene2);
                            primaryStage.show();
                            itsTimer.cancel();
                    }
                });
                    
                    
                root.getChildren().add(btn2);
                
                TimerTask gameLoop = new TimerTask() 
                {
                    @Override
                    public void run()
                    {
                        Platform.runLater(() ->
                        {
                            player1.moveRight(player1);
                            player2.moveLeft(player2);
                            player1.draw();
                            player2.draw();
                            
                            
                            Line blueLine = new Line(player1.getPosX(), player1.getPosY(), player1.getPosX(), player1.getPosY()); 
                            blueLine.setStroke(Color.BLUE);
                            blueLine.setStrokeWidth(5);

                            Wall blueWall = new Wall (blueLine);
                            root.getChildren().add(blueLine);
                            blueWall.setIsWall(true);
                            blueWall.setPosX((int) player1.getPosX());
                            blueWall.setPosY((int) player1.getPosY());
                            
                            Line redLine = new Line(player2.getPosX(), player2.getPosY(), player2.getPosX(), player2.getPosY()); 
                            redLine.setStroke(Color.RED);
                            redLine.setStrokeWidth(5);

                            Wall redWall = new Wall (redLine);
                            root.getChildren().add(redLine);
                            redWall.setIsWall(true);
                            redWall.setPosX((int) player2.getPosX());
                            redWall.setPosY((int) player2.getPosY());
                            
                            motoB.setX(player1.getPosX()-20);
                            motoB.setY(player1.getPosY()-15);
                            motoR.setX(player2.getPosX()-10);
                            motoR.setY(player2.getPosY()-15);
                            

                            /*
                            System.out.println("Pos X " + yo.getPosX());
                            System.out.println("Pos Y " + yo.getPosY());
                            */

                            if(collision.collisions(player1, player2, redWall,blueWall,blueTab,redTab) ) // || collision.collision(player1, yo)
                            {
                                itsTimer.cancel();
                                primaryStage.setScene(scene2);
                                primaryStage.show();
                                blueTab.clear();
                                redTab.clear();
                            }
                            blueTab.add(blueWall);
                            redTab.add(redWall);
                            System.out.println(redTab.size());
                            System.out.println(blueTab.size());
                            
                        });
                    }
                };
                
                itsTimer = new Timer();
                itsTimer.schedule(gameLoop, 1000,4);

                scene.setOnKeyPressed(key ->
                {
                    KeyCode keyCode = key.getCode();
                    if(keyCode.equals(KeyCode.RIGHT))
                    {
                        player1.setMoveRight(true);
                        player1.moveRight(player1);
                    }
                    if(keyCode.equals(KeyCode.DOWN))
                    {
                        player1.setMoveDown(true);
                        player1.moveDown(player1);
                    }
                    if(keyCode.equals(KeyCode.LEFT))
                    {
                        player1.setMoveLeft(true);
                        player1.moveLeft(player1);
                    }
                    if(keyCode.equals(KeyCode.UP))
                    {
                        player1.setMoveUp(true);
                        player1.moveUp(player1);
                    }
                    
                    
                    
                    if(keyCode.equals(KeyCode.D))
                    {
                        player2.setMoveRight(true);
                        player2.moveRight(player2);
                    }
                    if(keyCode.equals(KeyCode.S))
                    {
                        player2.setMoveDown(true);
                        player2.moveDown(player2);
                    }
                    if(keyCode.equals(KeyCode.Q))
                    {
                        player2.setMoveLeft(true);
                        player2.moveLeft(player2);
                    }
                    if(keyCode.equals(KeyCode.Z))
                    {
                        player2.setMoveUp(true);
                        player2.moveUp(player2);
                    }
                });
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
    
    @Override
    public void stop()
    {
        itsTimer.cancel();
    }
}
