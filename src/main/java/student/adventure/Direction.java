package student.adventure;

import java.util.ArrayList;
import java.util.List;

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

  public List<String> findAvailableDirections() {
    List<String> directionsList = new ArrayList<>();
    if (getNorthRoom() != null) {
      directionsList.add(Constants.NORTH);
    }

    if (getSouthRoom() != null) {
      directionsList.add(Constants.SOUTH);
    }

    if (getEastRoom() != null) {
      directionsList.add(Constants.EAST);
    }

    if (getWestRoom() != null) {
      directionsList.add(Constants.WEST);
    }

    if (getInsideRoom() != null) {
      directionsList.add(Constants.INSIDE);
    }

    if (getOutsideRoom() != null) {
      directionsList.add(Constants.OUTSIDE);
    }
    return directionsList;
  }
}
