package Tests.charactersTest;

import SpaceInvaders.characters.Enemy;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class EnemyTest {
    
    Enemy enemy;
    
    @Before
    public void setUp() {
        enemy = new Enemy(50, 50);
    }

    @Test
    public void createsAnEnemyWithRightHeight() {
        int height = 50;
        assertEquals(height,(int) this.enemy.getHeight());
    }
    
    @Test
    public void createsAnEnemyWithRightWidth() {
        int width = 50;
        assertEquals(width,(int) this.enemy.getWidth());
    }
    
    @Test
    public void enemyMakerWidthIsEqualToEnemyWidth() {
        assertEquals((int)this.enemy.getWidth(), (int) enemy.getWidth());
    }
    
    @Test
    public void enemyMakerHeightIsEqualToEnemyHeight() {
        assertEquals((int)this.enemy.getHeight(), (int) enemy.getHeight());
    }
    
    @Test
    public void enemyMakerArcWidthIsFive() {
        assertEquals(5, (int) enemy.getRectangle().getArcWidth());
    }
    
    @Test
    public void enemyMakerArcHeightIsFive() {
        assertEquals(5, (int) enemy.getRectangle().getArcHeight());
    }
    
    @Test
    public void enemyMakerColorIsWhite() {
        assertEquals(Paint.valueOf("#ffffff"), enemy.getRectangle().getFill());
    }
}
