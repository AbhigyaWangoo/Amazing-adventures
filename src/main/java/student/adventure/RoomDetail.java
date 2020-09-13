package student.adventure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Level of JSON schema containing individual rooms' details
 */
public class RoomDetail {
    private List<String> items = new ArrayList<>();
    private Direction directions;

    public List<String> getItems() {
        return items;
    }

    public Direction getDirections() {
        return directions;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
