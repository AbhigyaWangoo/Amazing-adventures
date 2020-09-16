package student.adventure;

import java.util.ArrayList;
import java.util.List;

/** Class decides the player's current information */
public class Player {
  private List<String> inventory;
  private RoomDetail currentRoom;
  private RoomDetail startingRoom;
  private String currentRoomName;
  private String startingRoomName;

  // Constructor sets current room to starting room and sets starting room values
  public Player(RoomMap roomMap, String initialRoomName) {
    RoomDetail room = roomMap.getRooms().get(initialRoomName);

    setCurrentRoom(room);
    setStartingRoom(room);

    setCurrentRoomName(initialRoomName);
    setStartingRoomName(initialRoomName);

    setInventory(new ArrayList<>());
  }

  public RoomDetail getStartingRoom() {
    return startingRoom;
  }

  public void setStartingRoom(RoomDetail startingRoom) {
    this.startingRoom = startingRoom;
  }

  public String getStartingRoomName() {
    return startingRoomName;
  }

  public void setStartingRoomName(String startingRoomName) {
    this.startingRoomName = startingRoomName;
  }

  public String getCurrentRoomName() {
    return currentRoomName;
  }

  public void setCurrentRoomName(String currentRoomName) {
    this.currentRoomName = currentRoomName;
  }

  public RoomDetail getCurrentRoom() {
    return currentRoom;
  }

  public void setCurrentRoom(RoomDetail currentRoom) {
    this.currentRoom = currentRoom;
  }

  public List<String> getInventory() {
    return inventory;
  }

  public void setInventory(List<String> inventory) {
    this.inventory = inventory;
  }

  /**
   * Moves the player into specific direction, doesn't need error handling, all is done in
   * GameEngine
   *
   * @param direction determines direction to move player to
   * @param roomMap references map the player is in
   * @param currentRoom contains details about current room player is in
   */
  public void move(String direction, RoomMap roomMap, RoomDetail currentRoom) {
    switch (UIOperations.getFormattedString(direction)) {
      case Constant.WEST:
        setCurrentRoomName(currentRoom.getDirections().getWestRoom());
        setCurrentRoom(westRoom(roomMap));
        break;

      case Constant.EAST:
        setCurrentRoomName(currentRoom.getDirections().getEastRoom());
        setCurrentRoom(eastRoom(roomMap));
        break;

      case Constant.NORTH:
        setCurrentRoomName(currentRoom.getDirections().getNorthRoom());
        setCurrentRoom(northRoom(roomMap));
        break;

      case Constant.SOUTH:
        setCurrentRoomName(currentRoom.getDirections().getSouthRoom());
        setCurrentRoom(southRoom(roomMap));
        break;

      case Constant.INSIDE:
        setCurrentRoomName(currentRoom.getDirections().getInsideRoom());
        setCurrentRoom(insideRoom(roomMap));
        break;

      case Constant.OUTSIDE:
        setCurrentRoomName(currentRoom.getDirections().getOutsideRoom());
        setCurrentRoom(outsideRoom(roomMap));
        break;

      default:
        throw new IllegalArgumentException();
    }
  }

  /**
   * Function for picking up items in rooms, error handling addressed in GameEngine
   *
   * @param item to pickup
   * @param roomMap references map the player is in
   */
  public void pickUpItem(String item, RoomMap roomMap) {
    List<String> items = roomMap.getRooms().get(currentRoomName).getItems();
    int indexOf = items.indexOf(item);

    if (indexOf == -1) {
      throw new NullPointerException();
    }

    inventory.add(items.get(indexOf)); // Adds item picked up to inventory
    items.remove(indexOf); // Removes item from room

    roomMap.getRooms().get(currentRoomName).setItems(items);
  }

  /**
   * Function for dropping items in rooms, throws illegal argument exception if item doesn't exist
   *
   * @param item to drop
   * @param roomMap references map the player is in
   */
  public void dropItem(String item, RoomMap roomMap) {
    List<String> items = roomMap.getRooms().get(currentRoomName).getItems();

    inventory.remove(item); // Drops item from inventory
    items.add(item); // Adds item to room

    roomMap.getRooms().get(currentRoomName).setItems(items);
  }

  /**
   * Returns whether the direction is valid for the room
   *
   * @param direction to check for validation
   * @return whether the direction is valid or not
   */
  public boolean isValidDirection(String direction) {
    return getCurrentRoom().getDirections().findAvailableDirections().contains(direction);
  }

  /**
   * Returns whether item is available in room
   *
   * @param commandParameter to check in room
   * @return whether is available or not
   */
  public boolean isValidAvailableItem(String commandParameter) {
    return getCurrentRoom().getItems().contains(commandParameter);
  }

  /**
   * Returns whether item is available in inventory
   * @param item to check in inventory
   * @return whether it exists or not
   */
  public boolean isValidItemDrop(String item) {
    return getInventory().contains(item);
  }

  /**
   * Moves player back to starting room
   */
  public void playerMoveBackToStart() {
    setCurrentRoom(startingRoom);
    setCurrentRoomName(startingRoomName);
  }

  /**
   * All functions below return RoomDetail object of the specified direction
   *
   * @param roomMap for the map of Rooms
   * @return RoomDetail object of the room in the specified direction
   */

  // Helper functions
  private RoomDetail westRoom(RoomMap roomMap) {
    return roomMap.getRooms().get(currentRoom.getDirections().getWestRoom());
  }

  private RoomDetail eastRoom(RoomMap roomMap) {
    return roomMap.getRooms().get(currentRoom.getDirections().getEastRoom());
  }

  private RoomDetail northRoom(RoomMap roomMap) {
    return roomMap.getRooms().get(currentRoom.getDirections().getNorthRoom());
  }

  private RoomDetail southRoom(RoomMap roomMap) {
    return roomMap.getRooms().get(currentRoom.getDirections().getSouthRoom());
  }

  private RoomDetail insideRoom(RoomMap roomMap) {
    return roomMap.getRooms().get(currentRoom.getDirections().getInsideRoom());
  }

  private RoomDetail outsideRoom(RoomMap roomMap) {
    return roomMap.getRooms().get(currentRoom.getDirections().getOutsideRoom());
  }
}
