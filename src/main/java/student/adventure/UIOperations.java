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
    System.out.println("You are currently in the " + player.getCurrentRoomName());
    System.out.println("From here, you can go: " + findAvailableDirections(player.getCurrentRoom().getDirections()));
    System.out.println("Items visible: " + player.getInventory());
  }

  public void displayErrorDirectionMessage(String wrongDirection) {
    System.out.println("I can't go \"" + wrongDirection+"\"");
  }

  public void displayErrorDroppingItem(String wrongItem) {
    System.out.println("You don't have \"" + wrongItem+"\"");
  }

  public void displayErrorItemDuplicate(String wrongItem) {
    System.out.println("The Item \"" + wrongItem+"\" is already in this room!");
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
    System.out.println("Here is your current status:");
  }

  // Helper functions
  private List<String> findAvailableDirections(Direction directions){
    List<String> directionsList = new ArrayList<>();
    if (directions.getNorthRoom().equals(null)) {
      directionsList.add("north");
    } if (directions.getSouthRoom().equals(null)) {
      directionsList.add("south");
    } if (directions.getEastRoom().equals(null)) {
      directionsList.add("east");
    } if (directions.getWestRoom().equals(null)) {
      directionsList.add("west");
    } if (directions.getInsideRoom().equals(null)) {
      directionsList.add("inside");
    } if (directions.getOutsideRoom().equals(null)) {
      directionsList.add("outside");
    }
    return directionsList;
  }
}
