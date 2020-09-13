package student.adventure;

import java.util.List;
/**
 * Class decides the player's current information
 */
public class Player {
    private List<String> inventory;
    private RoomDetail currentRoom;

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
                setCurrentRoom(westRoom(roomMap,currentRoom));
                break;

            case "east":
                setCurrentRoom(eastRoom(roomMap,currentRoom));
                break;

            case "north":
                setCurrentRoom(northRoom(roomMap,currentRoom));
                break;

            case "south":
                setCurrentRoom(southRoom(roomMap,currentRoom));
                break;

            case "inside":
                setCurrentRoom(insideRoom(roomMap,currentRoom));
                break;

            case "outside":
                setCurrentRoom(outsideRoom(roomMap,currentRoom));
                break;

            default:
                throw new IllegalArgumentException();
        }
    }

    // helper functions

    // following functions return a RoomDetail object of the room in the given direction
    private RoomDetail westRoom(RoomMap roomMap, RoomDetail currentRoom){
        String westRoom = currentRoom.getDirections().getWest();
        return roomMap.getRooms().get(westRoom);
    }
    private RoomDetail eastRoom(RoomMap roomMap, RoomDetail currentRoom){
        return roomMap.getRooms().get(currentRoom.getDirections().getEast());
    }
    private RoomDetail northRoom(RoomMap roomMap, RoomDetail currentRoom){
        return roomMap.getRooms().get(currentRoom.getDirections().getNorth());
    }
    private RoomDetail southRoom(RoomMap roomMap, RoomDetail currentRoom){
        return roomMap.getRooms().get(currentRoom.getDirections().getSouth());
    }
    private RoomDetail insideRoom(RoomMap roomMap, RoomDetail currentRoom){
        return roomMap.getRooms().get(currentRoom.getDirections().getInside());
    }
    private RoomDetail outsideRoom(RoomMap roomMap, RoomDetail currentRoom){
        return roomMap.getRooms().get(currentRoom.getDirections().getOutside());
    }

    private String getFormattedString(String s){
        return s.replaceAll("\\s","").toLowerCase();
    }
}
