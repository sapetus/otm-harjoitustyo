package spaceinvaders.characters;

import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Ammo {

    Rectangle ammo;

    double movementSpeed = 5;
    boolean isHit;
    
    Point2D movement;

    public Ammo() {
        ammo = new Rectangle(2, 6);
        ammo.setFill(Paint.valueOf("#ffffff"));
        this.isHit = false;
    }

    public Rectangle getAmmo() {
        return this.ammo;
    }

    public boolean getIsHit() {
        return this.isHit;
    }
    
    public void setIsHit(boolean bool) {
        this.isHit = bool;
    }
    
    public void MoveAmmo() {
        movement = new Point2D(0, movementSpeed);
        ammo.setTranslateY(ammo.getTranslateY() - movement.getY());
    }
    
    public boolean Collission(Enemy enemy) {
        Shape collision = Shape.intersect(this.ammo, enemy.getRectangle());
        return collision.getBoundsInLocal().getWidth() != -1;
    }
}
