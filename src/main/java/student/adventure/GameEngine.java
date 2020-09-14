package student.adventure;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class GameEngine {
  private RoomMap rooms;
  private Player player;
  private UIOperations uiOperations;

  public GameEngine() {
    setUpGame();
  }

  public Player getPlayer() {
    return player;
  }

  public RoomMap getRooms() {
    return rooms;
  }

  /**
   * Standard function to deserialize JSON file into room member variable, prints stack in case
   * deserialize causes error
   *
   * @return true if successful, false if otherwise
   */
  public boolean deserialize() {
    Gson gson = new Gson();
    rooms = new RoomMap();
    String file =
        "/Users/abhigyawangoo/IdeaProjects/amazing-adventures-AbhiWangoo/src/main/resources/AdventureMap.json";

    try {
      String jsonString = readFileAsString(file);
      Type type = new TypeToken<HashMap<String, RoomDetail>>() {}.getType();

      Map<String, RoomDetail> map = gson.fromJson(jsonString, type);
      rooms.setRooms(map);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Function called to begin game on command line. In case of setup error, prints stack,
   * in case of unknown command, will redisplay examine board and claim that command was invalid
   *
   * @param inputStream specifies the input stream to read from
   */
  public void play(InputStream inputStream) {
    try {
      uiOperations = new UIOperations();
      uiOperations.welcomeMessage();

      String userInput = "";
      Scanner scanner = new Scanner(inputStream);

      while (userInput != "exit") {
        uiOperations.examine(player);
        userInput = scanner.nextLine();

        userInput = followCommand(userInput);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    GameEngine gameEngine = new GameEngine();
    gameEngine.play(System.in);
  }

  // helper methods
  /**
   * Sets up entire game, loads JSON, assigns player to first room
   */
  private void setUpGame() {
    deserialize();
    player = new Player(rooms, "soccerField");
  }

  /**
   * Converts File to String, throws exception if file doesn't exist or with reading problem
   *
   * @param file for reading
   * @return String value of file
   * @throws Exception in case of readability error
   */
  private static String readFileAsString(String file) throws Exception {
    return new String(Files.readAllBytes(Paths.get(file)));
  }

  /**
   * Follows and executes command from user input provided in play(). Returns empty string if command
   * isn't recognizable
   * @param input represents user input from play()
   * @return empty String if theres no problem with command, "Unknown Command" if otherwise
   */
  private String followCommand(String input) {
    input = input.toLowerCase();
    String[] commandParameters = input.split(" ");
    if(commandParameters.length != 2)
      throw new IllegalArgumentException();

    String command = commandParameters[0]; // action
    String parameter = commandParameters[1]; // specification

    List<String> commands = new ArrayList<>();
    commands.add("go");
    commands.add("drop");
    commands.add("take");
    commands.add("start");
    commands.add("exit");
    commands.add("examine"); // potentially needs to be moved

    return determineCommand(commands, command, parameter);
  }

  private String determineCommand(List<String> commands, String command, String parameter) {
    if (!commands.contains(command)) {
      uiOperations.displayErrorUnknownCommand();
      return "Unknown Command";
    }

    if (commands.get(0).compareTo(command) == 0) {
      playerMovementCommand(parameter);
    }
    else if (commands.get(1).compareTo(command) == 0) {
      playerDropCommand(parameter);
    }
    else if (commands.get(2).compareTo(command) == 0) {
      playerPickUpCommand(parameter);
    }
    else if (commands.get(3).compareTo("start") == 0) {
      player.playerMoveBackToStart();
    } else if (command.compareTo("exit") == 0) {
      return "exit";
    }
    return "";
  }


  private void playerPickUpCommand(String parameter) {
    if (!player.isValidAvailableItem(parameter)) {
      uiOperations.displayErrorNoItemAvailable(parameter);
    } else {
      player.pickUpItem(parameter, rooms);
    }
  }

  private void playerDropCommand(String parameter) {
    if (!player.isValidItemDrop(parameter)) {
      uiOperations.displayErrorDirectionMessage(parameter);
    } else if (player.isValidAvailableItem(parameter)) {
      uiOperations.displayErrorNoItemAvailable(parameter);
    } else {
      player.dropItem(parameter, rooms);
    }
  }

  private void playerMovementCommand(String parameter) {
    if (!player.isValidDirection(parameter)) {
      uiOperations.displayErrorDirectionMessage(parameter);
    } else {
      player.move(parameter, rooms, player.getCurrentRoom());
    }
  }
}
