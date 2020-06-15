/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tronv2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
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
    MusicPlayer musicGame;
    Boolean musicOn ;
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException
    {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: Gainsboro;-fx-border-color: blue;");
        Scene menu = new Scene(root, 750, 750);
        
        primaryStage.setTitle("Tron");
        primaryStage.setScene(menu);
        Button btnPlay = new Button();
        Button btnOption = new Button();
        
        btnPlay.setMaxHeight(80);
        btnPlay.setMaxWidth(290);
        btnPlay.setTranslateY(180);
        btnPlay.setTranslateX(-10);
        btnPlay.setStyle("-fx-background-color: #ff0000; ");
        btnPlay.setStyle("-fx-background-color: transparent;"); 
        
        btnOption.setMaxHeight(80);
        btnOption.setMaxWidth(290);
        btnOption.setTranslateY(280);
        btnOption.setTranslateX(-10);
        btnOption.setStyle("-fx-background-color: #ff0000; ");
        btnOption.setStyle("-fx-background-color: transparent;"); 
        
        FileInputStream Menu = new FileInputStream("src/tronv2/img/Menu_princip_750.jpg");
        Image imageMenu = new Image(Menu, 750, 750, false, false);
        ImageView menuPrincip = new ImageView(imageMenu);
        root.getChildren().add(menuPrincip);
        root.getChildren().add(btnPlay);
        root.getChildren().add(btnOption);
                        
        FileInputStream inputB = new FileInputStream("src/tronv2/img/moto.png");
        Image imageMotoB = new Image(inputB, 32, 32, false, false);
        ImageView motoB = new ImageView(imageMotoB);
                
        FileInputStream inputR = new FileInputStream("src/tronv2/img/moto2.png");
        Image imageMotoR = new Image(inputR, 32, 32, false, false);
        ImageView motoR = new ImageView(imageMotoR);
        
        Player player1 = new Player(1, 50, 375);
        Player player2 = new Player(2, 650, 375);
        Collision collision = new Collision();
        
        primaryStage.show();
        
        try 
        {
            musicGame = new MusicPlayer("src/tronv2/musics/daft-punk-the-game-has-changed-tron-legacy.wav");
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
        }
        musicOn=musicGame.playMusic();
        btnOption.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                AnchorPane root = new AnchorPane();
                Scene scene = new Scene(root, 750, 750,Color.BLACK);
                primaryStage.setScene(scene);
                Button btnVolume = new Button();
                root.getChildren().add(btnVolume);
                btnVolume.setLayoutX(390);
                btnVolume.setLayoutY(610);
                btnVolume.setPrefSize(70,40);
                btnVolume.setMaxSize(70, 40);
                btnVolume.setMinSize(65, 35);
                btnVolume.setStyle("-fx-background-color: #000000");
                
                Button btnQuitOption = new Button();
                root.getChildren().add(btnQuitOption);
                btnQuitOption.setLayoutX(590);
                btnQuitOption.setLayoutY(610);
                btnQuitOption.setPrefSize(70,40);
                btnQuitOption.setMaxSize(70, 40);
                btnQuitOption.setMinSize(65, 35);
                btnQuitOption.setStyle("-fx-background-color: #000000");
                FileInputStream opt;
                Image imgOpt;
                BackgroundImage backOpt;
                try 
                {
                    opt = new FileInputStream("src/tronv2/img/quit_blue.png");
                    imgOpt = new Image(opt);
                    backOpt = new BackgroundImage(imgOpt, null, null, null, null);
                    btnQuitOption.setGraphic(new ImageView(imgOpt));
                } 
                catch (FileNotFoundException ex) 
                {
                    Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                btnQuitOption.setOnMousePressed(new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent event) 
                    {
                        FileInputStream opt;
                        Image imgOpt;
                        BackgroundImage backOpt;
                        try 
                        {
                            opt = new FileInputStream("src/tronv2/img/quit_red.png");
                            imgOpt = new Image(opt);
                            backOpt = new BackgroundImage(imgOpt, null, null, null, null);
                            btnQuitOption.setGraphic(new ImageView(imgOpt));
                        } 
                        catch (FileNotFoundException ex) 
                        {
                            Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                btnQuitOption.setOnAction(new EventHandler<ActionEvent>() 
                {
                    @Override
                    public void handle(ActionEvent event) 
                    {
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
                FileInputStream vol;
                Image imgVol;
                BackgroundImage backImg;
                if(musicOn)
                {
                    try 
                    {
                        vol = new FileInputStream("src/tronv2/img/TEY.png");
                        imgVol = new Image(vol);
                        backImg = new BackgroundImage(imgVol, null, null, null, null);
                        btnVolume.setGraphic(new ImageView(imgVol));  
                    } 
                    catch (FileNotFoundException ex) 
                    {
                        Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else
                {
                    try 
                    {
                        vol = new FileInputStream("src/tronv2/img/NEY.png");
                        imgVol = new Image(vol);
                        backImg = new BackgroundImage(imgVol, null, null, null, null);
                        btnVolume.setGraphic(new ImageView(imgVol));
                    }
                    catch (FileNotFoundException ex) 
                    {
                        Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                FileInputStream ctrls;
                try 
                {
                    ctrls = new FileInputStream("src/tronv2/img/CONTROLS.jpg");
                    Image imgCtlrs = new Image(ctrls, 750, 750, false, false);
                    ImageView controls = new ImageView(imgCtlrs);
                    root.getChildren().add(controls);
                    controls.toBack();   
                } 
                catch (FileNotFoundException ex) 
                {
                    Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                }
                
               
                btnVolume.setOnMousePressed(new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent event) 
                    {
                        FileInputStream vol;
                        Image imgVol;
                        BackgroundImage backImg;
                        if(musicOn)
                        {
                            try 
                            {
                                vol = new FileInputStream("src/tronv2/img/TEY_A.png");
                                imgVol = new Image(vol);
                                backImg = new BackgroundImage(imgVol, null, null, null, null);
                                btnVolume.setGraphic(new ImageView(imgVol));  
                            } 
                            catch (FileNotFoundException ex) 
                            {
                                Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        
                        }
                        else
                        {
                            try 
                            {
                                vol = new FileInputStream("src/tronv2/img/NEY_A.png");
                                imgVol = new Image(vol);
                                backImg = new BackgroundImage(imgVol, null, null, null, null);
                                btnVolume.setGraphic(new ImageView(imgVol));
                            } 
                            catch (FileNotFoundException ex) 
                            {
                                Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                        }
                    }
                    
                });
                btnVolume.setOnAction(new EventHandler<ActionEvent>() 
                {
                    @Override
                    public void handle(ActionEvent event) 
                    {
                        FileInputStream vol;
                        Image imgVol;
                        BackgroundImage backImg;
                        if(musicOn)
                        {
                            try 
                            {
                                vol = new FileInputStream("src/tronv2/img/TEY_A.png");
                                imgVol = new Image(vol);
                                backImg = new BackgroundImage(imgVol, null, null, null, null);
                                btnVolume.setGraphic(new ImageView(imgVol));
                                vol = new FileInputStream("src/tronv2/img/NEY.png");
                                imgVol = new Image(vol);
                                backImg = new BackgroundImage(imgVol, null, null, null, null);
                                btnVolume.setGraphic(new ImageView(imgVol));
                                musicOn=musicGame.stopMusic();
                            } 
                            catch (FileNotFoundException ex) 
                            {
                                Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        
                        }
                        else
                        {
                            try 
                            {
                                vol = new FileInputStream("src/tronv2/img/NEY_A.png");
                                imgVol = new Image(vol);
                                backImg = new BackgroundImage(imgVol, null, null, null, null);
                                btnVolume.setGraphic(new ImageView(imgVol));
                                vol = new FileInputStream("src/tronv2/img/TEY.png");
                                imgVol = new Image(vol);
                                backImg = new BackgroundImage(imgVol, null, null, null, null);
                                btnVolume.setGraphic(new ImageView(imgVol));
                                musicOn=musicGame.playMusic();
                            } 
                            catch (FileNotFoundException ex) 
                            {
                                Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                        }
                    }
                    
                });
                
                primaryStage.show();
            }
        });
        
        
        btnPlay.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                Group root = new Group();
                Scene scene = new Scene(root, 750, 750,Color.BLACK);
                primaryStage.setScene(scene);
                primaryStage.show();
                if(musicOn)
                {
                    musicGame.stopMusic();
                    try 
                    {
                        musicGame = new MusicPlayer("src/tronv2/musics/powerwalkin-by-future-joust-electro-music.wav");
                    } 
                    catch (IOException ex) 
                    {
                        Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    musicGame.playMusic();
                }
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
                btnMenu.setTranslateY(300);
                btnMenu.setTranslateX(350);
                btnMenu.toFront();
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
                        if(musicOn)
                        {
                            musicGame.stopMusic();
                            try 
                            {
                                musicGame = new MusicPlayer("src/tronv2/musics/daft-punk-the-game-has-changed-tron-legacy.wav");
                            } 
                            catch (IOException ex) 
                            {
                                Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        musicGame.playMusic();
                        }
                }
            });
                    
                            
                    
               
                
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
                            
                            if(collision.collisions(player1, player2, redWall,blueWall,blueTab,redTab))
                            {
                                itsTimer.cancel();
                                blueTab.clear();
                                redTab.clear();
                                motoR.toBack();
                                motoB.toBack();
                                btnMenu.toFront();
                                motoB.translateXProperty().set(0);
                                motoB.translateYProperty().set(0);
                                motoR.translateXProperty().set(0);
                                motoR.translateYProperty().set(0);
                                motoB.rotateProperty().setValue(0);
                                motoR.rotateProperty().setValue(180);
                                root.getChildren().add(btnMenu);
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
        musicGame.stopMusic();
    }
}
