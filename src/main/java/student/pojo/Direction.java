package student.pojo;

import student.pojo.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Level of JSON schema holding directions
 */
public class Direction {
  private String northRoom;
  private String southRoom;
  private String westRoom;
  private String eastRoom;
  private String insideRoom;
  private String outsideRoom;

  public String getNorthRoom() {
    return northRoom;
  }

  public String getSouthRoom() {
    return southRoom;
  }

  public String getWestRoom() {
    return westRoom;
  }

  public String getEastRoom() {
    return eastRoom;
  }

  public String getInsideRoom() {
    return insideRoom;
  }

  public void setInsideRoom(String insideRoom) {
    this.insideRoom = insideRoom;
  }

  public String getOutsideRoom() {
    return outsideRoom;
  }

  public void setOutsideRoom(String outsideRoom) {
    this.outsideRoom = outsideRoom;
  }

  /**
   * Finds available directions for current RoomDetail object. error handling done above function
   *
   * @return string list of directions
   */
  public List<String> findAvailableDirections() {
    List<String> directionsList = new ArrayList<>();
    if (getNorthRoom() != null) {
      directionsList.add(Constant.NORTH);
    }

    if (getSouthRoom() != null) {
      directionsList.add(Constant.SOUTH);
    }

    if (getEastRoom() != null) {
      directionsList.add(Constant.EAST);
    }

    if (getWestRoom() != null) {
      directionsList.add(Constant.WEST);
    }

    if (getInsideRoom() != null) {
      directionsList.add(Constant.INSIDE);
    }

    if (getOutsideRoom() != null) {
      directionsList.add(Constant.OUTSIDE);
    }
    return directionsList;
  }
}
