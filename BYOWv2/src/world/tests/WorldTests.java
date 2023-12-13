package world.tests;

import main.GamePanel;
import org.junit.jupiter.api.Test;
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

}
