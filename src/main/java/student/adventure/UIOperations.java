package student.adventure;

import java.util.ArrayList;
import java.util.List;

public class UIOperations {
  private String userInput;

  public String getUserInput() {
    return userInput;
  }

  public void setUserInput(String userInput) {
    this.userInput = userInput;
  }

  public void examine(Player player) {
    Direction direction = player.getCurrentRoom().getDirections();
    List<String> directions = direction.findAvailableDirections();
    System.out.println("You are currently in the " + player.getCurrentRoomName());
    System.out.println("From here, you can go: " + directions);
    System.out.println("Items visible: " + player.getInventory());
  }

  public void displayErrorDirectionMessage(String wrongDirection) {
    System.out.println("I can't go \"" + wrongDirection + "\"");
  }

  public void displayErrorDroppingItem(String wrongItem) {
    System.out.println("You don't have \"" + wrongItem + "\"");
  }

  public void displayErrorItemDuplicate(String wrongItem) {
    System.out.println("The Item \"" + wrongItem + "\" is already in this room!");
  }

  public void displayErrorNoItemAvailable(String wrongItem) {
    System.out.println("There is no item \"" + wrongItem + "\" in the room");
  }

  public void displayErrorUnknownCommand() {
    System.out.println("I don't understand that command, these are the available commands");
  }

  public void displayCommands() {
    System.out.println("go + <whichever available direction you want to go in>");
    System.out.println("take + <whichever available item you want to take>");
    System.out.println("drop + <whichever available item you want to drop in the room>");
    System.out.println("start, which takes you to the beginning of the game");
  }

  public void welcomeMessage() {
    System.out.println("Welcome to your very own adventure! Here are the controls:");
    displayCommands();
    System.out.println("Press any key to continue");
  }

  // Helper functions
}
