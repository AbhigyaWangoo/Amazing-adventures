package student.adventure;

import student.adventure.RoomDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Highest level of JSON Schema
 */
public class Room {
    private Map<String, RoomDetail> rooms;

    public void setRooms(Map<String, RoomDetail> rooms) {
        this.rooms = rooms;
    }

    public Map<String, RoomDetail> getRooms() {
        return rooms;
    }
}
