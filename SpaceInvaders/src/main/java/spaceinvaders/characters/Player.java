package spaceinvaders.characters;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

public class Player {

    Polygon[] player = new Polygon[2];

    Polygon body;
    Polygon cannon;

    double movementSpeed = 3;

    /**
     * Creates a new player with a cannon and a body hitbox.
     * gives them color
     */
    public Player() {
        this.body = new Polygon();
        this.cannon = new Polygon();

        body.getPoints().addAll(new Double[]{
            0.0, 0.0,
            28.0, 0.0,
            28.0, 14.0,
            0.0, 14.0
        });
        body.setFill(Paint.valueOf("#ffffff"));

        cannon.getPoints().addAll(new Double[]{
            0.0, 0.0,
            4.0, 0.0,
            4.0, 12.0,
            0.0, 12.0
        });
        cannon.setFill(Paint.valueOf("#ffffff"));

        player[0] = body;
        player[1] = cannon;
    }

    /**
     * @return returns players body rectangle
     */
    public Polygon getBody() {
        return this.body;
    }

    /**
     * @return returns players cannon rectangle
     */
    public Polygon getCannon() {
        return this.cannon;
    }

    /**
     * Moves the player (cannon and body) to the right
     */
    public void movePlayerRight() {
        this.body.setTranslateX(this.body.getTranslateX() + movementSpeed);
        this.cannon.setTranslateX(this.cannon.getTranslateX() + movementSpeed);
    }

    /**
     * Moves the player (cannon and body) to the left
     */
    public void movePlayerLeft() {
        this.body.setTranslateX(this.body.getTranslateX() - movementSpeed);
        this.cannon.setTranslateX(this.cannon.getTranslateX() - movementSpeed);
    }

    /**
     * Creates a new ammo at the player position and starts moving it.
     * 
     * @see spaceinvaders.characters.Ammo
     * @see spaceinvaders.characters.Ammo#moveAmmo() 
     * 
     * @return a new ammo
     */
    public Ammo shoot() {
        Ammo ammo = new Ammo();

        ammo.getAmmo().setTranslateX(this.cannon.getTranslateX() + 1);
        ammo.getAmmo().setTranslateY(this.cannon.getTranslateY());
        ammo.moveAmmo();

        return ammo;
    }
}
