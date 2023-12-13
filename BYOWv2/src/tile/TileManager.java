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
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/NOTHING.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GRASS.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/TREE.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() { // loads world.txt into mapTileNum

        mapTileNum = new int[gp.MAX_SCREEN_ROW][gp.MAX_SCREEN_COL];

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

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.MAX_SCREEN_COL && row < gp.MAX_SCREEN_ROW) {

            Tile currentTile = tile[mapTileNum[row][col]];

            g2.drawImage(currentTile.image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
            col++;
            x += gp.TILE_SIZE;

            if (col == gp.MAX_SCREEN_COL) {
                col = 0;
                x = 0;
                row++;
                y += gp.TILE_SIZE;
            }
        }
    }
}
