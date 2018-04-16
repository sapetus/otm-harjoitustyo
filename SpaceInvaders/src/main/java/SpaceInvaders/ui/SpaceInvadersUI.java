package SpaceInvaders.ui;

import SpaceInvaders.characters.Enemy;
import SpaceInvaders.characters.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class SpaceInvadersUI extends Application {

    private final int HEIGHT = 640;
    private final int WIDTH = 640;
    private final int distanceFromTop = 96;
    private final int distanceFromSide = 103;
    private final int distanceFromEachOtherHorizontal = 40;
    private final int distanceFromEachOtherVertical = 34;
    private final int enemyWidth = 28;
    private final int enemyHeight = 24;

    private Pane gamePlatform;

    Enemy[] enemyArray = new Enemy[55];
    Enemy basicEnemy1;

    Player player;

    AnimationTimer animation = new AnimationTimer() {
        @Override
        public void handle(long l) {
            basicEnemy1.StartEnemyMovement(enemyArray);
        }
    };

    public static void main(String[] args) {
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

        FillBoardWithEnemies();
        CreatePlayer();

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
                gamePlatform.getChildren().get(enemyIndex)
                        .setTranslateX(distanceFromSide + distanceFromEachOtherHorizontal * i);
                gamePlatform.getChildren().get(enemyIndex)
                        .setTranslateY(distanceFromTop + distanceFromEachOtherVertical * j);
                enemyIndex++;
            }
        }
    }

    public void CreatePlayer() {
        this.player = new Player();

        gamePlatform.getChildren().addAll(this.player.getBody(), this.player.getCannon());

        gamePlatform.getChildren().get(55).setTranslateX(WIDTH / 2);
        gamePlatform.getChildren().get(55).setTranslateY(600);

        gamePlatform.getChildren().get(56).setTranslateX(WIDTH / 2 + 12);
        gamePlatform.getChildren().get(56).setTranslateY(592);
    }
}
