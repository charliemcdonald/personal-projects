package world;

import main.GamePanel;

public class Coordinate {

    private int x;
    private int y;

    private int screenX;
    private int screenY;

    public Coordinate(int x, int y) { // converts user coord's into screen coord's

        this.x = x;
        this.y = y;

        this.screenX = GamePanel.MAX_SCREEN_ROW - 1 - y;
        this.screenY = x;
    }

    public int getX() {
        return screenX;
    }

    public int getY() {
        return screenY;
    }

    public int getUserX() {
        return x;
    }

    public int getUserY() {
        return y;
    }
}
