package student.adventure;

import java.util.ArrayList;
import java.util.List;

/**
 * Level of JSON schema containing individual rooms' details
 */
public class RoomDetail {
    private String name;
    private List<String> items = new ArrayList<>();
    private List<String> availableDirections = new ArrayList<>(4);

    public String getName() {
        return name;
    }

    public List<String> getItems() {
        return items;
    }

    public List<String> getAvailableDirections() {
        return availableDirections;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
