package spaceinvaders.characters;

import java.util.ArrayList;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Enemy {

    private double movementSpeedHorizontal = 0.33;

    Rectangle hitBox;
    ArrayList<Enemy> enemyList;

    int width;
    int height;
    int arcWidth = 5;
    int arcHeight = 5;
    boolean isHit;

    public Enemy(int width, int height) {
        this.hitBox = new Rectangle();
        
        this.hitBox.setWidth(width);
        this.hitBox.setHeight(height);
        this.hitBox.setArcHeight(arcHeight);
        this.hitBox.setArcWidth(arcWidth);
        this.hitBox.setFill(Paint.valueOf("#ffffff"));
        this.isHit = false;
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
    
    public boolean getIsHit() {
        return this.isHit;
    }
    
    public void setIsHit(boolean bool) {
        this.isHit = bool;
    }

    public void startEnemyMovement(ArrayList<Enemy> enemyList) {
        this.enemyList = enemyList;
        enemyMovementVertical(enemyList);
    }

    public void enemyMovementVertical(ArrayList<Enemy> enemyList) {
        this.enemyList = enemyList;
        for (Enemy enemy : enemyList) {
            enemy.getRectangle().setTranslateX(enemy.getRectangle().getTranslateX() + movementSpeedHorizontal);

            if (enemy.getRectangle().getTranslateX() >= 590 || enemy.getRectangle().getTranslateX() <= 18) {
                moveEnemyDown(enemyList);
                movementSpeedHorizontal = -movementSpeedHorizontal;
            }
        }
    }

    public void moveEnemyDown(ArrayList<Enemy> enemyList) {
        this.enemyList = enemyList;
        boolean isAboveLine = true;

        for (Enemy enemy : enemyList) {
            if (enemy.getRectangle().getTranslateY() >= 420) {
                isAboveLine = false;
            }
        }
        if (isAboveLine == true) {
            for (Enemy enemy2 : enemyList) {
                enemy2.getRectangle().setTranslateY(enemy2.getRectangle().getTranslateY() + 34);
            }
        }
    }
    
    public boolean collision(Ammo ammo) {
        Shape collision = Shape.intersect(this.hitBox, ammo.getAmmo());
        return collision.getBoundsInLocal().getWidth() != -1;
    }

}
