package student.adventure;

import java.util.List;



// STATIC OR NAW?



/**
 * Class decides the player's current information
 */
public class Player {
    private List<Directions> Inventory;
    private RoomMap currentRoom;

    public RoomMap getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(RoomMap currentRoom) {
        this.currentRoom = currentRoom;
    }

    public List<Directions> getInventory() {
        return Inventory;
    }
    public void setInventory(List<Directions> inventory) {
        Inventory = inventory;
    }

    /**
     * Moves the player into specific direction
     * @param direction determines direction to move player to
     */
    public void move(String direction){
        switch(getFormattedString(direction)){
            case "west":
                setCurrentRoom(currentRoom.getDirections().get(indexOfDirection(Directions.West)));
                break;

            case "east":
                setCurrentRoom(currentRoom.getDirections().get(indexOfDirection(Directions.East)));
                break;

            case "north":
                setCurrentRoom(currentRoom.getDirections().get(indexOfDirection(Directions.North)));
                break;

            case "south":
                setCurrentRoom(currentRoom.getDirections().get(indexOfDirection(Directions.South)));
                break;

            case "inside":
                setCurrentRoom(currentRoom.getDirections().get(indexOfDirection(Directions.Inside)));
                break;

            case "outside":
                setCurrentRoom(currentRoom.getDirections().get(indexOfDirection(Directions.Outside)));
                break;

            default:
                throw new IllegalArgumentException();
        }
    }

    // helper functions
    private int indexOfDirection(Directions direction){
        return currentRoom.getDirections().indexOf(direction);
    }

    private String getFormattedString(String s){
        return s.replaceAll("\\s","").toLowerCase();
    }
}
