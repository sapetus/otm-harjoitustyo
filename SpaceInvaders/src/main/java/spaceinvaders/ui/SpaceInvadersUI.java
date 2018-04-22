package spaceinvaders.ui;

import java.awt.Font;
import spaceinvaders.characters.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import spaceinvaders.characters.Ammo;
import spaceinvaders.characters.Enemy;

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
    private int points = 0;

    ArrayList<Enemy> enemyArray = new ArrayList<>();
    Enemy basicEnemy1;
    Player player;
    ArrayList<Ammo> ammoList = new ArrayList<>();
    HashMap<KeyCode, Boolean> keys = new HashMap<>();
    Text pointText = new Text("points: " + points);

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

        pointText.setFill(Paint.valueOf("#ffffff"));
        pointText.setTranslateX(15);
        pointText.setTranslateY(15);
        gamePlatform.getChildren().add(pointText);

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

                enemyArray.forEach((enemy) -> {
                    if (enemy.Collision(ammo)) {
                        ammo.setIsHit(true);
                        enemy.setIsHit(true);
                        points += 10;
                        pointText.setText("points: " + points);
                    }
                });
            });

            ammoList.stream()
                    .filter(ammo2 -> ammo2.getIsHit())
                    .forEach(ammo2 -> gamePlatform.getChildren().remove(ammo2.getAmmo()));
            ammoList.removeAll(ammoList.stream()
                    .filter(ammo2 -> ammo2.getIsHit())
                    .collect(Collectors.toList()));

            enemyArray.stream()
                    .filter(enemy -> enemy.getIsHit())
                    .forEach(enemy -> gamePlatform.getChildren().remove(enemy.getRectangle()));
            enemyArray.removeAll(enemyArray.stream()
                    .filter(enemy -> enemy.getIsHit())
                    .collect(Collectors.toList()));

            ammoList.stream()
                    .filter(ammo3 -> ammo3.getAmmo().getTranslateY() < -5)
                    .forEach(ammo3 -> gamePlatform.getChildren().remove(ammo3.getAmmo()));

        }
    };

    public void FillBoardWithEnemies() {
        int enemyIndex = 0;

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 5; j++) {
                basicEnemy1 = new Enemy(enemyWidth, enemyHeight);
                enemyArray.add(basicEnemy1);

                gamePlatform.getChildren().add(basicEnemy1.getRectangle());
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
