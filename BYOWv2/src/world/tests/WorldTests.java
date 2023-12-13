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

        Hallway testHall = new Hallway(new Coordinate(20, 20), new Coordinate(30, 10));
        Hallway testHall2 = new Hallway(new Coordinate(1,1), new Coordinate(30, 30));
        testHall.updateWorld(testWorld);
        testHall2.updateWorld(testWorld);

        testWorld.saveWorldToTextFile();
    }

}
