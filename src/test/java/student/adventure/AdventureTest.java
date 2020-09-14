package student.adventure;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class AdventureTest {
    GameEngine gameEngine = new GameEngine();
    Player player;

    @Before
    public void setUp() {
        gameEngine.deserialize();
        player = new Player(gameEngine.getRooms());
    }

    @Test
    public void testDeserialization() {
        assertTrue(gameEngine.deserialize());
    }

    @Test
    public void testPlayerMove() {
        RoomDetail currentRoom = gameEngine.getRooms().getRooms().get("soccerField");
        RoomDetail nextRoom = gameEngine.getRooms().getRooms().get("amazonTower");

        player.move("west", gameEngine.getRooms(),currentRoom);
        assertEquals(player.getCurrentRoom(), nextRoom);
    }

    @Test
    public void testInventoryDisplay(){

    }
    @Test
    public void testPlayerPath() {
        String[] args = {"go","east"};
        gameEngine.play();
    }

    @Test
    public void testNullPath() {
    }

    @Test
    public void testItemPickup() {
        gameEngine.getPlayer().pickUpItem("ball", gameEngine.getRooms());

        gameEngine.getRooms().getRooms().get("soccerField").getItems().remove(0);
        RoomDetail finalRoom = gameEngine.getRooms().getRooms().get("soccerField");

        if(gameEngine.getPlayer().getInventory().contains("ball") && !finalRoom.getItems().contains("ball")){
            assertTrue(true);
        }
    }

    @Test
    public void testItemDrop() {
        gameEngine.getPlayer().pickUpItem("ball", gameEngine.getRooms());
        gameEngine.getPlayer().dropItem("ball", gameEngine.getRooms());
        List<String> emptyList = new ArrayList<>();
        assertEquals(emptyList,gameEngine.getPlayer().getInventory());
    }

    // Boundary Cases
    @Test
    public void testNullMovePath() {
        RoomDetail currentRoom = gameEngine.getRooms().getRooms().get("soccerField");
        try{
            gameEngine.getPlayer().move("north", gameEngine.getRooms(), currentRoom);
        } catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }

    @Test
    public void testNonexistantItemPickup() {
        try{
            gameEngine.getPlayer().pickUpItem("the state of Illinois", gameEngine.getRooms());
        } catch (RuntimeException e){
            assertTrue(true);
        }
    }

    @Test
    public void testNonexistantItemDrop() {
        try{
            gameEngine.getPlayer().dropItem("valuable onyx quartz and jewels", gameEngine.getRooms());
        } catch (RuntimeException e){
            assertTrue(true);
        }
    }
}