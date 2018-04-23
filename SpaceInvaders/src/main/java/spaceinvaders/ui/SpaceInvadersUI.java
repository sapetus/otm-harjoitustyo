package spaceinvaders.ui;

import spaceinvaders.characters.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.*;
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

        //Game
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

        Scene gameScene = new Scene(gamePlatform);
        gameScene.setOnKeyPressed(event -> {
            keys.put(event.getCode(), Boolean.TRUE);
        });
        gameScene.setOnKeyReleased(event -> {
            keys.put(event.getCode(), Boolean.FALSE);
        });
        //Game
        
        //Menu
        StackPane menu = new StackPane();
        menu.setPrefSize(WIDTH, HEIGHT);
        menu.setBackground(new Background(
                new BackgroundFill(
                        Paint.valueOf("#000000"),
                        CornerRadii.EMPTY,
                        Insets.EMPTY)
        ));
        
        Text startText = new Text("Start Game");
        
        startText.setFill(Paint.valueOf("#ffffff"));
        startText.setFont(new Font(30));
        startText.setTextAlignment(TextAlignment.CENTER);
        startText.setOnMouseEntered((MouseEvent me) -> {
            startText.setFont(new Font(40));
        });
        startText.setOnMouseExited((MouseEvent me) -> {
            startText.setFont(new Font(30));
        });
        startText.setOnMouseClicked((MouseEvent me) -> {
            primaryStage.setScene(gameScene);
        });
        menu.getChildren().add(startText);
        //Menu
        
        Scene menuScene = new Scene(menu);

        primaryStage.setScene(menuScene);
        primaryStage.show();
        gameAnimation.start();
    }
    
    AnimationTimer gameAnimation = new AnimationTimer() {
        @Override
        public void handle(long l) {
            basicEnemy1.StartEnemyMovement(enemyArray);

            if (keys.getOrDefault(KeyCode.LEFT, false) && player.getBody().getTranslateX() > 25) {
                player.MovePlayerLeft();
            }
            if (keys.getOrDefault(KeyCode.RIGHT, false) && player.getBody().getTranslateX() < 587) {
                player.MovePlayerRight();
            }
            if (keys.getOrDefault(KeyCode.SPACE, false) && System.currentTimeMillis() - time >= 950) {
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
            ammoList.stream()
                    .filter(ammo3 -> ammo3.getAmmo().getTranslateY() < -5)
                    .forEach(ammo3 -> gamePlatform.getChildren().remove(ammo3.getAmmo()));

            enemyArray.stream()
                    .filter(enemy -> enemy.getIsHit())
                    .forEach(enemy -> gamePlatform.getChildren().remove(enemy.getRectangle()));
            enemyArray.removeAll(enemyArray.stream()
                    .filter(enemy -> enemy.getIsHit())
                    .collect(Collectors.toList()));
            
            if (enemyArray.isEmpty()) {
                gameAnimation.stop();
            }
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
