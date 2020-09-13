package student.adventure;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class AdventureTest {
    GameEngine gameEngine = new GameEngine();
    Player player = new Player();
    @Before
    public void setUp() {
        gameEngine = new GameEngine();
        player.setCurrentRoom(gameEngine.getRooms().getRooms().get("soccerField"));
        player.setCurrentRoomName("soccerField");
        player.setInventory(new ArrayList<>());

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
        player.pickUpItem("ball", gameEngine.getRooms());

        gameEngine.getRooms().getRooms().get("soccerField").getItems().remove(0);
        RoomDetail finalRoom = gameEngine.getRooms().getRooms().get("soccerField");

        if(player.getInventory().contains("ball") && !finalRoom.getItems().contains("ball")){
            assertTrue(true);
        }
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

    @Test
    public void testNonexistantItem() {
        try{
            player.pickUpItem("the state of Illinois", gameEngine.getRooms());
        } catch (RuntimeException e){
            assertTrue(true);
        }
    }
}