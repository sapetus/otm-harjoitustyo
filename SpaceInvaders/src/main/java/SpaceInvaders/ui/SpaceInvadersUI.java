
package SpaceInvaders.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SpaceInvadersUI extends Application{
    
    private final int HEIGHT = 640;
    private final int WIDTH = 640;
    private final int distanceFromTop = 96;
    private final int distanceFromSide = 84;
    private final int distanceFromEachOtherHorizontal = 44;
    private final int distanceFromEachOtherVertical = 34;
    private final int enemyWidth = 36;
    private final int enemyHeight = 24;
    
    Rectangle[] enemyArray = new Rectangle[55];
    
    public static void main(String [] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Space Invaders");
        
        Pane gamePlatform = new Pane();
        gamePlatform.setPrefSize(WIDTH, HEIGHT);
        gamePlatform.setBackground(new Background(
                new BackgroundFill(
                        Paint.valueOf("#000000"),
                        CornerRadii.EMPTY,
                        Insets.EMPTY)
        )); 
        
        int enemyIndex = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 5; j++) {
                enemyArray[enemyIndex] = EnemyMaker();
                
                gamePlatform.getChildren().add(enemyArray[enemyIndex]);
                gamePlatform.getChildren().get(enemyIndex)
                        .setTranslateX(distanceFromSide + distanceFromEachOtherHorizontal * i);
                gamePlatform.getChildren().get(enemyIndex)
                        .setTranslateY(distanceFromTop + distanceFromEachOtherVertical * j);
                enemyIndex++;
            }
        }
        
        Scene mainScene = new Scene(gamePlatform);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
    
    public Rectangle EnemyMaker() { //Creates enemies
        Rectangle enemy = new Rectangle();
        
        enemy.setWidth(enemyWidth);
        enemy.setHeight(enemyHeight);
        enemy.setArcWidth(5);
        enemy.setArcHeight(5);
        enemy.setFill(Paint.valueOf("#ffffff"));
        
        return enemy;
    }
}
