package main;

import world.World;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Build Your Own World v2");
        try {
            window.setIconImage(ImageIO.read(Main.class.getResourceAsStream("/player/PLAYER_FRONT1.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);


        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}