package world;

import main.GamePanel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class World {

    GamePanel gp;
    final int height;
    final int width;

    final int MIN_ROOM_DIMENSION = 5;
    final int MAX_ROOM_DIMENSION = 13;

    Random random;

    public Coordinate playerSpawnPoint;
    public Coordinate batteryTest;

    int[][] worldReferenceArray; // the integer representation of the world

    public World(GamePanel gp) {

        this.gp = gp;
        height = GamePanel.MAX_WORLD_ROW;
        width = GamePanel.MAX_WORLD_COL;

        worldReferenceArray = new int[height][width];

        random = new Random();

        generateWorld();
        saveWorldToTextFile();
    }

    public void generateWorld() {
        generateRooms();
    }

    private void generateRooms() {
        // rooms will be between 5x5 and 15x15
        List<Room> generatedRooms = new LinkedList<>();

        // the following code attempts to generate rooms at valid root points with p = 1 / 4

        for (int i = MAX_ROOM_DIMENSION; i <= width - (2 * MAX_ROOM_DIMENSION); i += MAX_ROOM_DIMENSION) {
            for (int j = MAX_ROOM_DIMENSION; j <= height - MAX_ROOM_DIMENSION; j += MAX_ROOM_DIMENSION) { // loop through every valid root location

                int generationChance = random.nextInt(4);

                if (generationChance == 3) {
                    continue;
                }


                int rHeight = random.nextInt(MAX_ROOM_DIMENSION - MIN_ROOM_DIMENSION) + MIN_ROOM_DIMENSION;
                int rWidth = random.nextInt(MAX_ROOM_DIMENSION - MIN_ROOM_DIMENSION) + MIN_ROOM_DIMENSION;

                Room room = new Room(rWidth, rHeight, new Coordinate(i, j));
                generatedRooms.add(room);
                room.updateWorld(this);
            }
        }

        // generate hallways between all generated rooms

        Room currRoom = generatedRooms.get(random.nextInt(generatedRooms.size()));

        for (Room room : generatedRooms) {
            new Hallway(currRoom.getRoomCenter(), room.getRoomCenter()).updateWorld(this);
            currRoom = room;
        }

        // set the player spawn point to the center of a random room

        playerSpawnPoint = generatedRooms.get(random.nextInt(generatedRooms.size())).getRoomCenter();
        batteryTest = generatedRooms.get(random.nextInt(generatedRooms.size())).getRoomCenter();
    }

    public void saveWorldToTextFile() {

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

    public int[][] getWorldReference() {
        return worldReferenceArray;
    }

    public Coordinate getPlayerSpawnPoint() {
        return playerSpawnPoint;
    }
}
