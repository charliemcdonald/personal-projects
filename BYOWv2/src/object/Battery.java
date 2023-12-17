package object;

import main.GamePanel;

public class Battery extends GameObject {

    public Battery(GamePanel gp, int worldX, int worldY) {
        super(gp, worldX * gp.TILE_SIZE, worldY * gp.TILE_SIZE);

        loadImage("/objects/BATTERY.png");
    }
}
