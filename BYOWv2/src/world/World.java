package world;

import main.GamePanel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class World {

    GamePanel gp;
    final int height;
    final int width;

    Random random;

    int[][] worldReferenceArray; // the integer representation of the world

    public World(GamePanel gp) {

        this.gp = gp;
        height = gp.MAX_SCREEN_ROW;
        width = gp.MAX_SCREEN_COL;

        worldReferenceArray = new int[height][width];



        generateWorld();
        saveWorldToTextFile();
    }

    public void generateWorld() {

    }

    private void saveWorldToTextFile() {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("world.txt")));
            for (int[] worldLn : worldReferenceArray) {
                for (int tile : worldLn) {
                    out.print(tile);
                }
                out.println();
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
