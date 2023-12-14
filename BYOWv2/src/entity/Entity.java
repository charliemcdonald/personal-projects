package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int worldX, worldY;
    public int speed;

    // for animation
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle hitBox;

    // for debugging
    public void drawHitBox(Graphics2D g2) {

        g2.setColor(Color.PINK);
        g2.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }

    public void updateHitBox() {
        hitBox.x = worldX * 16;
        hitBox.y = worldY * 16;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }
}
