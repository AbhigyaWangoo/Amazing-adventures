package student.adventure;

import java.util.List;
import java.util.Map;

/**
 * Class for constructing map of rooms, works similar to a non-binary tree
 */
public class RoomMap {
    private String roomName;
    private Map<String,RoomMap> directions;

    public RoomMap(String roomName, Map<String,RoomMap> directions) {
        this.roomName = roomName;
        this.directions = directions;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Map<String,RoomMap> getDirections() {
        return directions;
    }

    public void setDirections(Map<String,RoomMap> directions) {
        this.directions = directions;
    }
}
