package Tests.charactersTest;

import javafx.geometry.Point2D;
import spaceinvaders.characters.Ammo;
import javafx.scene.paint.Paint;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AmmoTest {

    Ammo ammo;

    @Before
    public void setUp() {
        ammo = new Ammo();
    }

    @Test
    public void ammoColorIsRight() {
        assertEquals(Paint.valueOf("#ffffff"), ammo.getAmmo().getFill());
    }

    @Test
    public void ammoHeightIsRight() {
        assertEquals(6, (int) ammo.getAmmo().getHeight());
    }

    @Test
    public void ammoWidthIsRight() {
        assertEquals(2, (int) ammo.getAmmo().getWidth());
    }
    
    @Test
    public void ammoIsNotHitWhenCreated() {
        assertEquals(false, ammo.getIsHit());
    }
    
    @Test
    public void setIsHitWorksCorrectly() {
        ammo.setIsHit(true);
        assertEquals(true, ammo.getIsHit());
    }
    
    @Test
    public void moveAmmoWorksCorrectly() {
        ammo.moveAmmo();
        assertEquals(-6, (int)ammo.getAmmo().getTranslateY());
    }
}
