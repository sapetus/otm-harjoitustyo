package spaceinvaders.characters;

import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Ammo {

    Rectangle ammo;

    double movementSpeed = 6;
    boolean isHit;
    
    Point2D movement;

    /**
     * Creates an ammo with a hitbox and a color
     */
    public Ammo() {
        ammo = new Rectangle(2, 6);
        ammo.setFill(Paint.valueOf("#ffffff"));
        this.isHit = false;
    }

    /**
     * @return hitbox of the given ammo
     */
    public Rectangle getAmmo() {
        return this.ammo;
    }

    /**
     * @return returns boolean of ammos isHit value
     */
    public boolean getIsHit() {
        return this.isHit;
    }
    
    /**
     * sets ammos isHit value to given boolean
     * 
     * @param bool boolean value 
     */
    public void setIsHit(boolean bool) {
        this.isHit = bool;
    }
    
    /**
     *  moves the specific ammo up
     */
    public void moveAmmo() {
        movement = new Point2D(0, movementSpeed);
        ammo.setTranslateY(ammo.getTranslateY() - movement.getY());
    }
    
}
