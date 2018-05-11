package Tests.charactersTest;

import java.util.ArrayList;
import spaceinvaders.characters.Enemy;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import spaceinvaders.characters.Ammo;

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
    
    @Test
    public void returnsEnemyRectangle() {
        assertEquals(Rectangle.class, enemy.getRectangle().getClass());
    }
    
    @Test
    public void enemySpawnsNotHit() {
        assertEquals(false, this.enemy.getIsHit());
    }
    
    @Test
    public void setIsHitWorksCorrectly() {
        this.enemy.setIsHit(true);
        assertEquals(true, this.enemy.getIsHit());
    }
 
    @Test
    public void collissionWorksCorrectly() {
        Ammo ammo = new Ammo();
        ammo.getAmmo().setTranslateX(this.enemy.getRectangle().getTranslateX());
        ammo.getAmmo().setTranslateY(this.enemy.getRectangle().getTranslateY());
        assertEquals(true, this.enemy.collision(ammo));
    }
    
    @Test
    public void enemyMovementDownWorksCorrectly() {
        ArrayList<Enemy> enemyArray = new ArrayList<>();
        enemyArray.add(new Enemy(50, 50));
        
        enemy.moveEnemyDown(enemyArray);
        assertEquals((int)34, (int)enemyArray.get(0).getRectangle().getTranslateY());
        
        enemyArray.get(0).getRectangle().setTranslateY(600);
        enemy.moveEnemyDown(enemyArray);
        assertEquals((int)600, (int)enemyArray.get(0).getRectangle().getTranslateY());
    }
    
    @Test
    public void enemyMovementVerticalWorksCorrectly() {
        ArrayList<Enemy> enemyArray = new ArrayList<>();
        enemyArray.add(new Enemy(50, 50));
        enemyArray.get(0).getRectangle().setTranslateX(20);
        
        for (int i = 0; i < 10; i++) {
            enemy.enemyMovementVertical(enemyArray);
        }
        assertEquals((int)23.3, (int)enemyArray.get(0).getRectangle().getTranslateX());
    }
}
