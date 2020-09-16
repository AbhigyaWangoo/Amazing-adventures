package student.adventure;

import java.util.HashMap;
import java.util.Map;

/** Class for constructing map of rooms, works similar to a non-binary tree */
public class RoomMap {
  private Map<String, RoomDetail> rooms = new HashMap<>();

  public void setRooms(Map<String, RoomDetail> rooms) {
    this.rooms = rooms;
  }

  public Map<String, RoomDetail> getRooms() {
    return rooms;
  }
}
