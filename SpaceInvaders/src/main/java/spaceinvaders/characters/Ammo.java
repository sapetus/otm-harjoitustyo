package spaceinvaders.characters;

import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Ammo {

    Rectangle ammo;

    double movementSpeed = 5;

    Point2D movement;

    public Ammo() {
        ammo = new Rectangle(2, 6);
        ammo.setFill(Paint.valueOf("#ffffff"));
    }

    public Rectangle getAmmo() {
        return this.ammo;
    }

    public void MoveAmmo() {
        movement = new Point2D(0, movementSpeed);
        ammo.setTranslateY(ammo.getTranslateY() - movement.getY());
    }
    
}
