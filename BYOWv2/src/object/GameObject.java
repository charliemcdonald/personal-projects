package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameObject {

    public GamePanel gp;

    public int worldX, worldY;
    public int screenX, screenY;

    public BufferedImage image;

    public Rectangle hitBox;
    boolean collectable = true;
    boolean collected = false;

    public GameObject(GamePanel gp, int worldX, int worldY) {

        this.gp = gp;
        this.worldX = worldX;
        this.worldY = worldY;

        int screenX = worldY - gp.player.worldX + gp.player.screenX;
        int screenY = worldX - gp.player.worldY + gp.player.screenY;

        this.hitBox = new Rectangle(screenX + (gp.TILE_SIZE / 4), screenY + (gp.TILE_SIZE / 4), 8 * gp.SCALE, 8 * gp.SCALE);
    }

    public void draw(Graphics2D g2) {

        if (!collected) {

            g2.drawImage(image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
            drawHitBox(g2);
        }
    }

    public void update() {

        collectObject(); // check for collection

        // update screen position
        screenX = worldY - gp.player.worldX + gp.player.screenX;
        screenY = worldX - gp.player.worldY + gp.player.screenY;

        // update hitbox position
        hitBox.x = screenX;
        hitBox.y = screenY;
    }

    public void loadImage(String filePath) {

        try {
            image = ImageIO.read(getClass().getResourceAsStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void collectObject() {

        if (collectable) {
            if (gp.collisionHandler.collided(this.hitBox, gp.player.getHitBox())) {
                this.collected = true;
                this.hitBox = new Rectangle(0, 0);
            }
        }
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    // for debugging
    public void drawHitBox(Graphics2D g2) {

        g2.setColor(Color.PINK);
        g2.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }

}
