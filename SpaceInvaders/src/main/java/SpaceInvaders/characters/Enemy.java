
package SpaceInvaders.characters;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Enemy {
    
    int width;
    int height;
    
    public Enemy(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public Rectangle EnemyMaker() {
        Rectangle enemy = new Rectangle();
        
        enemy.setWidth(this.width);
        enemy.setHeight(this.height);
        enemy.setArcHeight(5);
        enemy.setArcWidth(5);
        enemy.setFill(Paint.valueOf("#ffffff"));
        
        return enemy;
    }
}
