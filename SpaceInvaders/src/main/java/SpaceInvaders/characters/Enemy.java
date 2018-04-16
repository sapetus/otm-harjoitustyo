package SpaceInvaders.characters;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Enemy {

    private double movementSpeedHorizontal = 0.34;

    Rectangle hitBox;
    Enemy[] enemyArray;

    int width;
    int height;
    int arcWidth = 5;
    int arcHeight = 5;

    public Enemy(int width, int height) {
        this.hitBox = new Rectangle();

        this.hitBox.setWidth(width);
        this.hitBox.setHeight(height);
        this.hitBox.setArcHeight(arcHeight);
        this.hitBox.setArcWidth(arcWidth);
        this.hitBox.setFill(Paint.valueOf("#ffffff"));
    }

    public double getWidth() {
        return this.hitBox.getWidth();
    }

    public double getHeight() {
        return this.hitBox.getHeight();
    }

    public Rectangle getRectangle() {
        return this.hitBox;
    }

    public void StartEnemyMovement(Enemy array[]) {
        enemyArray = array;

        EnemyMovementVertical(enemyArray);
    }

    public void EnemyMovementVertical(Enemy array[]) {
        enemyArray = array;

        for (Enemy enemy : enemyArray) {
            enemy.getRectangle().setTranslateX(enemy.getRectangle().getTranslateX() + movementSpeedHorizontal);

            if (enemy.getRectangle().getTranslateX() >= 590 || enemy.getRectangle().getTranslateX() <= 18) {
                MoveEnemyDown(enemyArray);
                movementSpeedHorizontal = -movementSpeedHorizontal;
            }
        }
    }

    public void MoveEnemyDown(Enemy array[]) {
        enemyArray = array;
        boolean isAboveLine = true;

        for (Enemy enemy : array) {
            if (enemy.getRectangle().getTranslateY() >= 420) {
                isAboveLine = false;
            }
        }

        if (isAboveLine == true) {
            for (Enemy enemy2 : array) {
                enemy2.getRectangle().setTranslateY(enemy2.getRectangle().getTranslateY() + 34);
            }
        }
    }

}
