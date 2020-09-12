package student.adventure;

import student.adventure.RoomDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Highest level of JSON Schema
 */
public class Room {
    private List<RoomDetail> rooms = new ArrayList<>();

    public List<RoomDetail> getRooms() {
        return rooms;
    }
}
