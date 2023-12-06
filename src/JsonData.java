import java.util.Calendar;

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

    private JsonObject calendarToJson(Calendar date, JsonObject obj) {
        // Getting each of the time measurements from the Calendar 
        // object into the JsonObject
        obj.addProperty("year", date.get(Calendar.YEAR));
        obj.addProperty("month", date.get(Calendar.MONTH));
        obj.addProperty("day", date.get(Calendar.DAY_OF_MONTH));
        obj.addProperty("hour", date.get(Calendar.HOUR_OF_DAY));
        obj.addProperty("minute", date.get(Calendar.MINUTE));
        // Returning back the JsonObject
        return obj;
    }
}
