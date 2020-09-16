package student.adventure;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class GameEngine {
  private RoomMap rooms;
  private Player player;
  private Gson gson;

  public Gson getGson() {
    return gson;
  }

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
    gson = new Gson();
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
      UIOperations.welcomeMessage();

      String userInput = "";
      Scanner scanner = new Scanner(inputStream);

      while (!userInput.toLowerCase().equals(Constant.EXIT) && !userInput.toLowerCase().equals(Constant.QUIT)) {
        UIOperations.examine(player);
        userInput = scanner.nextLine().toLowerCase();
        try {
          userInput = followCommand(userInput);
        } catch (IllegalArgumentException e) {
          System.out.println(e.getMessage());
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
   * Follows and executes command from user input provided in play(). Throws
   * IllegalArgumentException if the user's input isn't in the form (command) (parameter)
   *
   * @param input represents user input from play()
   * @return empty String if theres no problem with command, "Unknown Command" if otherwise
   */
  private String followCommand(String input) {
    String[] commandParameters = input.split(" ");

    if (commandParameters.length != 2) {
      throw new IllegalArgumentException(UIOperations.unknownCommand());
    }

    String command = commandParameters[0]; // action
    String parameter = commandParameters[1]; // action specification

    List<String> commands = new ArrayList<>();
    commands.add(Constant.GO);
    commands.add(Constant.DROP);
    commands.add(Constant.TAKE);
    commands.add(Constant.START);
    commands.add(Constant.EXIT);
    commands.add(Constant.EXAMINE); // potentially needs to be moved

    return determineCommand(commands, command, parameter);
  }

  /**
   * Returns String which corresponds to proper commands object. Then appropriately changes player
   * based on command. If command doesn't exist, throws IllegalArgumentException
   *
   * @param commands includes list of acceptable commands
   * @param command has command for checking
   * @param parameter has parameter for specification of direction, item, etc.
   * @return String exit if user wants to quit, exception otherwise
   */
  private String determineCommand(List<String> commands, String command, String parameter) {
    if (!commands.contains(command)) {
      throw new IllegalArgumentException(UIOperations.unknownCommand());
    }

    if (commands.get(0).compareTo(command) == 0) {
      playerMovementCommand(parameter);
    } else if (commands.get(1).compareTo(command) == 0) {
      playerDropCommand(parameter);
    } else if (commands.get(2).compareTo(command) == 0) {
      playerPickUpCommand(parameter);
    } else if (commands.get(3).compareTo(Constant.START) == 0) {
      player.playerMoveBackToStart();
    } else if (command.compareTo(Constant.EXIT) == 0) {
      return Constant.EXIT;
    }
    return "\n";
  }

  /**
   * Called when user inputs "take" command. In case of an item which doesn't exist, function throws
   * an IllegalArgumentException with a message of error on the command line.
   *
   * @param parameter specifies the item to take
   */
  private void playerPickUpCommand(String parameter) {
    if (!player.isValidAvailableItem(parameter)) {
      throw new IllegalArgumentException(UIOperations.displayErrorNoItemAvailable(parameter));
    } else {
      player.pickUpItem(parameter, rooms);
    }
  }

  /**
   * Called when user inputs "drop" command. In case item doesn't exist in the inventory, or the
   * item already exists in the room, function throws an IllegalArgumentException with a message of
   * error on the command line.
   *
   * @param parameter specifies the item to drop
   */
  private void playerDropCommand(String parameter) {
    if (!player.isValidItemDrop(parameter)) {
      throw new IllegalArgumentException(UIOperations.displayErrorDroppingItem(parameter));
    } else if (player.isValidAvailableItem(parameter)) {
      throw new IllegalArgumentException(UIOperations.displayErrorItemDuplicate(parameter));
    } else {
      player.dropItem(parameter, rooms);
    }
  }

  /**
   * Called when user inputs "go" command. In case the direction isn't valid, function throws an
   * IllegalArgumentException with message of error
   *
   * @param parameter specifies the direction to move in
   */
  private void playerMovementCommand(String parameter) {
    if (!player.isValidDirection(parameter)) {
      throw new IllegalArgumentException(UIOperations.errorDirectionMessage(parameter));
    } else {
      player.move(parameter, rooms, player.getCurrentRoom());
    }
  }
}
