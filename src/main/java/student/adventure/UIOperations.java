package student.adventure;

import student.pojo.Constant;
import student.pojo.Direction;

import java.util.List;

/**
 * Class to handle UI Messages
 */
public class UIOperations {
  public static void examine(Player player) {
    Direction direction = player.getCurrentRoom().getDirections();
    List<String> directions = direction.findAvailableDirections();
    System.out.println("You are currently in the " + player.getCurrentRoomName());
    System.out.println("From here, you can go: " + directions);
    System.out.println("Items visible: " + player.getCurrentRoom().getItems());
    System.out.println("Inventory: " + player.getInventory());
  }

  public static String unknownCommand(){
    displayCommands();
    return "I don't understand that command, these are the available commands above^";

  }
  public static String errorDirectionMessage(String wrongDirection) {
    return "I can't go \"" + wrongDirection + "\"";
  }

  public static String displayErrorDroppingItem(String wrongItem) {
    return "You don't have \"" + wrongItem + "\"";
  }

  public static String displayErrorItemDuplicate(String wrongItem) {
    return "The Item \"" + wrongItem + "\" is already in this room!";
  }

  public static String displayErrorNoItemAvailable(String wrongItem) {
    return "There is no item \"" + wrongItem + "\" in the room";
  }

  public static void displayCommands() {
    System.out.println("\n" + Constant.GO + " + <whichever available direction you want to go in>");
    System.out.println(Constant.TAKE + " + <whichever available item you want to take>");
    System.out.println(Constant.DROP + " + <whichever available item you want to drop in the room>");
    System.out.println(Constant.START + " which takes you to the beginning of the game \n");
  }

  public static void welcomeMessage() {
    System.out.println("Welcome to your very own adventure! Here are the controls:");
    displayCommands();
    System.out.println("\nPress any key to continue");
  }

  static String getFormattedString(String s) {
    return s.replaceAll("\\s", "").toLowerCase();
  }

  public static String getPlayerLocation(Player player){
    Direction directions = player.getCurrentRoom().getDirections();
    List<String> availableDirections = directions.findAvailableDirections();

    String returnValue = "You can head ";
    for(String s:availableDirections){
      returnValue = returnValue +s+" ";
    }

    return returnValue;
  }
}
