package world.tests;

import main.GamePanel;
import org.junit.jupiter.api.Test;
import world.Coordinate;
import world.Hallway;
import world.World;

public class WorldTests {

    @Test
    public void basicRoomTest() {
        GamePanel gp = new GamePanel();
        World testWorld = new World(gp);

        testWorld.generateWorld();
    }

    @Test
    public void basicSaveTest() {
        GamePanel gp = new GamePanel();
        World testWorld = new World(gp);
    }

    @Test
    public void sanityHallwayTest() {
        GamePanel gp = new GamePanel();
        World testWorld = new World(gp);

        Hallway testHall = new Hallway(new Coordinate(35, 38), new Coordinate(30, 15));
        testHall.updateWorld(testWorld);

        testWorld.saveWorldToTextFile();
    }

}
