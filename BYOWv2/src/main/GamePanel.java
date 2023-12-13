package main;

import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int ORIGINAL_TILE_SIZE = 16; // 16x16 tile
    final int SCALE = 1;

    public final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // 48x48
    public static final int MAX_SCREEN_COL = 80;
    public static final int MAX_SCREEN_ROW = 40;
    public final int SCREEN_WIDTH = MAX_SCREEN_COL * TILE_SIZE;
    public final int SCREEN_HEIGHT = MAX_SCREEN_ROW * TILE_SIZE;

    // FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // game loop goes here
        while (gameThread != null) {
            // 1. UPDATE
            update();
            // 2. REPAINT
            repaint();
        }
    }

    public void update() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);

        g2.dispose();
    }
}
