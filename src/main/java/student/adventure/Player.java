package student.adventure;

import java.util.ArrayList;
import java.util.List;
/**
 * Class decides the player's current information
 */
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
     * Moves the player into specific direction
     * @param direction determines direction to move player to
     * @param roomMap references map the player is in
     * @param currentRoom contains details about current room player is in
     */
    public void move(String direction, RoomMap roomMap, RoomDetail currentRoom){
        switch(getFormattedString(direction)){
            case Constants.WEST:
                setCurrentRoomName(currentRoom.getDirections().getWestRoom());
                setCurrentRoom(westRoom(roomMap,currentRoom));
                break;

            case Constants.EAST:
                setCurrentRoomName(currentRoom.getDirections().getEastRoom());
                setCurrentRoom(eastRoom(roomMap,currentRoom));
                break;

            case Constants.NORTH:
                setCurrentRoomName(currentRoom.getDirections().getNorthRoom());
                setCurrentRoom(northRoom(roomMap,currentRoom));
                break;

            case Constants.SOUTH:
                setCurrentRoomName(currentRoom.getDirections().getSouthRoom());
                setCurrentRoom(southRoom(roomMap,currentRoom));
                break;

            case Constants.INSIDE:
                setCurrentRoomName(currentRoom.getDirections().getInsideRoom());
                setCurrentRoom(insideRoom(roomMap,currentRoom));
                break;

            case Constants.OUTSIDE:
                setCurrentRoomName(currentRoom.getDirections().getOutsideRoom());
                setCurrentRoom(outsideRoom(roomMap,currentRoom));
                break;

            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * Function for picking up items in rooms, throws runtime exception if item doesn't exist
     * @param item to pickup
     * @param roomMap references map the player is in
     */
    public void pickUpItem(String item, RoomMap roomMap){
        List<String> items = roomMap.getRooms().get(currentRoomName).getItems();
        try{
            int indexOf = items.indexOf(item);
            if (indexOf == -1)
                throw new NullPointerException();

            inventory.add(items.get(indexOf)); // Adds item picked up to inventory
            items.remove(indexOf); // Removes item from room

            roomMap.getRooms().get(currentRoomName).setItems(items);
        }catch (NullPointerException e) {
            System.out.println("Item doesn't exist");
        } catch(Exception e){
            throw new RuntimeException();
        }
    }

    /**
     * Function for dropping items in rooms
     * @param item to drop
     * @param roomMap references map the player is in
     */
    public void dropItem(String item, RoomMap roomMap){
        List<String> roomItems = roomMap.getRooms().get(currentRoomName).getItems();
        try{
            inventory.remove(item); // Drops item from inventory
            roomItems.add(item); // Adds item to room

            roomMap.getRooms().get(currentRoomName).setItems(roomItems);
        } catch(Exception e){
            throw new RuntimeException();
        }
    }

    public boolean isValidDirection(String direction) {
        return getCurrentRoom().getDirections().findAvailableDirections().contains(direction);
    }

    public boolean isValidAvailableItem(String commandParameter) {
        return getCurrentRoom().getItems().contains(commandParameter);
    }

    public boolean isValidItemDrop(String item) {
        return getInventory().contains(item);
    }

    public void playerMoveBackToStart() {
        setCurrentRoom(startingRoom);
        setCurrentRoomName(startingRoomName);
    }

    // Helper functions
    // following functions return a RoomDetail object of the room in the given direction
    private RoomDetail westRoom(RoomMap roomMap, RoomDetail currentRoom){
        return roomMap.getRooms().get(currentRoom.getDirections().getWestRoom());
    }
    private RoomDetail eastRoom(RoomMap roomMap, RoomDetail currentRoom){
        return roomMap.getRooms().get(currentRoom.getDirections().getEastRoom());
    }
    private RoomDetail northRoom(RoomMap roomMap, RoomDetail currentRoom){
        return roomMap.getRooms().get(currentRoom.getDirections().getNorthRoom());
    }
    private RoomDetail southRoom(RoomMap roomMap, RoomDetail currentRoom){
        return roomMap.getRooms().get(currentRoom.getDirections().getSouthRoom());
    }
    private RoomDetail insideRoom(RoomMap roomMap, RoomDetail currentRoom){
        return roomMap.getRooms().get(currentRoom.getDirections().getInsideRoom());
    }
    private RoomDetail outsideRoom(RoomMap roomMap, RoomDetail currentRoom){
        return roomMap.getRooms().get(currentRoom.getDirections().getOutsideRoom());
    }

    private String getFormattedString(String s){
        return s.replaceAll("\\s","").toLowerCase();
    }
}
