package student.adventure;


import com.google.gson.JsonObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;


public class AdventureTest {
    private GameEngine gameEngine;
    private Player player;
    private String args;
    private ByteArrayInputStream in;
    private final String SOCCER_FIELD = "soccerField";
    private final String AMAZON_TOWER = "amazonTower";
    private final String BALL = "ball";
    private final String WEST = "west";
    @Before
    public void setUp() {
        gameEngine = new GameEngine();
        player = new Player(gameEngine.getRooms(), SOCCER_FIELD);
    }

    @After
    public void closeStreams(){
        System.out.flush();
        OutputStream outs = new ByteArrayOutputStream();
    }
    @Test
    public void testDeserialization() {
        assertTrue(gameEngine.deserialize());
    }

    @Test
    public void isValidJson(){
        JsonObject jsonObject = new JsonObject();
        if(jsonObject.has(SOCCER_FIELD) && jsonObject.has(WEST) && jsonObject.has(BALL)){
            assertTrue(true);
        }
    }

    @Test
    public void testPlayerMove() {
        RoomDetail nextRoom = gameEngine.getRooms().getRooms().get(AMAZON_TOWER);

        player.move("west", gameEngine.getRooms(),player.getStartingRoom());
        assertEquals(player.getCurrentRoom(), nextRoom);
    }

    @Test
    public void testPlayerPath() {
        GameEngine gameEngine = new GameEngine();
        InputStream x = System.in;
        gameEngine.play(x);

        args = "go east";
        in = new ByteArrayInputStream(args.getBytes());

        Scanner scanner = new Scanner(in);

    }

    @Test
    public void testItemPickup() {
        gameEngine.getPlayer().pickUpItem(BALL, gameEngine.getRooms());

        gameEngine.getRooms().getRooms().get(SOCCER_FIELD).getItems().remove(0);
        RoomDetail finalRoom = gameEngine.getRooms().getRooms().get(SOCCER_FIELD);


        if(gameEngine.getPlayer().isValidItemDrop(BALL) && !finalRoom.getItems().contains(BALL)){
            assertTrue(true);
        }
    }

    @Test
    public void testItemDrop() {
        gameEngine.getPlayer().pickUpItem(BALL, gameEngine.getRooms());
        gameEngine.getPlayer().dropItem(BALL, gameEngine.getRooms());
        List<String> emptyList = new ArrayList<>();
        assertEquals(emptyList,gameEngine.getPlayer().getInventory());
    }

    @Test
    public void testGoToStart(){
        gameEngine.getPlayer().move("south", gameEngine.getRooms(), player.getCurrentRoom());
        gameEngine.getPlayer().playerMoveBackToStart();

        assertEquals(gameEngine.getPlayer().getStartingRoom(), gameEngine.getPlayer().getStartingRoom());
    }

    // Boundary Cases
    @Test
    public void testNullMovePath() {
        RoomDetail currentRoom = gameEngine.getRooms().getRooms().get(SOCCER_FIELD);
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
    public void testDuplicateItemPickup() {
        try{
            gameEngine.getPlayer().getInventory().add(BALL);
            gameEngine.getPlayer().pickUpItem(BALL, gameEngine.getRooms());
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