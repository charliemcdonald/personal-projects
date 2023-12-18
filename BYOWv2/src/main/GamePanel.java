package main;

import entity.Player;
import object.Battery;
import tile.TileManager;
import world.World;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {


    // SCREEN SETTINGS
    final int ORIGINAL_TILE_SIZE = 16; // 16x16 tile
    public final int SCALE = 3;

    public final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // 48x48
    public static final int MAX_SCREEN_COL = 16;
    public static final int MAX_SCREEN_ROW = 12;
    public final int SCREEN_WIDTH = MAX_SCREEN_COL * TILE_SIZE;
    public final int SCREEN_HEIGHT = MAX_SCREEN_ROW * TILE_SIZE;


    // WORLD SETTINGS
    public static final int MAX_WORLD_COL = 100;
    public static final int MAX_WORLD_ROW = 60;
    public final int WORLD_WIDTH = TILE_SIZE * MAX_WORLD_COL;
    public final int WORLD_HEIGHT = TILE_SIZE * MAX_WORLD_ROW;
    // FPS
    int FPS = 60;

    TileManager tileM;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionHandler collisionHandler;

    public World world;
    public Player player;
    public Battery battery;

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true); // GP "focused" to receive key input

        world = new World(this);
        tileM = new TileManager(this);

        collisionHandler = new CollisionHandler(this);


        player = new Player(this, keyH); // generate last
        battery = new Battery(this, world.batteryTest.getX(), world.batteryTest.getY());
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // game loop goes here

        double drawInterval = 1000000000.0 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            // 1. UPDATE
            update();
            // 2. REPAINT
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {

        player.update();
        battery.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);
        battery.draw(g2);
        battery.drawHitBox(g2);
        player.draw(g2);


        g2.dispose();
    }
}
