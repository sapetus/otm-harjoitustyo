package charactersTest;

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
        assertEquals(height, this.enemy.getHeight());
    }
    
    @Test
    public void createsAnEnemyWithRightWidth() {
        int width = 50;
        assertEquals(width, this.enemy.getWidth());
    }
    
    @Test
    public void enemyMakerWidthIsEqualToEnemyWidth() {
        Rectangle enemyRectangle = enemy.EnemyMaker();
        
        assertEquals(this.enemy.getWidth(), (int) enemyRectangle.getWidth());
    }
    
    @Test
    public void enemyMakerHeightIsEqualToEnemyHeight() {
        Rectangle enemyRectangle = enemy.EnemyMaker();
        
        assertEquals(this.enemy.getHeight(), (int) enemyRectangle.getHeight());
    }
    
    @Test
    public void enemyMakerArcWidthIsFive() {
        Rectangle enemyRectangle = enemy.EnemyMaker();
        
        assertEquals(5, (int) enemyRectangle.getArcWidth());
    }
    
    @Test
    public void enemyMakerArcHeightIsFive() {
        Rectangle enemyRectangle = enemy.EnemyMaker();
        
        assertEquals(5, (int) enemyRectangle.getArcHeight());
    }
    
    @Test
    public void enemyMakerColorIsWhite() {
        Rectangle enemyRectangle = enemy.EnemyMaker();
        
        assertEquals(Paint.valueOf("#ffffff"), enemyRectangle.getFill());
    }
}
