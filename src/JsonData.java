import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonData extends SaveAppData {
    private JsonConnection conn; 

    public void save(Recipe obj) {
        JsonObject jsonObject = new JsonObject();
        JsonArray mealsArray = jsonObject.getAsJsonArray("meals");
        JsonObject mealObject = mealsArray.get(0).getAsJsonObject();
    }
    public void delete(Recipe obj) {

    }
    public void save(Item obj) {

    }
    public void delete(Item obj) {

    }
}
