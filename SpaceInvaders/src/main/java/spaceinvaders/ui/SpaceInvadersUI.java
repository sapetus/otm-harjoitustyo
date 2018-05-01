package spaceinvaders.ui;

import spaceinvaders.domain.HighScoresDao;
import spaceinvaders.characters.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
    private final int WIDTH = HEIGHT;
    private final double distanceFromTop = WIDTH * 0.15;
    private final double distanceFromSide = WIDTH * 0.16;
    private final double distanceFromEachOtherHorizontal = WIDTH * 0.0625;
    private final double distanceFromEachOtherVertical = WIDTH * 0.053125;
    private final double enemyWidth = WIDTH * 0.04375;
    private final double enemyHeight = WIDTH * 0.0375;
    private Pane gamePlatform;
    private long time;
    private int points = 0;
    private Stage primaryStage;
    private StackPane menu = new StackPane();
    private Scene menuScene = new Scene(menu);
    private StackPane highScores = new StackPane();
    private Scene highScoreScene = new Scene(highScores);

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
    public void start(Stage primaryStage) throws Exception {
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
        pointText.setFont(new Font(WIDTH * 0.0234375));
        pointText.setTranslateX(WIDTH * 0.0234375);
        pointText.setTranslateY(WIDTH * 0.034375);
        gamePlatform.getChildren().add(pointText);

        Scene gameScene = new Scene(gamePlatform);
        gameScene.setOnKeyPressed(event -> {
            keys.put(event.getCode(), Boolean.TRUE);
        });
        gameScene.setOnKeyReleased(event -> {
            keys.put(event.getCode(), Boolean.FALSE);
        });
        //Game end

        //HighScores start
        HighScoresDao dao = new HighScoresDao();

        Text nameText = new Text("NAME\n\n" + dao.getNamesAsString());
        nameText.setFill(Paint.valueOf("#ffffff"));
        nameText.setFont(new Font(20));
        nameText.setTranslateX(-80);

        Text pointText = new Text("POINTS\n\n" + dao.getPointsAsString());
        pointText.setFill(Paint.valueOf("#ffffff"));
        pointText.setFont(new Font(20));
        pointText.setTranslateX(40);

        Text returnToMenu = new Text("Menu");
        returnToMenu.setFill(Paint.valueOf("#ffffff"));
        returnToMenu.setFont(new Font(25));
        returnToMenu.setTranslateY(-200);
        returnToMenu.setOnMouseClicked((MouseEvent mouseEvent) -> {
            this.primaryStage.setScene(menuScene);
        });
        returnToMenu.setOnMouseEntered((MouseEvent mouseEvent) -> {
            returnToMenu.setFont(new Font(35));
        });
        returnToMenu.setOnMouseExited((MouseEvent mouseEvent) -> {
            returnToMenu.setFont(new Font(25));
        });

        highScores.setPrefSize(WIDTH, HEIGHT);
        highScores.setAlignment(Pos.CENTER);
        highScores.setBackground(new Background(
                new BackgroundFill(
                        Paint.valueOf("#000000"),
                        CornerRadii.EMPTY,
                        Insets.EMPTY)
        ));

        highScores.getChildren().addAll(nameText, pointText, returnToMenu);
        //HighScores end

        //Menu start
        menu.setPrefSize(WIDTH, HEIGHT);
        menu.setBackground(new Background(
                new BackgroundFill(
                        Paint.valueOf("#000000"),
                        CornerRadii.EMPTY,
                        Insets.EMPTY)
        ));

        Text startText = new Text("Start Game");
        Text highScore = new Text("High Scores");

        highScore.setFill(Paint.valueOf("#ffffff"));
        highScore.setFont(new Font(WIDTH * 0.04));
        highScore.setTextAlignment(TextAlignment.CENTER);
        highScore.setTranslateY(60);
        highScore.setOnMouseEntered((MouseEvent mouseEvent) -> {
            highScore.setFont(new Font(WIDTH * 0.052));
        });
        highScore.setOnMouseExited((MouseEvent mouseEvent) -> {
            highScore.setFont(new Font(WIDTH * 0.04));
        });
        highScore.setOnMouseClicked((MouseEvent mouseEvent) -> {
            this.primaryStage.setScene(highScoreScene);
        });

        startText.setFill(Paint.valueOf("#ffffff"));
        startText.setFont(new Font(WIDTH * 0.052));
        startText.setTextAlignment(TextAlignment.CENTER);
        startText.setOnMouseEntered((MouseEvent mouseEvent) -> {
            startText.setFont(new Font(WIDTH * 0.065));
        });
        startText.setOnMouseExited((MouseEvent mouseEvent) -> {
            startText.setFont(new Font(WIDTH * 0.052));
        });
        startText.setOnMouseClicked((MouseEvent mouseEvent) -> {
            this.primaryStage.setScene(gameScene);
            gameAnimation.start();
        });
        menu.getChildren().addAll(startText, highScore);
        //Menu end

        this.primaryStage.setScene(menuScene);
        this.primaryStage.show();
    }

    AnimationTimer gameAnimation = new AnimationTimer() {
        @Override
        public void handle(long l) {
            basicEnemy1.startEnemyMovement(enemyArray);

            if (keys.getOrDefault(KeyCode.LEFT, false) && player.getBody().getTranslateX() > WIDTH * 0.039) {
                player.movePlayerLeft();
            }
            if (keys.getOrDefault(KeyCode.RIGHT, false) && player.getBody().getTranslateX() < WIDTH * 0.9171875) {
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
                gameOverText.setFont(new Font(WIDTH * 0.046875));
                gameOverScreen.getChildren().add(gameOverText);

                Scene gameOverScene = new Scene(gameOverScreen);

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
        gamePlatform.getChildren().get(55).setTranslateY(WIDTH * 0.9375);

        gamePlatform.getChildren().get(56).setTranslateX(WIDTH / 2 + 12);
        gamePlatform.getChildren().get(56).setTranslateY(WIDTH * 0.9265625);
    }
}
