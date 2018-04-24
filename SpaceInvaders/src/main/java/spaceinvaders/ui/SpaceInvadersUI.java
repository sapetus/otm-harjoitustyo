package spaceinvaders.ui;

import spaceinvaders.characters.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private Stage primaryStage;
    private StackPane menu = new StackPane();
    private Scene menuScene = new Scene(menu);
    
    ArrayList<Enemy> enemyArray = new ArrayList<>();
    Enemy basicEnemy1;
    Player player;
    ArrayList<Ammo> ammoList = new ArrayList<>();
    HashMap<KeyCode, Boolean> keys = new HashMap<>();
    Text pointText = new Text("points: " + points);
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Space Invaders");

        //Game start
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
        pointText.setFont(new Font(15));
        pointText.setTranslateX(15);
        pointText.setTranslateY(22);
        gamePlatform.getChildren().add(pointText);
        
        Scene gameScene = new Scene(gamePlatform);
        gameScene.setOnKeyPressed(event -> {
            keys.put(event.getCode(), Boolean.TRUE);
        });
        gameScene.setOnKeyReleased(event -> {
            keys.put(event.getCode(), Boolean.FALSE);
        });
        //Game end

        //Menu start
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
        startText.setOnMouseEntered((MouseEvent mouseEvent) -> {
            startText.setFont(new Font(40));
        });
        startText.setOnMouseExited((MouseEvent mouseEvent) -> {
            startText.setFont(new Font(30));
        });
        startText.setOnMouseClicked((MouseEvent mouseEvent) -> {
            this.primaryStage.setScene(gameScene);
        });
        menu.getChildren().add(startText);
        //Menu end
        
        this.primaryStage.setScene(menuScene);
        this.primaryStage.show();
        gameAnimation.start();
    }
    
    AnimationTimer gameAnimation = new AnimationTimer() {
        @Override
        public void handle(long l) {
            basicEnemy1.startEnemyMovement(enemyArray);
            
            if (keys.getOrDefault(KeyCode.LEFT, false) && player.getBody().getTranslateX() > 25) {
                player.movePlayerLeft();
            }
            if (keys.getOrDefault(KeyCode.RIGHT, false) && player.getBody().getTranslateX() < 587) {
                player.movePlayerRight();
            }
            if (keys.getOrDefault(KeyCode.SPACE, false) && System.currentTimeMillis() - time >= 900) {
                Ammo ammo = player.shoot();
                ammoList.add(ammo);
                gamePlatform.getChildren().add(ammo.getAmmo());
                time = System.currentTimeMillis();
                if (points > 0) {
                    points -= 1;
                    pointText.setText("points: " + points);
                }
            }
            
            ammoList.forEach((ammo) -> {
                ammo.moveAmmo();
                
                enemyArray.forEach((enemy) -> {
                    if (enemy.collision(ammo)) {
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
                StackPane gameOverScreen = new StackPane();
                gameOverScreen.setPrefSize(WIDTH, HEIGHT);
                gameOverScreen.setBackground(new Background(
                        new BackgroundFill(
                                Paint.valueOf("#000000"),
                                CornerRadii.EMPTY,
                                Insets.EMPTY)
                ));
                
                Text gameOverText = new Text("Game Over\n\n Your Points: " + points);
                gameOverText.setFill(Paint.valueOf("#ffffff"));
                gameOverText.setTextAlignment(TextAlignment.CENTER);
                gameOverText.setFont(new Font(30));
                gameOverScreen.getChildren().add(gameOverText);
                
                Scene gameOverScene = new Scene(gameOverScreen);
//                gameOverScene.setOnKeyPressed((KeyEvent keyEvent) -> {
//                    if (KeyCode.ENTER.equals(keyEvent.getCode())) {
//                        
//                    }
//                });
                
                primaryStage.setScene(gameOverScene);
                
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
