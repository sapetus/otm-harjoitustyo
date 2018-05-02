package Tests.charactersTest;

import spaceinvaders.characters.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import spaceinvaders.characters.Ammo;

public class PlayerTest {

    Player player;

    @Before
    public void setUp() {
        player = new Player();
    }

    @Test
    public void returnsBodyPolygon() {
        assertEquals(Polygon.class, player.getBody().getClass());
    }

    @Test
    public void returnsCannonPolygon() {
        assertEquals(Polygon.class, player.getCannon().getClass());
    }

    @Test
    public void bodyIsRightColor() {
        assertEquals(Paint.valueOf("#ffffff"), player.getBody().getFill());
    }

    @Test
    public void cannonIsRightColor() {
        assertEquals(Paint.valueOf("#ffffff"), player.getCannon().getFill());
    }

    @Test
    public void bodyIsRightSize() {
        ObservableList<Double> pointList = FXCollections.observableArrayList();
        pointList.addAll(new Double[] {
            0.0, 0.0,
            28.0, 0.0,
            28.0, 14.0,
            0.0, 14.0
        });
        assertEquals(pointList, player.getBody().getPoints());
    }

    @Test
    public void cannonIsRightSize() {
        ObservableList<Double> pointList = FXCollections.observableArrayList();
        pointList.addAll(new Double[] {
            0.0, 0.0,
            4.0, 0.0,
            4.0, 12.0,
            0.0, 12.0
        });
        assertEquals(pointList, player.getCannon().getPoints());
    }
    
    @Test
    public void bodyMovesRightCorrectly() {
        this.player.movePlayerRight();
        assertEquals(3, (int) this.player.getBody().getTranslateX());
    }
    
    @Test
    public void bodyMovesLeftCorrectly() {
        this.player.movePlayerLeft();
        assertEquals(-3, (int) this.player.getBody().getTranslateX());
    }
    
    @Test
    public void cannonMovesRightCorrectly() {
        this.player.movePlayerRight();
        assertEquals(3, (int) this.player.getCannon().getTranslateX());
    }
    
    @Test
    public void cannonMovesLeftCorrectly() {
        this.player.movePlayerLeft();
        assertEquals(-3, (int) this.player.getCannon().getTranslateX());
    }
    
    @Test
    public void shootReturnsAnAmmo() {
        assertEquals(Ammo.class, this.player.shoot().getClass());
    }
    
    @Test
    public void generatedAmmoIsInRightPositionX() {
        assertEquals((int) this.player.getCannon().getTranslateX(), (int)this.player.shoot().getAmmo().getTranslateX() - 1);
    }
    
    @Test
    public void generatedAmmoIsInRightPositionY() {
        System.out.println(this.player.getCannon().getTranslateY());
        assertEquals((int) this.player.getCannon().getTranslateY(), (int)this.player.shoot().getAmmo().getTranslateY() + 6);
    }
}
