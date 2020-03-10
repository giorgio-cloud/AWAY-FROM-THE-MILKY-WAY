/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AwayFromTheMilkyWay.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

/**
 *
 * @author giorg
 */
public class GamePane extends AnchorPane {//sarà la schermata di gioco che  dovràconvivere nella stessa scena con la playerDataPane.
    
    private Circle spaceship;  
    private Circle planet1;  
    private Circle planet2;  
    
    
    public GamePane(){//avevo intenzione di mettere qui la dipendenza dal livello così da cambiare lo scenario in base al livello automaticamente
        super();
       
    }
        
    
    public void setScenario(int levelNumber) {
        Image image = new Image("AwayFromTheMilkyWay/media/images/spazio2.jpg");
        ImageView imageView = new ImageView(image);
        this.getChildren().add(imageView);
       // this.setBackground(new Background(new BackgroundFill(RED,CornerRadii.EMPTY,Insets.EMPTY)));
        
        switch(levelNumber){
            
            case 1 :  spaceship = new Circle(100,100,50);//x, y , raggio   
                      planet1 = new Circle(1000,500,60);
                      planet2 = new Circle(600,600,100);
                      spaceship.setFill(javafx.scene.paint.Color.RED);
                      planet1.setFill(javafx.scene.paint.Color.IVORY);
                      planet2.setFill(javafx.scene.paint.Color.OLIVE);
                      this.getChildren().addAll(spaceship,planet1,planet2);
                      
                
            case 2 :
                
            case 3 :
        
            case 4 :
        
            case 5 :
        
            case 6 :        
        
            case 7 :    
        
            case 8 :    
        }
        
        
    }
}
