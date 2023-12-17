package entity;

import main.CollisionHandler;
import main.GamePanel;
import main.KeyHandler;
import world.Coordinate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    CollisionHandler cH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;
        this.cH = gp.collisionHandler;

        screenX = gp.SCREEN_WIDTH / 2 - (gp.TILE_SIZE / 2); // always in the center of the screen
        screenY = gp.SCREEN_HEIGHT / 2 - (gp.TILE_SIZE / 2);



        setDefault();
        getPlayerImage();


        hitBox = new Rectangle(screenX + (gp.TILE_SIZE / 4), screenY + (gp.TILE_SIZE / 4), 8 * gp.SCALE, 8 * gp.SCALE);
    }

    public void setDefault() {

        Coordinate spawnPoint = gp.world.getPlayerSpawnPoint();

        worldX = spawnPoint.getY() * gp.TILE_SIZE;
        worldY = spawnPoint.getX() * gp.TILE_SIZE;

        speed = gp.SCALE * 3;
        direction = "down";
    }

    public void getPlayerImage() {
        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/player/PLAYER_BACK1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/PLAYER_BACK2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/PLAYER_FRONT1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/PLAYER_FRONT2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/PLAYER_LEFT1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/PLAYER_LEFT2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/PLAYER_RIGHT1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/PLAYER_RIGHT2.png"));



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (!(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed)) {
            return;
        }
        // handle player input
        if (keyH.upPressed) {
            direction = "up";
            if (cH.canMove(this, direction)) {
                worldY -= speed;
            }
        } else if (keyH.downPressed) {
            direction = "down";
            if (cH.canMove(this, direction)) {
                worldY += speed;
            }
        } else if (keyH.leftPressed) {
            direction = "left";
            if (cH.canMove(this, direction)) {
                worldX -= speed;
            }
        } else if (keyH.rightPressed) {
            direction = "right";
            if (cH.canMove(this, direction)) {
                worldX += speed;
            }
        }

        // handle switching between sprites
        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null; // the image to be drawn

        // handle direction specific animation
        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
        };

        g2.drawImage(image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);

    }
}
