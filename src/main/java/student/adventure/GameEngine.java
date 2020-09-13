package student.adventure;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameEngine {
  private RoomMap rooms = new RoomMap();

  public RoomMap getRooms() {
    return rooms;
  }

  /**
   * Standard function to deserialize JSON file into room member variable
   * @return true if successful, false if otherwise
   */
  public boolean deserialize() {
    Gson gson = new Gson();
    try {
      String jsonString =
          readFileAsString(
              "/Users/abhigyawangoo/IdeaProjects/amazing-adventures-AbhiWangoo/src/main/resources/AdventureMap.json");
      Type type = new TypeToken<HashMap<String, RoomDetail>>() {}.getType();

      Map<String, RoomDetail> map = gson.fromJson(jsonString, type);
      rooms.setRooms(map);

      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public void play() {}


  // helper methods
  /**
   * Converts File to String
   * @param file for reading
   * @return String value of file
   * @throws Exception in case of readability error
   */
  public static String readFileAsString(String file) throws Exception {
    return new String(Files.readAllBytes(Paths.get(file)));
  }
}
