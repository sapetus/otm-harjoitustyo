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

    /**
    * Creates an enemy with a hitbox, a color and given size
    * 
    * @param width sets the width of the enemy
    * @param height sets the height of the enemy
    */
    public Enemy(double width, double height) {
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

    /**
     * Only starts moving the given list of enemies
     * 
     * @see spaceinvaders.characters.Enemy#enemyMovementVertical(java.util.ArrayList)
     * 
     * @param enemyList List of enemies that need to be moved
     */
    public void startEnemyMovement(ArrayList<Enemy> enemyList) {
        this.enemyList = enemyList;
        enemyMovementVertical(enemyList);
    }

    /**
     * Starts moving enemies to the right and if any enemy in the given lists 
     * hits a specific point, starts moving them to the left and calls another 
     * method that moves them down. 
     * 
     * @see spaceinvaders.characters.Enemy#moveEnemyDown(java.util.ArrayList) 
     * 
     * @param enemyList List of enemies that need to be moved
     */
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

    /**
     * Moves the given list of enemies down until one of them hits a point where
     * they are not supposed to move down anymore
     * 
     * @param enemyList List of enemies that need to be moved
     */
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
    
    /**
     * Checks if the enemy hit the given ammo
     * 
     * @see spaceinvaders.characters.Ammo
     * 
     * @param ammo Object that needs to be checked if it hit
     * @return a boolean about if an ammo hit an enemy
     */
    public boolean collision(Ammo ammo) {
        Shape collision = Shape.intersect(this.hitBox, ammo.getAmmo());
        return collision.getBoundsInLocal().getWidth() != -1;
    }

}
