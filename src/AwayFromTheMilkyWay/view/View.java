/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AwayFromTheMilkyWay.view;

import AwayFromTheMilkyWay.controller.ControllerForView;
import AwayFromTheMilkyWay.utils.Resources;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;


/**
 *
 * @author giorg
 */
public class View implements IView{
    
    private Stage stage;
    private static View instance;
    private GameWindow game;
    private Scene scene;
    Stage pauseStage,alertStage;
    
    
    public View(){
        stage = new Stage();
    }
      
           
    public static IView getInstance() {
        if(instance == null)
            instance = new View();
        return instance;
    }
    
    public void closeWindow(){
        if(this.stage != null){
            
            this.stage.close();
            this.stage = null;
        }
    
    }
    
   
    //metodi per switchare le scene
    
    @Override
    public void changeCurrentWindow(String window) throws IOException{
        
        Parent root = FXMLLoader.load(getClass().getResource(window));
    
        scene = new Scene(root, 1280, 800);
    
        stage.setTitle("Away From the Milky Way");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setWidth(1280);
        stage.setHeight(835);
        stage.getIcons().add(Resources.GeneralImages.SPACEMANICON.getImage());
        stage.show();
        
        
    
    
    }
    
    @Override
    public void openGameWindow(int level){
        
        try {//il try catch va chiamato perche il metodo game.composegamewindow ha la clausola throws IOexeption
            game = new GameWindow();
            game.composeWindow(level);
        } catch (IOException ex) {
        Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
        scene = new Scene(game, 1280, 900);
        ControllerForView.getInstance().setDirection();
        scene.setOnKeyPressed((KeyEvent ke) -> {
           if(ke.getCode() == KeyCode.ESCAPE)
                    View.getInstance().pause();
            });
            
        stage.setTitle("Away From the Milky Way");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setWidth(1280);
        stage.setHeight(935);
        stage.getIcons().add(Resources.GeneralImages.SPACEMANICON.getImage());
        
        stage.show();
        handleExit();
        Resources.Music.SOUNDINTRO.stop();
        Resources.Music.SOUNDTRACK.play();
        
    
    }
    
    
    @Override
    public void pause(){
        
        ControllerForView.getInstance().pauseAnimations();
        pauseStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("opzioniPausa.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        scene = new Scene(root, 1280, 800);
        
        pauseStage.setScene(scene);
        pauseStage.setResizable(false);
        pauseStage.setTitle("Opzioni");
        pauseStage.initStyle(StageStyle.UNDECORATED);
        stage.hide();
        pauseStage.show();
        
    
    }
    
   
    //metodi per restituire oggetti relativi alla finestra
    
    @Override
    public GameWindow getGameWindow(){
        return this.game;
    }
    
    
    
    
    @Override
    public Stage getPauseStage(){
        return this.pauseStage;
    }
    
    
    @Override
    public Stage getMainStage(){
        return this.stage;
    }
    
    
    @Override
    public Scene getScene(){
        return this.scene;
    }
    
    @Override
    public GamePane getGamePane(){
        return this.game.getSchermataGioco();
    }
    
    
    @Override
    public PlayerDataPane getDataPane(){
        return this.game.getPDP();
    }
    
    
    @Override//sostituire questo metodo nella ControllerForView invece che quella struttura riondante usata sin ora
    public Circle getSpaceship(){
        return this.game.schermataGioco.getSpaceship();
                
    }
    
    @Override
    public Circle getMovingObstacle1(){
        return this.game.schermataGioco.getMO1();
    }
    @Override
    public Circle getMovingObstacle2(){
        return this.game.schermataGioco.getMO2();
    }
    
    
    @Override
    public Circle getMilkyWay(){
        return this.game.schermataGioco.getMilkyWay();
    }
    
    @Override
    public int getRimbDes(){
        return this.getDataPane().getRimbDes();
    }
    
    @Override 
    public String getNome(){
        return this.getDataPane().getNome();
    }
    
    @Override
    public Button getHelpButton(){
       return this.getGamePane().getHelpButton();
    }
    
    
    
    
    @Override
    public void explosion(){
        
        GamePane gm = this.game.schermataGioco;
        Timeline t = new Timeline();
        t.setCycleCount(1);
        
        t.getKeyFrames().add(new KeyFrame(Duration.millis(0),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP1.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(80),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP2.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(160),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP3.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(240),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP4.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(320),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP5.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(400),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP6.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(480),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP7.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(560),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP8.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(640),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP9.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(720),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP10.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(800),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP11.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(880),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP12.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(960),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP13.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(1040),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP14.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(1120),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP15.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(1200),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP16.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(1280),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP17.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(1360),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP18.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(1440),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP19.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(1520),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP20.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(1600),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP21.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(1680),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP22.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(1780),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP23.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(1860),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP24.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(1920),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP25.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(2000),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP26.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(2080),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP27.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(2160),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP28.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(2240),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP29.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(2320),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP30.getImage());})); 
        t.getKeyFrames().add(new KeyFrame(Duration.millis(2400),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP31.getImage());})); 
        t.getKeyFrames().add(new KeyFrame(Duration.millis(2480),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP32.getImage());})); 
        t.getKeyFrames().add(new KeyFrame(Duration.millis(2560),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP33.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(2640),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP34.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(2720),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP35.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(2800),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Explosion.EXP36.getImage());}));
        
        
        t.setOnFinished((ActionEvent e) -> {
            ControllerForView.getInstance().pauseAnimations();
            Resources.Music.SOUNDTRACK.stop();
            View.getInstance().createAlert("restartLevel.fxml");
            Resources.SoundEffects.DEFEAT.play();
        });
        
        Resources.SoundEffects.EXPLOSION.play();
        t.play();
 
        
    }
    
   
    @Override
    public void backwashWin(){
        GamePane gm = this.game.schermataGioco;
        Timeline t = new Timeline();
        t.setCycleCount(1);
        
        t.getKeyFrames().add(new KeyFrame(Duration.millis(0),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Backwash.BCK1.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(100),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Backwash.BCK2.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(200),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Backwash.BCK3.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(300),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Backwash.BCK4.getImage());}));
        
        t.setOnFinished((ActionEvent e) -> {
            ControllerForView.getInstance().pauseAnimations();
            Resources.Music.SOUNDTRACK.stop();
            View.getInstance().createAlert("levelComplete.fxml");
            Resources.SoundEffects.VICTORY.play();
        });

        t.play();
    }
    
    
    @Override
    public void backwashLose(){
        GamePane gm = this.game.schermataGioco;
        Timeline t = new Timeline();
        t.setCycleCount(1);
        
        t.getKeyFrames().add(new KeyFrame(Duration.millis(0),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Backwash.BCK1.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(100),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Backwash.BCK2.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(200),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Backwash.BCK3.getImage());}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(300),(ActionEvent event) ->{gm.setSpaceshipIm(Resources.Backwash.BCK4.getImage());}));
        
        t.setOnFinished((ActionEvent e) -> {
            ControllerForView.getInstance().pauseAnimations();
            Resources.Music.SOUNDTRACK.stop();
            View.getInstance().createAlert("restartLevel.fxml");
            Resources.SoundEffects.DEFEAT.play();
        });

        t.play();
    }
    
    
    
    @Override
    public void createAlert(String window){
        
        getDataPane().disableButton();
        alertStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(window));
        } catch (IOException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        scene = new Scene(root, 320, 240);
        
        alertStage.setScene(scene);
        alertStage.setResizable(false);
        alertStage.setTitle("Away From The Milky Way");
        alertStage.initStyle(StageStyle.UNDECORATED);
        //stage.hide();
        alertStage.show();
        
    }
    
    
    @Override
    public Stage getAlertStage(){
        return this.alertStage;
    }
    
    
    @Override
    public void handleExit(){//metodo che serve a chiudere tutte le ffinestre attive se quella principale viene chiusa
        EventHandler handler = (EventHandler<WindowEvent>) (WindowEvent event) -> {
            if(pauseStage != null)
                pauseStage.close();
            if(alertStage != null)
                alertStage.close();
        };
        
        stage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, handler);
    }
 
    
    
}//end class
