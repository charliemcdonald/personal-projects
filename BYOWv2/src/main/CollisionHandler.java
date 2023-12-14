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

    public boolean isEmpty(int worldX, int worldY) {

        int tileIndex = mapTileNum[worldX / gp.TILE_SIZE][worldY / gp.TILE_SIZE];
        return !tileset[tileIndex].collision;
    }

    public boolean canMove(Entity e, String direction) {

        Rectangle hb = e.hitBox;

        int leftPoint = e.worldY;
        int rightPoint = e.worldY + hb.width;
        int topPoint = e.worldX;
        int bottomPoint = e.worldX + hb.height;

        switch (direction) {
            case "up":
                return isEmpty(leftPoint, topPoint - e.speed) && isEmpty(rightPoint, topPoint - e.speed);
//            case "left":
//                return isEmpty(e.worldX, e.worldY - e.speed) && isEmpty(e.worldX + hb.height, e.worldY - e.speed);
//            case "down":
//                return isEmpty(e.worldX + hb.height + e.speed, e.worldY) && isEmpty(e.worldX + hb.height + e.speed, e.worldY + hb.width);
//            case "right":
//                return isEmpty(e.worldX, e.worldY + hb.width + e.speed) && isEmpty(e.worldX + hb.height, e.worldY + hb.width + e.speed);
        }

        return true;
    }
}
