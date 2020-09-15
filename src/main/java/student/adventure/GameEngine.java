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
   * Function called to begin game on command line. In case of setup error, prints stack, in case of
   * unknown command, will redisplay examine board and claim that command was invalid
   *
   * @param inputStream specifies the input stream to read from
   */
  public void play(InputStream inputStream) {
    try {
      uiOperations = new UIOperations();
      uiOperations.welcomeMessage();

      String userInput = "";
      Scanner scanner = new Scanner(inputStream);

      while (!userInput.toLowerCase().equals(Constants.EXIT)) {
        uiOperations.examine(player);
        userInput = scanner.nextLine().toLowerCase();
        try{
          userInput = followCommand(userInput);
        } catch (IllegalArgumentException e) {
          System.out.println(e.getMessage());
          uiOperations.displayErrorUnknownCommand();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // helper methods
  /** Sets up entire game, loads JSON, assigns player to first room */
  private void setUpGame() {
    deserialize();
    player = new Player(rooms, player.getStartingRoomName());
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
   * Follows and executes command from user input provided in play(). Returns empty string if
   * command isn't recognizable
   *
   * @param input represents user input from play()
   * @return empty String if theres no problem with command, "Unknown Command" if otherwise
   */
  private String followCommand(String input) {
    String[] commandParameters = input.split(" ");

    if (commandParameters.length != 2) {
      throw new IllegalArgumentException(UIOperations.ERR_UNKNOWN_CMD);
    }

    String command = commandParameters[0]; // action
    String parameter = commandParameters[1]; // action specification

    List<String> commands = new ArrayList<>();
    commands.add(Constants.GO);
    commands.add(Constants.DROP);
    commands.add(Constants.TAKE);
    commands.add(Constants.START);
    commands.add(Constants.EXIT);
    commands.add(Constants.EXAMINE); // potentially needs to be moved

    return determineCommand(commands, command, parameter);
  }

  private String determineCommand(List<String> commands, String command, String parameter) {
    if (!commands.contains(command)) {
      throw new IllegalArgumentException(UIOperations.ERR_UNKNOWN_CMD);
    }

    if (commands.get(0).compareTo(command) == 0) {
      playerMovementCommand(parameter);
    } else if (commands.get(1).compareTo(command) == 0) {
      playerDropCommand(parameter);
    } else if (commands.get(2).compareTo(command) == 0) {
      playerPickUpCommand(parameter);
    } else if (commands.get(3).compareTo(Constants.START) == 0) {
      player.playerMoveBackToStart();
    } else if (command.compareTo(Constants.EXIT) == 0) {
      return Constants.EXIT;
    }
    throw new IllegalArgumentException(UIOperations.ERR_UNKNOWN_CMD);
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
