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
    }

    @Test
    public void testDeserialization() {
        assertTrue(gameEngine.deserialize());
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
}