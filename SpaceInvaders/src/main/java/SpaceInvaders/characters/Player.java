package SpaceInvaders.characters;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

public class Player {

    Polygon[] player = new Polygon[2];

    Polygon body;
    Polygon cannon;

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

    public Polygon getBody() {
        return this.body;
    }

    public Polygon getCannon() {
        return this.cannon;
    }
}
