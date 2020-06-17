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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.CYAN;
import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.PINK;
import static javafx.scene.paint.Color.PURPLE;
import static javafx.scene.paint.Color.RED;
import static javafx.scene.paint.Color.YELLOW;
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
    private static Color colorP1 = BLUE;
    private static Color colorP2 = RED;
    Boolean musicOn ;
    private boolean colorChosen = false;
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException
    {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: Gainsboro;-fx-border-color: blue;");
        Scene menu = new Scene(root, 750, 750);
        
        primaryStage.setTitle("Tron");
        primaryStage.setScene(menu);
        Button btnParam = new Button();
        Button btnOption = new Button();
        
        btnParam.setMaxHeight(80);
        btnParam.setMaxWidth(290);
        btnParam.setTranslateY(180);
        btnParam.setTranslateX(-10);
        btnParam.setStyle("-fx-background-color: #ff0000; ");
        btnParam.setStyle("-fx-background-color: transparent;"); 
        
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
        root.getChildren().add(btnParam);
        root.getChildren().add(btnOption);
        
        FileInputStream inputB = new FileInputStream("src/tronv2/img/blue_moto.png");
        Image imageMotoB = new Image(inputB, 100,100, false, false);
        ImageView moto1 = new ImageView(imageMotoB);
        ImageView moto1V = new ImageView(imageMotoB);
        moto1V.setLayoutX(70);
        moto1V.setLayoutY(160);
        
        
        FileInputStream inputR = new FileInputStream("src/tronv2/img/moto2.png");
        Image imageMotoR = new Image(inputR, 100, 100, false, false);
        ImageView moto2 = new ImageView(imageMotoR);
        ImageView moto2V = new ImageView(imageMotoR);
        moto2V.setLayoutX(70);
        moto2V.setLayoutY(260);
        
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
                
                try 
                {
                    FileInputStream opt;
                    Image imgOpt;
                    opt = new FileInputStream("src/tronv2/img/quit_blue.png");
                    imgOpt = new Image(opt);
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
                        
                        try 
                        {
                            FileInputStream opt;
                            Image imgOpt;
                            opt = new FileInputStream("src/tronv2/img/quit_red.png");
                            imgOpt = new Image(opt);
                            
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
                        primaryStage.setScene(menu);
                        primaryStage.show();
                    }
                });
                
                if(musicOn)
                {
                    
                    try 
                    {
                        FileInputStream vol;
                        Image imgVol;
                        vol = new FileInputStream("src/tronv2/img/TEY.png");
                        imgVol = new Image(vol);
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
                        FileInputStream vol;
                        Image imgVol;
                        vol = new FileInputStream("src/tronv2/img/NEY.png");
                        imgVol = new Image(vol);
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
                        
                        if(musicOn)
                        {
                            try 
                            {
                                FileInputStream vol;
                                Image imgVol;
                                vol = new FileInputStream("src/tronv2/img/TEY_A.png");
                                imgVol = new Image(vol);
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
                                FileInputStream vol;
                                Image imgVol;
                                vol = new FileInputStream("src/tronv2/img/NEY_A.png");
                                imgVol = new Image(vol);
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
                        
                        if(musicOn)
                        {
                            try 
                            {
                                FileInputStream vol;
                                Image imgVol;
                                vol = new FileInputStream("src/tronv2/img/NEY.png");
                                imgVol = new Image(vol);
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
                                FileInputStream vol;
                                Image imgVol;
                                vol = new FileInputStream("src/tronv2/img/TEY.png");
                                imgVol = new Image(vol);
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
        
        btnParam.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                AnchorPane root = new AnchorPane();
                root.setStyle("-fx-background-color: #000000;");
                Scene scene = new Scene(root, 750, 750);
                root.getChildren().add(moto1V);
                root.getChildren().add(moto2V);
                primaryStage.setScene(scene);
                
                
                try 
                {
                    FileInputStream chx = new FileInputStream("src/tronv2/img/Menu_Choice.jpg");
                    Image imgChx = new Image(chx);
                    ImageView menuChoice = new ImageView(imgChx);
                    root.getChildren().add(menuChoice);
                    menuChoice.toBack();
                } 
                catch (FileNotFoundException ex) 
                {
                    Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                Button btnPlay = new Button();    
                btnPlay.setLayoutX(500);
                btnPlay.setLayoutY(620);
                btnPlay.setMinSize(200, 60);
                btnPlay.setStyle("-fx-background-color: transparent;");
                try 
                {
                    FileInputStream ply = new FileInputStream("src/tronv2/img/play_btn.png");
                    Image imgPly = new Image(ply);
                    ImageView play = new ImageView(imgPly);
                    play.setLayoutX(500);
                    play.setLayoutY(620);
                    root.getChildren().add(play);
                } 
                catch (FileNotFoundException ex) 
                {
                    Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
                // Group
                Button btnBlue1 = new Button();
                btnBlue1.setLayoutX(200);
                btnBlue1.setLayoutY(200);
                btnBlue1.setMinSize(40, 40);
                btnBlue1.setStyle("-fx-background-color: transparent;");
                try 
                {
                    FileInputStream clr = new FileInputStream("src/tronv2/img/Chx_B.png");
                    Image imgClr = new Image(clr);
                    ImageView color = new ImageView(imgClr);
                    color.setLayoutX(200);
                    color.setLayoutY(200);
                    root.getChildren().add(color);
                } 
                catch (FileNotFoundException ex) 
                {
                    Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                }
                btnBlue1.setOnAction(new EventHandler<ActionEvent>() 
                {
                    @Override
                    public void handle(ActionEvent event) 
                    {
                        try 
                        {
                            FileInputStream mt1 = new FileInputStream("src/tronv2/img/blue_moto.png");
                            Image imgMt1 = new Image(mt1);
                            moto1V.setImage(imgMt1);
                            moto1.setImage(imgMt1);
                            colorP1=BLUE;
                        } 
                        catch (FileNotFoundException ex) 
                        {
                            Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            
                });
                Button btnRed1 = new Button();
                btnRed1.setLayoutX(280);
                btnRed1.setLayoutY(200);
                btnRed1.setMinSize(40, 40);
                btnRed1.setStyle("-fx-background-color: transparent;");
                try 
                {
                    FileInputStream clr = new FileInputStream("src/tronv2/img/Chx_R.png");
                    Image imgClr = new Image(clr);
                    ImageView color = new ImageView(imgClr);
                    color.setLayoutX(280);
                    color.setLayoutY(200);
                    root.getChildren().add(color);
                } 
                catch (FileNotFoundException ex) 
                {
                    Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                }
                btnRed1.setOnAction(new EventHandler<ActionEvent>() 
                {
                    @Override
                    public void handle(ActionEvent event) 
                    {
                        try 
                        {
                            FileInputStream mt1 = new FileInputStream("src/tronv2/img/red_moto.png");
                            Image imgMt1 = new Image(mt1);
                            moto1V.setImage(imgMt1);
                            moto1.setImage(imgMt1);
                            colorP1=RED;
                        } 
                        catch (FileNotFoundException ex) 
                        {
                            Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            
                });
                Button btnYellow1 = new Button();
                btnYellow1.setLayoutX(360);
                btnYellow1.setLayoutY(200);
                btnYellow1.setMinSize(40, 40);
                btnYellow1.setStyle("-fx-background-color: transparent;");
                try 
                {
                    FileInputStream clr = new FileInputStream("src/tronv2/img/Chx_Y.png");
                    Image imgClr = new Image(clr);
                    ImageView color = new ImageView(imgClr);
                    color.setLayoutX(360);
                    color.setLayoutY(200);
                    root.getChildren().add(color);
                    
                } 
                catch (FileNotFoundException ex) 
                {
                    Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                }
                btnYellow1.setOnAction(new EventHandler<ActionEvent>() 
                {
                    @Override
                    public void handle(ActionEvent event) 
                    {
                        try 
                        {
                            FileInputStream mt1 = new FileInputStream("src/tronv2/img/yellow_moto.png");
                            Image imgMt1 = new Image(mt1);
                            moto1V.setImage(imgMt1);
                            moto1.setImage(imgMt1);
                            colorP1=YELLOW;
                        } 
                        catch (FileNotFoundException ex) 
                        {
                            Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            
                });
                Button btnCyan1 = new Button();
                btnCyan1.setLayoutX(440);
                btnCyan1.setLayoutY(200);
                btnCyan1.setMinSize(40, 40);
                btnCyan1.setStyle("-fx-background-color: transparent;");
                try 
                {
                    FileInputStream clr = new FileInputStream("src/tronv2/img/Chx_C.png");
                    Image imgClr = new Image(clr);
                    ImageView color = new ImageView(imgClr);
                    color.setLayoutX(440);
                    color.setLayoutY(200);
                    root.getChildren().add(color);
                } 
                catch (FileNotFoundException ex) 
                {
                    Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                }
                btnCyan1.setOnAction(new EventHandler<ActionEvent>() 
                {
                    @Override
                    public void handle(ActionEvent event) 
                    {
                        try 
                        {
                            FileInputStream mt1 = new FileInputStream("src/tronv2/img/cyan_moto.png");
                            Image imgMt1 = new Image(mt1);
                            moto1V.setImage(imgMt1);
                            moto1.setImage(imgMt1);
                            colorP1=CYAN;
                        } 
                        catch (FileNotFoundException ex) 
                        {
                            Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            
                });
                Button btnPurple1 = new Button();
                btnPurple1.setLayoutX(520);
                btnPurple1.setLayoutY(200);
                btnPurple1.setMinSize(40, 40);
                btnPurple1.setStyle("-fx-background-color: transparent;");
                try 
                {
                    FileInputStream clr = new FileInputStream("src/tronv2/img/Chx_Pur.png");
                    Image imgClr = new Image(clr);
                    ImageView color = new ImageView(imgClr);
                    color.setLayoutX(520);
                    color.setLayoutY(200);
                    root.getChildren().add(color);
                } 
                catch (FileNotFoundException ex) 
                {
                    Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                }
                btnPurple1.setOnAction(new EventHandler<ActionEvent>() 
                {
                    @Override
                    public void handle(ActionEvent event) 
                    {
                        try 
                        {
                            FileInputStream mt1 = new FileInputStream("src/tronv2/img/purple_moto.png");
                            Image imgMt1 = new Image(mt1);
                            moto1V.setImage(imgMt1);
                            moto1.setImage(imgMt1);
                            colorP1=PURPLE;
                        } 
                        catch (FileNotFoundException ex) 
                        {
                            Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            
                });
                Button btnPink1 = new Button();
                btnPink1.setLayoutX(600);
                btnPink1.setLayoutY(200);
                btnPink1.setMinSize(40, 40);
                btnPink1.setStyle("-fx-background-color: transparent;");
                try 
                {
                    FileInputStream clr = new FileInputStream("src/tronv2/img/Chx_P.png");
                    Image imgClr = new Image(clr);
                    ImageView color = new ImageView(imgClr);
                    color.setLayoutX(600);
                    color.setLayoutY(200);
                    root.getChildren().add(color);
                } 
                catch (FileNotFoundException ex) 
                {
                    Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                }
                btnPink1.setOnAction(new EventHandler<ActionEvent>() 
                {
                    @Override
                    public void handle(ActionEvent event) 
                    {
                        try 
                        {
                            FileInputStream mt1 = new FileInputStream("src/tronv2/img/pink_moto.png");
                            Image imgMt1 = new Image(mt1);
                            moto1V.setImage(imgMt1);
                            moto1.setImage(imgMt1);
                            colorP1=PINK;
                        } 
                        catch (FileNotFoundException ex) 
                        {
                            Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            
                });
                Button btnGreen1 = new Button();
                btnGreen1.setLayoutX(680);
                btnGreen1.setLayoutY(200);
                btnGreen1.setMinSize(40, 40);
                btnGreen1.setStyle("-fx-background-color: transparent;");
                try 
                {
                    FileInputStream clr = new FileInputStream("src/tronv2/img/Chx_V.png");
                    Image imgClr = new Image(clr);
                    ImageView color = new ImageView(imgClr);
                    color.setLayoutX(680);
                    color.setLayoutY(200);
                    root.getChildren().add(color);
                } 
                catch (FileNotFoundException ex) 
                {
                    Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                }
                btnGreen1.setOnAction(new EventHandler<ActionEvent>() 
                {
                    @Override
                    public void handle(ActionEvent event) 
                    {
                        try 
                        {
                            FileInputStream mt1 = new FileInputStream("src/tronv2/img/green_moto.png");
                            Image imgMt1 = new Image(mt1);
                            moto1V.setImage(imgMt1);
                            moto1.setImage(imgMt1);
                            colorP1=GREEN;
                        } 
                        catch (FileNotFoundException ex) 
                        {
                            Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            
                });
                

 
                Button btnBlue2 = new Button();
                btnBlue2.setLayoutX(200);
                btnBlue2.setLayoutY(300);
                btnBlue2.setMinSize(40, 40);
                btnBlue2.setStyle("-fx-background-color: transparent;");
                try 
                {
                    FileInputStream clr = new FileInputStream("src/tronv2/img/Chx_B.png");
                    Image imgClr = new Image(clr);
                    ImageView color = new ImageView(imgClr);
                    color.setLayoutX(200);
                    color.setLayoutY(300);
                    root.getChildren().add(color);
                } 
                catch (FileNotFoundException ex) 
                {
                    Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                }
                btnBlue2.setOnAction(new EventHandler<ActionEvent>() 
                {
                    @Override
                    public void handle(ActionEvent event) 
                    {
                        try 
                        {
                            FileInputStream mt2 = new FileInputStream("src/tronv2/img/blue_moto.png");
                            Image imgMt2 = new Image(mt2);
                            moto2V.setImage(imgMt2);
                            moto2.setImage(imgMt2);
                            colorP2=BLUE;
                        } 
                        catch (FileNotFoundException ex) 
                        {
                            Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            
                });
                
                Button btnRed2 = new Button();
                btnRed2.setLayoutX(280);
                btnRed2.setLayoutY(300);
                btnRed2.setMinSize(40, 40);
                btnRed2.setStyle("-fx-background-color: transparent;");
                try 
                {
                    FileInputStream clr = new FileInputStream("src/tronv2/img/Chx_R.png");
                    Image imgClr = new Image(clr);
                    ImageView color = new ImageView(imgClr);
                    color.setLayoutX(280);
                    color.setLayoutY(300);
                    root.getChildren().add(color);
                } 
                catch (FileNotFoundException ex) 
                {
                    Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                }
                btnRed2.setOnAction(new EventHandler<ActionEvent>() 
                {
                    @Override
                    public void handle(ActionEvent event) 
                    {
                        try 
                        {
                            FileInputStream mt2 = new FileInputStream("src/tronv2/img/red_moto.png");
                            Image imgMt2 = new Image(mt2);
                            moto2V.setImage(imgMt2);
                            moto2.setImage(imgMt2);
                            colorP2=RED;
                            
                        } 
                        catch (FileNotFoundException ex) 
                        {
                            Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            
                });
                
                Button btnYellow2 = new Button();
                btnYellow2.setLayoutX(360);
                btnYellow2.setLayoutY(300);
                btnYellow2.setMinSize(40, 40);
                btnYellow2.setStyle("-fx-background-color: transparent;");
                try 
                {
                    FileInputStream clr = new FileInputStream("src/tronv2/img/Chx_Y.png");
                    Image imgClr = new Image(clr);
                    ImageView color = new ImageView(imgClr);
                    color.setLayoutX(360);
                    color.setLayoutY(300);
                    root.getChildren().add(color);
                } 
                catch (FileNotFoundException ex) 
                {
                    Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                }
                btnYellow2.setOnAction(new EventHandler<ActionEvent>() 
                {
                    @Override
                    public void handle(ActionEvent event) 
                    {
                        try 
                        {
                            FileInputStream mt2 = new FileInputStream("src/tronv2/img/yellow_moto.png");
                            Image imgMt2 = new Image(mt2);
                            moto2V.setImage(imgMt2);
                            moto2.setImage(imgMt2);
                            colorP2=YELLOW;
                        } 
                        catch (FileNotFoundException ex) 
                        {
                            Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            
                });
                
                Button btnCyan2 = new Button();
                btnCyan2.setLayoutX(440);
                btnCyan2.setLayoutY(300);
                btnCyan2.setMinSize(40, 40);
                btnCyan2.setStyle("-fx-background-color: transparent;");
                try 
                {
                    FileInputStream clr = new FileInputStream("src/tronv2/img/Chx_C.png");
                    Image imgClr = new Image(clr);
                    ImageView color = new ImageView(imgClr);
                    color.setLayoutX(440);
                    color.setLayoutY(300);
                    root.getChildren().add(color);
                } 
                catch (FileNotFoundException ex) 
                {
                    Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                }
                btnCyan2.setOnAction(new EventHandler<ActionEvent>() 
                {
                    @Override
                    public void handle(ActionEvent event) 
                    {
                        try 
                        {
                            FileInputStream mt2 = new FileInputStream("src/tronv2/img/cyan_moto.png");
                            Image imgMt2 = new Image(mt2);
                            moto2V.setImage(imgMt2);
                            moto2.setImage(imgMt2);
                            colorP2=CYAN;
                        } 
                        catch (FileNotFoundException ex) 
                        {
                            Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            
                });
                  
                Button btnPurple2 = new Button();
                btnPurple2.setLayoutX(520);
                btnPurple2.setLayoutY(300);
                btnPurple2.setMinSize(40, 40);
                btnPurple2.setStyle("-fx-background-color: transparent;");
                try 
                {
                    FileInputStream clr = new FileInputStream("src/tronv2/img/Chx_Pur.png");
                    Image imgClr = new Image(clr);
                    ImageView color = new ImageView(imgClr);
                    color.setLayoutX(520);
                    color.setLayoutY(300);
                    root.getChildren().add(color);
                } 
                catch (FileNotFoundException ex) 
                {
                    Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                }
                btnPurple2.setOnAction(new EventHandler<ActionEvent>() 
                {
                    @Override
                    public void handle(ActionEvent event) 
                    {
                        try 
                        {
                            FileInputStream mt2 = new FileInputStream("src/tronv2/img/purple_moto.png");
                            Image imgMt2 = new Image(mt2);
                            moto2V.setImage(imgMt2);
                            moto2.setImage(imgMt2);
                            colorP2=PURPLE;
                        } 
                        catch (FileNotFoundException ex) 
                        {
                            Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            
                });
                
                Button btnPink2 = new Button();
                btnPink2.setLayoutX(600);
                btnPink2.setLayoutY(300);
                btnPink2.setMinSize(40, 40);
                btnPink2.setStyle("-fx-background-color: transparent;");
                try 
                {
                    FileInputStream clr = new FileInputStream("src/tronv2/img/Chx_P.png");
                    Image imgClr = new Image(clr);
                    ImageView color = new ImageView(imgClr);
                    color.setLayoutX(600);
                    color.setLayoutY(300);
                    root.getChildren().add(color);
                } 
                catch (FileNotFoundException ex) 
                {
                    Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                }
                btnPink2.setOnAction(new EventHandler<ActionEvent>() 
                {
                    @Override
                    public void handle(ActionEvent event) 
                    {
                        try 
                        {
                            FileInputStream mt2 = new FileInputStream("src/tronv2/img/pink_moto.png");
                            Image imgMt2 = new Image(mt2);
                            moto2V.setImage(imgMt2);
                            moto2.setImage(imgMt2);
                            colorP2=PINK;
                        } 
                        catch (FileNotFoundException ex) 
                        {
                            Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            
                });
                Button btnGreen2 = new Button();
                btnGreen2.setLayoutX(680);
                btnGreen2.setLayoutY(300);
                btnGreen2.setMinSize(40, 40);
                btnGreen2.setStyle("-fx-background-color: transparent;");
                try 
                {
                    FileInputStream clr = new FileInputStream("src/tronv2/img/Chx_V.png");
                    Image imgClr = new Image(clr);
                    ImageView color = new ImageView(imgClr);
                    color.setLayoutX(680);
                    color.setLayoutY(300);
                    root.getChildren().add(color);
                } 
                catch (FileNotFoundException ex) 
                {
                    Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                }
                btnGreen2.setOnAction(new EventHandler<ActionEvent>() 
                {
                    @Override
                    public void handle(ActionEvent event) 
                    {
                        try 
                        {
                            FileInputStream mt2 = new FileInputStream("src/tronv2/img/green_moto.png");
                            Image imgMt2 = new Image(mt2);
                            moto2V.setImage(imgMt2);
                            moto2.setImage(imgMt2);
                            colorP2=GREEN;
                        } 
                        catch (FileNotFoundException ex) 
                        {
                            Logger.getLogger(TronV2.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            
                });
                
                
                Button dif0 = new Button();
                dif0.setLayoutX(300);
                dif0.setLayoutY(415);
                dif0.setOnAction(new EventHandler<ActionEvent>()
                        {
                            @Override
                            public void handle(ActionEvent event) 
                            {
                                
                            }                       
                        });
                dif0.setMinSize(30,30);
                Button dif1 = new Button();
                dif1.setLayoutX(300);
                dif1.setLayoutY(460);
                dif1.setMinSize(30,30);
                Button dif2 = new Button();
                dif2.setLayoutX(300);
                dif2.setLayoutY(510);
                dif2.setMinSize(30,30);
                Button dif3 = new Button();
                dif3.setLayoutX(300);
                dif3.setLayoutY(555);
                dif3.setMinSize(30,30);
                
                
                
                root.getChildren().add(btnBlue1);
                root.getChildren().add(btnRed1);
                root.getChildren().add(btnYellow1);
                root.getChildren().add(btnCyan1);
                root.getChildren().add(btnPurple1);
                root.getChildren().add(btnPink1);
                root.getChildren().add(btnGreen1);
                root.getChildren().add(btnBlue2);
                root.getChildren().add(btnRed2);
                root.getChildren().add(btnYellow2);
                root.getChildren().add(btnCyan2);
                root.getChildren().add(btnPurple2);
                root.getChildren().add(btnPink2);
                root.getChildren().add(btnGreen2);
                root.getChildren().add(btnPlay);
                root.getChildren().add(dif0);
                root.getChildren().add(dif1);
                root.getChildren().add(dif2);
                root.getChildren().add(dif3);
                
                
                
                
                
                
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
                    
                   
                    moto1.setLayoutX(0);
                    moto1.setLayoutY(0);
                    moto1.setFitHeight(32);
                    moto1.setFitWidth(32);

                    
                    moto2.setLayoutX(0);
                    moto2.setLayoutY(0);
                    moto2.setFitHeight(32);
                    moto2.setFitWidth(32);
                    
                    
                    
                    root.getChildren().remove(moto1V);
                    root.getChildren().remove(moto2V);
                    root.getChildren().add(moto1);
                    root.getChildren().add(moto2);
                    moto1.rotateProperty().setValue(0);
                    moto2.rotateProperty().setValue(180);
                    colorChosen = true;
                    
                
                
                    Button btnMenu = new Button();
                    btnMenu.setText("Quit");

                    btnMenu.toFront();
          
                
                    btnMenu.setOnAction(new EventHandler<ActionEvent>() 
                    {
                        @Override
                        public void handle(ActionEvent event) 
                        {
                            
                            primaryStage.setScene(menu);
                            primaryStage.show();
                            itsTimer.cancel();
                            redTab.clear();
                            blueTab.clear();
                                
                            moto1.translateXProperty().set(0);
                            moto1.translateYProperty().set(0);
                            moto2.translateXProperty().set(0);
                            moto2.translateYProperty().set(0);
                            moto1.rotateProperty().setValue(0);
                            moto2.rotateProperty().setValue(180);
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
                                if(colorChosen){
                                   blueLine.setStroke(colorP1); 
                                }
                                else{
                                    
                                }
                                blueLine.setStrokeWidth(5);

                                Wall blueWall = new Wall (blueLine);
                                root.getChildren().add(blueLine);
                                blueWall.setPosX((int) player1.getPosX());
                                blueWall.setPosY((int) player1.getPosY());
                            
                                Line redLine = new Line(player2.getPosX(), player2.getPosY(), player2.getPosX(), player2.getPosY()); 
                                if(colorChosen){
                                   redLine.setStroke(colorP2); 
                                }
                                else{
                                    
                                }
                                redLine.setStrokeWidth(5);

                                Wall redWall = new Wall (redLine);
                                root.getChildren().add(redLine);
                                redWall.setPosX((int) player2.getPosX());
                                redWall.setPosY((int) player2.getPosY());
                            
                            
                                moto1.setX(player1.getPosX()-24);
                                moto1.setY(player1.getPosY()-15);
                                moto2.setX(player2.getPosX()-9);
                                moto2.setY(player2.getPosY()-18);
                            
                            
                                blueTab.add(blueWall);
                                redTab.add(redWall);
                            
                                if(collision.collisions(player1, player2, redWall,blueWall,blueTab,redTab))
                                {
                                    itsTimer.cancel();
                                    blueTab.clear();
                                    redTab.clear();
                                    moto2.toBack();
                                    moto1.toBack();

                                    moto1.translateXProperty().set(0);
                                    moto1.translateYProperty().set(0);
                                    moto2.translateXProperty().set(0);
                                    moto2.translateYProperty().set(0);
                                    moto1.rotateProperty().setValue(0);
                                    moto2.rotateProperty().setValue(180);

                                }
                                moto2.toFront();
                                moto1.toFront();
                            
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
                                moto1.rotateProperty().setValue(0);
                                moto1.translateXProperty().set(0);
                                moto1.translateYProperty().set(0);
                            }          
                        }
                        if(keyCode.equals(KeyCode.S))
                        {
                            player1.moveDown();
                        
                            if(!player1.getMoveUp())
                            {
                                moto1.rotateProperty().setValue(90);
                                moto1.translateXProperty().set(7);
                                moto1.translateYProperty().set(-7);
                            }
                        }
                        if(keyCode.equals(KeyCode.Q))
                        {
                            player1.moveLeft();
                        
                            if(!player1.getMoveRight())
                            {
                                moto1.rotateProperty().setValue(180);
                                moto1.translateXProperty().set(17);
                                moto1.translateYProperty().set(-4);
                            }
                        }
                        if(keyCode.equals(KeyCode.Z))
                        {
                            player1.moveUp();
                        
                            if(!player1.getMoveDown())
                            {
                                moto1.rotateProperty().setValue(-90);
                                moto1.translateXProperty().set(10);
                                moto1.translateYProperty().set(6);
                            }
                        }
                    
                    
                    
                        if(keyCode.equals(KeyCode.RIGHT))
                        {
                            player2.moveRight();
                        
                            if(!player2.getMoveLeft())
                            {
                                moto2.rotateProperty().setValue(0);
                                moto2.translateXProperty().set(-15);
                                moto2.translateYProperty().set(3);
                            }
                        }
                        if(keyCode.equals(KeyCode.DOWN))
                        {
                            player2.moveDown();
                        
                            if(!player2.getMoveUp())
                            {
                                moto2.rotateProperty().setValue(90);
                                moto2.translateXProperty().set(-8);
                                moto2.translateYProperty().set(-7);
                            }
                        }
                        if(keyCode.equals(KeyCode.LEFT))
                        {
                            player2.moveLeft();
                        
                            if(!player2.getMoveRight())
                            {
                                moto2.rotateProperty().setValue(180);
                                moto2.translateXProperty().set(0);
                                moto2.translateYProperty().set(0);
                            }
                        }
                        if(keyCode.equals(KeyCode.UP))
                        {
                            player2.moveUp();
                        
                            if(!player2.getMoveDown())
                            {
                                moto2.rotateProperty().setValue(-90);
                                moto2.translateXProperty().set(-5);
                                moto2.translateYProperty().set(8);
                            }
                        }
                    });
                }
                });
                
                primaryStage.show();
                
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
