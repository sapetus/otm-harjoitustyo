package SpaceInvaders.characters;

import SpaceInvaders.ui.SpaceInvadersUI;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Enemy {

    private final double movementSpeedHorizontal = 0.3;

    Rectangle hitBox;
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

    public void EnemyMovementRight(Enemy array[]) {
        for (Enemy enemy : array) {
            enemy.getRectangle().setTranslateX(enemy.getRectangle().getTranslateX() + movementSpeedHorizontal);
        }
    }

    public void EnemyMovementLeft(Enemy array[]) {
        for (Enemy enemy : array) {
            enemy.getRectangle().setTranslateX(enemy.getRectangle().getTranslateX() - movementSpeedHorizontal);
        }
    }
}
