package tile;

import main.GamePanel;
import org.eclipse.jetty.util.ajax.JSON;
import world.World;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
    int[][] mapTileNum;

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[10];

        getTileImage();

        loadMap();
    }

    public void getTileImage() {

        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/TREE.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GRASS.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/TREE.png"));
            tile[2].collision = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() { // loads world.txt into mapTileNum

        mapTileNum = new int[gp.MAX_WORLD_ROW][gp.MAX_WORLD_COL];

        try {
            BufferedReader br = new BufferedReader(new FileReader("world.txt")); // reads world.txt
            String line = br.readLine();
            int i = 0;

            while (line != null) { // loops line by line
                char[] numArray = line.toCharArray();
                for (int j = 0; j < numArray.length; j++) {
                    mapTileNum[i][j] = numArray[j] - '0'; // converts char '0' to int 0
                }
                i++;
                line = br.readLine();
            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < GamePanel.MAX_WORLD_COL && worldRow < GamePanel.MAX_WORLD_ROW) {

            Tile currentTile = tile[mapTileNum[worldRow][worldCol]];

            int worldX = worldCol * gp.TILE_SIZE;
            int worldY = worldRow * gp.TILE_SIZE;
            int screenX = worldX - gp.player.worldX + gp.player.screenX; // find screen position
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // only render tiles in the player viewport
            if (worldX + gp.TILE_SIZE > gp.player.worldX - gp.player.screenX &&
                worldX - gp.TILE_SIZE < gp.player.worldX + gp.player.screenX &&
                worldY + gp.TILE_SIZE > gp.player.worldY - gp.player.screenY &&
                worldY - gp.TILE_SIZE < gp.player.worldY + gp.player.screenY) {

                    g2.drawImage(currentTile.image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
            }


            worldCol++;

            if (worldCol == GamePanel.MAX_WORLD_COL) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

    public Tile[] getTile() {
        return tile;
    }

    public int[][] getMapTileNum() {
        return mapTileNum;
    }
}
