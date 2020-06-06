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
        Scene menu = new Scene(root, 750, 750);
        
        primaryStage.setTitle("Tron");
        primaryStage.setScene(menu);
        Button btnPlay = new Button();
        
        btnPlay.setMaxHeight(80);
        btnPlay.setMaxWidth(290);
        btnPlay.setTranslateY(180);
        btnPlay.setTranslateX(-10);
        btnPlay.setStyle("-fx-background-color: #ff0000; ");
        btnPlay.setStyle("-fx-background-color: transparent;"); 
        
        FileInputStream Menu = new FileInputStream("src/tronv2/Menu_princip_750.jpg");
        Image imageMenu = new Image(Menu, 750, 750, false, false);
        ImageView MenuPrincip = new ImageView(imageMenu);
        root.getChildren().add(MenuPrincip);
        root.getChildren().add(btnPlay);
                        
        FileInputStream inputB = new FileInputStream("src/tronv2/moto.png");
        Image imageMotoB = new Image(inputB, 32, 32, false, false);
        ImageView motoB = new ImageView(imageMotoB);
                
        FileInputStream inputR = new FileInputStream("src/tronv2/moto2.png");
        Image imageMotoR = new Image(inputR, 32, 32, false, false);
        ImageView motoR = new ImageView(imageMotoR);
        
        Player player1 = new Player(1, 50, 375);
        Player player2 = new Player(2, 650, 375);
        Collision collision = new Collision();
        
        primaryStage.show();
            
        btnPlay.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                Group root = new Group();
                Scene scene = new Scene(root, 750, 750,Color.BLACK);
                primaryStage.setScene(scene);
                primaryStage.show();
                
                player1.start();
                player2.start();
                
                player1.moveRight();
                player2.moveLeft();
                
                root.getChildren().add(motoB);
                root.getChildren().add(motoR);
                motoB.rotateProperty().setValue(0);
                motoR.rotateProperty().setValue(180);
                
                Button btnMenu = new Button();
                btnMenu.setText("Quit");
                btnMenu.setOnAction(new EventHandler<ActionEvent>() 
                {
                    @Override
                    public void handle(ActionEvent event) {
                        //System.out.println("Menu");
                        primaryStage.setScene(menu);
                        primaryStage.show();
                        itsTimer.cancel();
                        redTab.clear();
                        blueTab.clear();
                                
                        motoB.translateXProperty().set(0);
                        motoB.translateYProperty().set(0);
                        motoR.translateXProperty().set(0);
                        motoR.translateYProperty().set(0);
                        motoB.rotateProperty().setValue(0);
                        motoR.rotateProperty().setValue(180);
                }
            });
                    
                            
                    
                root.getChildren().add(btnMenu);
                
                TimerTask gameLoop = new TimerTask() 
                {
                    @Override
                    public void run()
                    {
                        Platform.runLater(() ->
                        {
                            player1.draw();
                            player2.draw();
                            
                            
                            Line blueLine = new Line(player1.getPosX(), player1.getPosY(), player1.getPosX(), player1.getPosY()); 
                            blueLine.setStroke(Color.BLUE);
                            blueLine.setStrokeWidth(5);

                            Wall blueWall = new Wall (blueLine);
                            root.getChildren().add(blueLine);
                            blueWall.setPosX((int) player1.getPosX());
                            blueWall.setPosY((int) player1.getPosY());
                            
                            Line redLine = new Line(player2.getPosX(), player2.getPosY(), player2.getPosX(), player2.getPosY()); 
                            redLine.setStroke(Color.RED);
                            redLine.setStrokeWidth(5);

                            Wall redWall = new Wall (redLine);
                            root.getChildren().add(redLine);
                            redWall.setPosX((int) player2.getPosX());
                            redWall.setPosY((int) player2.getPosY());
                            
                            
                            motoB.setX(player1.getPosX()-24);
                            motoB.setY(player1.getPosY()-15);
                            motoR.setX(player2.getPosX()-9);
                            motoR.setY(player2.getPosY()-18);
                            
                            
                            blueTab.add(blueWall);
                            redTab.add(redWall);
                            /*
                            System.out.println(redTab.size());
                            System.out.println(blueTab.size());
                            */
                            if(collision.collisions(player1, player2, redWall,blueWall,blueTab,redTab))
                            {
                                itsTimer.cancel();
                                /*
                                primaryStage.setScene(menu);
                                primaryStage.show();
                                */
                                blueTab.clear();
                                redTab.clear();
                                
                                motoB.translateXProperty().set(0);
                                motoB.translateYProperty().set(0);
                                motoR.translateXProperty().set(0);
                                motoR.translateYProperty().set(0);
                                motoB.rotateProperty().setValue(0);
                                motoR.rotateProperty().setValue(180);
                            }
                            motoR.toFront();
                            motoB.toFront();
                            
                        });
                    }
                };
                
                itsTimer = new Timer();
                itsTimer.schedule(gameLoop, 1000,4);

                scene.setOnKeyPressed(key ->
                {
                    KeyCode keyCode = key.getCode();
                    if(keyCode.equals(KeyCode.D))
                    {
                        player1.moveRight();
                        
                        if(!player1.getMoveLeft())
                        {
                            motoB.rotateProperty().setValue(0);
                            motoB.translateXProperty().set(0);
                            motoB.translateYProperty().set(0);
                        }
                        
                    }
                    if(keyCode.equals(KeyCode.S))
                    {
                        player1.moveDown();
                        
                        if(!player1.getMoveUp())
                        {
                            motoB.rotateProperty().setValue(90);
                            motoB.translateXProperty().set(7);
                            motoB.translateYProperty().set(-7);
                        }
                    }
                    if(keyCode.equals(KeyCode.Q))
                    {
                        player1.moveLeft();
                        
                        if(!player1.getMoveRight())
                        {
                            motoB.rotateProperty().setValue(180);
                            motoB.translateXProperty().set(17);
                            motoB.translateYProperty().set(-4);
                        }
                    }
                    if(keyCode.equals(KeyCode.Z))
                    {
                        player1.moveUp();
                        
                        if(!player1.getMoveDown())
                        {
                            motoB.rotateProperty().setValue(-90);
                            motoB.translateXProperty().set(10);
                            motoB.translateYProperty().set(6);
                        }
                    }
                    
                    
                    
                    if(keyCode.equals(KeyCode.RIGHT))
                    {
                        player2.moveRight();
                        
                        if(!player2.getMoveLeft())
                        {
                            motoR.rotateProperty().setValue(0);
                            motoR.translateXProperty().set(-15);
                            motoR.translateYProperty().set(3);
                        }
                    }
                    if(keyCode.equals(KeyCode.DOWN))
                    {
                        player2.moveDown();
                        
                        if(!player2.getMoveUp())
                        {
                            motoR.rotateProperty().setValue(90);
                            motoR.translateXProperty().set(-8);
                            motoR.translateYProperty().set(-7);
                        }
                    }
                    if(keyCode.equals(KeyCode.LEFT))
                    {
                        player2.moveLeft();
                        
                        if(!player2.getMoveRight())
                        {
                            motoR.rotateProperty().setValue(180);
                            motoR.translateXProperty().set(0);
                            motoR.translateYProperty().set(0);
                        }
                    }
                    if(keyCode.equals(KeyCode.UP))
                    {
                        player2.moveUp();
                        
                        if(!player2.getMoveDown())
                        {
                            motoR.rotateProperty().setValue(-90);
                            motoR.translateXProperty().set(-5);
                            motoR.translateYProperty().set(8);
                        }
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
