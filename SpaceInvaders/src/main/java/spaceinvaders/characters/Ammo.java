package spaceinvaders.characters;

import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Ammo {

    Rectangle ammo;

    double movementSpeed = 6;
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
    
    public void moveAmmo() {
        movement = new Point2D(0, movementSpeed);
        ammo.setTranslateY(ammo.getTranslateY() - movement.getY());
    }
    
}
