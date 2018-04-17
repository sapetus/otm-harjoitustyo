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
    
    
}
