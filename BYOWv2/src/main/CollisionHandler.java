package main;

import entity.Entity;
import tile.Tile;

import java.awt.*;

public class CollisionHandler {

    GamePanel gp;
    Tile[] tileset;
    int[][] mapTileNum;

    public CollisionHandler(GamePanel gp) {

        this.gp = gp;
        tileset = gp.tileM.getTile();
        mapTileNum = gp.tileM.getMapTileNum();
    }

    public boolean isEmpty(int worldX, int worldY) { // returns true/false if tile at worldX, worldY has collision properties

        int tileIndex = mapTileNum[worldY / gp.TILE_SIZE][worldX / gp.TILE_SIZE];
        return !tileset[tileIndex].collision;
    }

    // VERY BAD DO NOT TOUCH WILL BREAK IF YOU BREATHE ON IT
    public boolean canMove(Entity e, String direction) {

        Rectangle hb = e.hitBox;

        int offset = (gp.TILE_SIZE / 4);

        int topPoint = e.worldY + offset;
        int bottomPoint = e.worldY + e.hitBox.width + (2 * offset);
        int leftPoint = e.worldX + offset;
        int rightPoint = e.worldX + e.hitBox.width + offset;


        switch (direction) {
            case "up":
                return isEmpty(leftPoint, topPoint - e.speed) && isEmpty(rightPoint, topPoint - e.speed);
            case "left":
                return isEmpty(leftPoint - e.speed, topPoint) && isEmpty(leftPoint - e.speed, bottomPoint);
            case "down":
                return isEmpty(leftPoint, bottomPoint + e.speed) && isEmpty(leftPoint, bottomPoint + e.speed);
            case "right":
                return isEmpty(rightPoint + e.speed, topPoint) && isEmpty(rightPoint + e.speed, bottomPoint);
        }

        return true;
    }

    public boolean collided(Rectangle hb1, Rectangle hb2) {

        return hb1.intersects(hb2);
    }

    // for debugging only
    public void drawCollisionPoints(Entity e, Graphics2D g2) {

        int offset = (gp.TILE_SIZE / 4);

        int topPoint = e.worldY + offset;
        int bottomPoint = e.worldY + e.hitBox.width + (2 * offset);
        int leftPoint = e.worldX + offset;
        int rightPoint = e.worldX + e.hitBox.width + offset;

        g2.setColor(Color.blue);
        g2.drawRect(leftPoint, bottomPoint, 1, 1); // bottom left
        g2.drawRect(rightPoint, bottomPoint, 1, 1); // bottom right
        g2.drawRect(leftPoint, topPoint, 1, 1); // top left
        g2.drawRect(rightPoint, topPoint , 1, 1); // top right
    }
}
