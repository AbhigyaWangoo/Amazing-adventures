package student.adventure;

import java.util.List;

/**
 * Class for constructing map of rooms, works similar to a non-binary tree
 */
public class RoomMap {
    private String roomName;
    private List<RoomMap> directions;

    public RoomMap(String roomName, List<RoomMap> directions) {
        this.roomName = roomName;
        this.directions = directions;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public List<RoomMap> getDirections() {
        return directions;
    }

    public void setDirections(List<RoomMap> directions) {
        this.directions = directions;
    }
}
