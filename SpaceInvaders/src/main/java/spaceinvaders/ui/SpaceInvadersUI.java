package spaceinvaders.ui;

import spaceinvaders.characters.Ammo;
import spaceinvaders.characters.Enemy;
import spaceinvaders.characters.Player;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
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
    private long time;

    Enemy[] enemyArray = new Enemy[55];
    Enemy basicEnemy1;
    Player player;
    ArrayList<Ammo> ammoList = new ArrayList<>();
    HashMap<KeyCode, Boolean> keys = new HashMap<>();

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

        mainScene.setOnKeyPressed(event -> {
            keys.put(event.getCode(), Boolean.TRUE);
        });
        mainScene.setOnKeyReleased(event -> {
            keys.put(event.getCode(), Boolean.FALSE);
        });

        primaryStage.setScene(mainScene);
        primaryStage.show();
        animation.start();
    }

    AnimationTimer animation = new AnimationTimer() {
        @Override
        public void handle(long l) {
            basicEnemy1.StartEnemyMovement(enemyArray);

            if (keys.getOrDefault(KeyCode.LEFT, false) && player.getBody().getTranslateX() > 25) {
                player.MovePlayerLeft();
            }
            if (keys.getOrDefault(KeyCode.RIGHT, false) && player.getBody().getTranslateX() < 587) {
                player.MovePlayerRight();
            }
            if (keys.getOrDefault(KeyCode.SPACE, false) && System.currentTimeMillis() - time >= 1000) {
                Ammo ammo = player.Shoot();
                ammoList.add(ammo);
                gamePlatform.getChildren().add(ammo.getAmmo());
                time = System.currentTimeMillis();
            }

            ammoList.forEach((ammo) -> {
                ammo.MoveAmmo();
                ammoList.stream()
                        .filter(ammo2 -> ammo2.getAmmo().getTranslateY() < -5)
                        .forEach(ammus2 -> gamePlatform.getChildren().remove(ammus2.getAmmo()));
            });

        }
    };

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
        gamePlatform.getChildren().get(56).setTranslateY(593);
    }
}
