package student.adventure;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class AdventureTest {
    GameEngine gameEngine = new GameEngine();

    @Before
    public void setUp() {
        gameEngine = new GameEngine();
        gameEngine.deserialize();
    }

    @Test
    public void testDeserialization() {
        assertTrue(gameEngine.deserialize());
    }

    @Test
    public void testPlayerMove() {
        Player player = new Player();
        RoomDetail currentRoom = gameEngine.getRooms().getRooms().get("soccerField");
        RoomDetail nextRoom = gameEngine.getRooms().getRooms().get("amazonTower");

        player.move("west", gameEngine.getRooms(), currentRoom);
        assertEquals(player.getCurrentRoom(),nextRoom);
    }

    @Test
    public void testPlayerPath() {
    }

    @Test
    public void testNullPath() {
    }

    @Test
    public void testItemDrop() {
    }

    @Test
    public void testItemPickup() {
    }

    // Boundary Cases
    @Test
    public void testNullMovePath() {
        Player player = new Player();
        RoomDetail currentRoom = gameEngine.getRooms().getRooms().get("soccerField");
        try{
            player.move("north", gameEngine.getRooms(), currentRoom);
        } catch (IllegalArgumentException e){
            assertTrue(true);
        }

    }
}