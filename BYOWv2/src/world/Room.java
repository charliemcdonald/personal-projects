package world;

public class Room {

    int height;
    int width;
    Coordinate root; // the root of a room is the bottom left corner;

    public Room(int width, int height, Coordinate root) {

        this.height = height;
        this.width = width;
        this.root = root;
    }

    public void updateWorld(World world) {

        int[][] refArray = world.worldReferenceArray;

        for (int dx = 0; dx < height; dx++) {
            for (int dy = 0; dy < width; dy++) {

                int x = root.getX() + dx;
                int y = root.getY() + dy;

                // check for wall and not already floor
                if ((dx == 0 || dy == 0 || dx == height - 1 || dy == width - 1) && refArray[x][y] != 1) {
                    refArray[x][y] = 2;
                } else {
                    refArray[x][y] = 1; // otherwise floor
                }
            }
        }
    }
}
