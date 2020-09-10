import java.util.ArrayList;
import java.util.List;

public class RoomDetails {
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
}
