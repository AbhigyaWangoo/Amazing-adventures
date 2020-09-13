package student.adventure;


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
}