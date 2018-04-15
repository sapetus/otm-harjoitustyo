package SpaceInvaders.ui;

import SpaceInvaders.characters.Enemy;
import javafx.animation.AnimationTimer;
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

public class SpaceInvadersUI extends Application {

    private final int HEIGHT = 640;
    private final int WIDTH = 640;
    private final int distanceFromTop = 96;
    private final int distanceFromSide = 103;
    private final int distanceFromEachOtherHorizontal = 40;
    private final int distanceFromEachOtherVertical = 34;
    private final int enemyWidth = 32;
    private final int enemyHeight = 24;
    private Pane gamePlatform;

    Enemy[] enemyArray = new Enemy[55];
    Enemy basicEnemy1;
    
    static Rectangle rightBound = new Rectangle();
    static Rectangle leftBound = new Rectangle();

    AnimationTimer animation = new AnimationTimer() {
        @Override
        public void handle(long l) {
            basicEnemy1.EnemyMovementRight(enemyArray);
        }
    };

    public static void main(String[] args) {
        rightBound.setWidth(80);
        rightBound.setHeight(640);
        
        leftBound.setWidth(80);
        leftBound.setHeight(640);
        
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Space Invaders");

        gamePlatform = new Pane();
        gamePlatform.setPrefSize(WIDTH, HEIGHT);
        gamePlatform.setBackground(new Background(
                new BackgroundFill(
                        Paint.valueOf("#000000"),
                        CornerRadii.EMPTY,
                        Insets.EMPTY)
        ));
        
        gamePlatform.getChildren().addAll(rightBound, leftBound);
        gamePlatform.getChildren().get(0).setTranslateX(560);
        gamePlatform.getChildren().get(1).setTranslateX(0);

        FillBoardWithEnemies();

        Scene mainScene = new Scene(gamePlatform);
        primaryStage.setScene(mainScene);
        primaryStage.show();
        animation.start();
    }

    public void FillBoardWithEnemies() {
        int enemyIndex = 0;

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 5; j++) {
                basicEnemy1 = new Enemy(enemyWidth, enemyHeight);
                enemyArray[enemyIndex] = basicEnemy1;

                gamePlatform.getChildren().add(enemyArray[enemyIndex].getRectangle());
                gamePlatform.getChildren().get(enemyIndex + 2)
                        .setTranslateX(distanceFromSide + distanceFromEachOtherHorizontal * i);
                gamePlatform.getChildren().get(enemyIndex + 2)
                        .setTranslateY(distanceFromTop + distanceFromEachOtherVertical * j);
                enemyIndex++;
            }
        }
    }
    
    public Rectangle getRightBound() {
        return rightBound;
    }
    
    public Rectangle getLeftBound() {
        return leftBound;
    }
    
    public Pane getGamePlatform() {
        return gamePlatform;
    }
}
