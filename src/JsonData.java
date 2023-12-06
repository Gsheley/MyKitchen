import java.util.Calendar;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonData extends SaveAppData {

    public void create(Recipe obj) {
        // To keep the format correct with the API, each item must be in 
        // its own array by itself
        JsonArray mealsArray = new JsonArray(1);
        // New JsonObject for the 
        JsonObject newRecipe = new JsonObject();
        newRecipe.addProperty("strMeal", obj.getName());
        newRecipe.addProperty("idMeal", obj.getID());
        for (int index = 0; index < obj.recipeIngredients.size(); index++) {
            newRecipe.addProperty("strIngredient"+(index + 1), obj.recipeIngredients.get(index));
        }
        for (int index = 0; index < obj.recipeMeasurements.size(); index++) {
            newRecipe.addProperty("strMeasure"+(index + 1), obj.recipeMeasurements.get(index));
        }
        newRecipe.addProperty("strInstructions", obj.recipeSteps);
        mealsArray.add(newRecipe);
        JsonConnection.recipes.add("meals", mealsArray);
    }

    public void update(Recipe obj) {
        JsonConnection.cookbook 
    }

    public void delete(Recipe obj) {
        JsonConnection.cookbook
    }

    public void create(Pantry obj) {

    }

    public void update(Pantry obj) {
        conn.inventory
    }

    public void delete(Pantry obj) {
        conn.inventory
    }

    public void create(Pantry pantryObj, Item itemObj) {

    }

    public void update(Pantry pantryObj, Item itemObj) {
        conn.inventory
    }

    public void delete(Pantry pantryObj, Item itemObj) {
        conn.inventory
    }

    public void create(Notification obj) {

    }

    public void update(Notification obj) {
        conn.notifications
    }

    public void delete(Notification obj) {
        conn.notifications
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
