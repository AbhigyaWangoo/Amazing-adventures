package student.adventure;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class GameEngine {
  private RoomMap rooms;
  private Player player;
  private RoomDetail startingRoom;
  private String startingRoomName;
  private UIOperations uiOperations;

  public UIOperations getUiOperations() {
    return uiOperations;
  }

  public Player getPlayer() {
    return player;
  }

  public RoomMap getRooms() {
    return rooms;
  }

  /**
   * Standard function to deserialize JSON file into room member variable
   *
   * @return true if successful, false if otherwise
   */
  public boolean deserialize() {
    Gson gson = new Gson();
    rooms = new RoomMap();

    try {
      String jsonString =
          readFileAsString(
              "/Users/abhigyawangoo/IdeaProjects/amazing-adventures-AbhiWangoo/src/main/resources/AdventureMap.json");
      Type type = new TypeToken<HashMap<String, RoomDetail>>() {}.getType();

      Map<String, RoomDetail> map = gson.fromJson(jsonString, type);
      rooms.setRooms(map);

      System.out.println("loaded JSON with size "+map.size());
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  void setUpGame() {
    player = new Player(rooms);
    startingRoom = player.getCurrentRoom();
    startingRoomName = "soccerField"; // TBD
  }

  public void play() {
    try {
      uiOperations = new UIOperations();
      uiOperations.welcomeMessage();

      String userInput = "";
      Scanner scanner = new Scanner(new InputStreamReader(System.in));

      while (userInput != "exit") {
        uiOperations.examine(player);
        userInput = scanner.nextLine();

        followCommand(userInput);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    GameEngine gameEngine = new GameEngine();
    gameEngine.deserialize();
    gameEngine.setUpGame();
    gameEngine.play();
  }

  // helper methods
  /**
   * Converts File to String
   *
   * @param file for reading
   * @return String value of file
   * @throws Exception in case of readability error
   */
  private static String readFileAsString(String file) throws Exception {
    return new String(Files.readAllBytes(Paths.get(file)));
  }

  private String followCommand(String command) {
    command = command.toLowerCase();
    String[] commandParameters = command.split(" ");
    command = commandParameters[0];
    String parameter = commandParameters[1];

    if (command.compareTo("go") == 0) {
      if (!isValidDirection(parameter)) {
        uiOperations.displayErrorDirectionMessage(parameter);
      }
      else {
        player.move(parameter, rooms, player.getCurrentRoom());
      }
    }

    else if (command.compareTo("drop") == 0) {
      if (!isValidItemDrop(parameter)) {
        uiOperations.displayErrorDirectionMessage(parameter);
      } else if (isValidAvailableItem(parameter)) {
        uiOperations.displayErrorNoItemAvailable(parameter);
      }
      else{
        player.dropItem(parameter, rooms);
      }
    }

    else if (command.compareTo("take") == 0) {
      if (!isValidAvailableItem(parameter)) {
        uiOperations.displayErrorNoItemAvailable(parameter);
      }
      else{
        player.pickUpItem(parameter, rooms);
      }
    }

    else if (command.compareTo("start") == 0) {
      player.setCurrentRoom(startingRoom);
      player.setCurrentRoomName(startingRoomName);
    }

    else if (command.compareTo("exit") == 0) {
      return "exit";
    }
    return "Unknown Command";
  }

  private boolean isValidAvailableItem(String commandParameter) {
    return player.getCurrentRoom().getItems().contains(commandParameter);
  }

  private boolean isValidItemDrop(String item) {
    return player.getInventory().contains(item);
  }

  private boolean isValidDirection(String direction) {
    return player.getCurrentRoom().getDirections().findAvailableDirections().contains(direction);
  }
}
