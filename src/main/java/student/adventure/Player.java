package student.adventure;

import java.util.List;
/**
 * Class decides the player's current information
 */
public class Player {
    private List<String> inventory;
    private RoomDetail currentRoom;
    private String currentRoomName;

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
     */
    public void move(String direction, RoomMap roomMap, RoomDetail currentRoom){
        switch(getFormattedString(direction)){
            case "west":
                setCurrentRoomName(currentRoom.getDirections().getWestRoom());
                setCurrentRoom(westRoom(roomMap,currentRoom));

                break;

            case "east":
                setCurrentRoomName(currentRoom.getDirections().getEastRoom());
                setCurrentRoom(eastRoom(roomMap,currentRoom));
                break;

            case "north":
                setCurrentRoomName(currentRoom.getDirections().getNorthRoom());
                setCurrentRoom(northRoom(roomMap,currentRoom));
                break;

            case "south":
                setCurrentRoomName(currentRoom.getDirections().getSouthRoom());
                setCurrentRoom(southRoom(roomMap,currentRoom));
                break;

            case "inside":
                setCurrentRoomName(currentRoom.getDirections().getInsideRoom());
                setCurrentRoom(insideRoom(roomMap,currentRoom));
                break;

            case "outside":
                setCurrentRoomName(currentRoom.getDirections().getOutsideRoom());
                setCurrentRoom(outsideRoom(roomMap,currentRoom));
                break;

            default:
                throw new IllegalArgumentException();
        }
    }

    public void pickUpItem(String item, RoomMap roomMap){
        List<String> items = roomMap.getRooms().get(currentRoomName).getItems();
        try{
            int indexOf = items.indexOf(item);

            inventory.add(items.get(indexOf)); // Adds item picked up to inventory
            items.remove(indexOf); // Removes item from room

            roomMap.getRooms().get(currentRoomName).setItems(items);
        } catch(Exception e){
            throw new RuntimeException();
        }
    }

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
