import java.util.Calendar;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonData extends SaveAppData {

    private static JsonConnection conn = new JsonConnection();

    public void create(Recipe obj) {
        conn.cookbook.remove("cookbookName");
        conn.cookbook.addProperty("cookbookName", Cookbook.cookbookName);
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
        JsonObject aggregate = new JsonObject();
        // To keep the same formatting with the Recipe class and with the API
        // we connect with, it is necessary to give each Recipe its own single array
        aggregate.add("meals", mealsArray);
        conn.recipes.add(aggregate);
        save();
    }

    public void update(Recipe obj) {
        conn.cookbook.remove("cookbookName");
        conn.cookbook.addProperty("cookbookName", Cookbook.cookbookName);
        JsonArray jsonRecipeArray;
        JsonObject jsonRecipe = null;
        int index = 0;
        for (; index < conn.recipes.size(); index++) {
            jsonRecipeArray = conn.recipes.get(index).getAsJsonArray();
            jsonRecipe = jsonRecipeArray.get(0).getAsJsonObject();
            if (obj.getID() == jsonRecipe.get("idMeal").getAsInt()) {
                break;
            }
        }
        if (jsonRecipe != null) {
            jsonRecipe.remove("strMeal");
            jsonRecipe.remove("idMeal");
            jsonRecipe.remove("strIngredient");
            jsonRecipe.remove("strMeasure");
            jsonRecipe.remove("strInstructions");
            
            jsonRecipe.addProperty("strMeal", obj.getName());
            jsonRecipe.addProperty("idMeal", obj.getID());
            for (int i = 0; i < obj.recipeIngredients.size(); i++) {
                jsonRecipe.addProperty("strIngredient"+(i + 1), obj.recipeIngredients.get(i));
            }
            for (int i = 0; i < obj.recipeMeasurements.size(); i++) {
                jsonRecipe.addProperty("strMeasure"+(i + 1), obj.recipeMeasurements.get(i));
            }
            jsonRecipe.addProperty("strInstructions", obj.recipeSteps);
            JsonArray replacementArray = new JsonArray(1);
            replacementArray.add(jsonRecipe);
            conn.recipes.set(index, replacementArray);
        }
        save();
    }

    public void delete(Recipe obj) {
        conn.cookbook.remove("cookbookName");
        conn.cookbook.addProperty("cookbookName", Cookbook.cookbookName);
        for (int index = 0; index < conn.recipes.size(); index++) {
            JsonObject jsonRecipeArray = conn.recipes.get(index).getAsJsonObject();
            JsonArray mealsArray = jsonRecipeArray.getAsJsonArray("meals");
            JsonObject mealObject = mealsArray.get(0).getAsJsonObject();

            if (obj.getID() == mealObject.get("idMeal").getAsInt()) {
                conn.recipes.remove(index);
                break;
            }
        }
        save();
    }

    public void create(Pantry obj) {
        // Creating a new JsonObject for the new pantry
        JsonObject newPantry = new JsonObject();
        // Creating new properties for each of the pantry's attributes
        newPantry.addProperty("pantryID", obj.getPantryID());
        newPantry.addProperty("currentItemID", obj.getCurrentItemID());
        newPantry.addProperty("pantryName", obj.getPantryName());
        // The items will be represented by a JsonArray
        JsonArray items = new JsonArray();
        for (int index = 0; index < obj.items.size(); index++) {
            items.add(itemToJson(obj.getPantryID(), obj.getItem(index)));
        }
        newPantry.add("items", items);
        conn.inventory.add(newPantry);
        // Updating the records of the pantries and shopping carts accordingly
        if (obj.getPantryID() < PantryService.getRange()) {
            conn.numKitchens = Navigation.contr.getNumPantries();
            conn.nextKitchenInventoryID = PantryService.getNextKitchenInventoryID();
        } else {
            conn.numShoppingCarts = Navigation.contr.getNumCarts();
            conn.nextShoppingCartID = PantryService.getNextShoppingCartID();
        }
        save();
    }

    public void update(Pantry obj) {
        JsonObject jsonPantry = null;
        int index = 0;
        for (; index < conn.inventory.size(); index++) {
            jsonPantry = conn.inventory.get(index).getAsJsonObject();
            if (obj.getPantryID() == jsonPantry.get("pantryID").getAsInt()) {
                break;
            }
        }
        if (jsonPantry != null) {
            jsonPantry.remove("pantryID");
            jsonPantry.remove("currentItemID");
            jsonPantry.remove("pantryName");
            jsonPantry.remove("items");
        
            jsonPantry.addProperty("pantryID", obj.getPantryID());
            jsonPantry.addProperty("currentItemID", obj.getCurrentItemID());
            jsonPantry.addProperty("pantryName", obj.getPantryName());
            // The items will be represented by a JsonArray
            JsonArray items = new JsonArray();
            for (int i = 0; i < obj.items.size(); i++) {
                items.add(itemToJson(obj.getPantryID(), obj.getItem(i)));
            }
            jsonPantry.add("items", items);
            conn.inventory.set(index, jsonPantry);
            save();
        }
    }

    public void delete(Pantry obj) {
        JsonObject jsonPantry = null;
        int index = 0;
        for (; index < conn.inventory.size(); index++) {
            jsonPantry = conn.inventory.get(index).getAsJsonObject();
            if (obj.getPantryID() == jsonPantry.get("pantryID").getAsInt()) {
                conn.inventory.remove(index);
                break;
            }
        }
        save();
    }

    public void create(Notification obj) {
        // Update the current NotificationID in the record
        conn.notifications.remove("currentID");
        conn.notifications.addProperty("currentID", obj.getNotifID());
        // Creating a new JsonObject for the new notification
        conn.listNotifications.add(notificationToJson(obj));
        save();
    }

    public void update(Notification obj) {
        // Update the current NotificationID in the record
        conn.notifications.remove("currentID");
        conn.notifications.addProperty("currentID", obj.getNotifID());
        // Search for the correct notification in the list
        JsonObject jsonNotification = null;
        int index = 0;
        for (; index < conn.listNotifications.size(); index++) {
            jsonNotification = conn.listNotifications.get(index).getAsJsonObject();
            if (obj.getNotifID() == jsonNotification.get("notificationID").getAsInt()) {
                break;
            }
        }
        conn.listNotifications.set(index, jsonNotification);
        save();
    }

    public void delete(Notification obj) {
        // Update the current NotificationID in the record
        conn.notifications.remove("currentID");
        conn.notifications.addProperty("currentID", obj.getNotifID());
        // Search for the correct notification in the list
        JsonObject jsonNotification = null;
        int index = 0;
        for (; index < conn.listNotifications.size(); index++) {
            jsonNotification = conn.listNotifications.get(index).getAsJsonObject();
            if (obj.getNotifID() == jsonNotification.get("notificationID").getAsInt()) {
                conn.listNotifications.remove(index);
                break;
            }
        }
        save();
    }

    private JsonObject calendarToJson(Calendar date) {
        // Creating the JsonObject for storage
        JsonObject obj = new JsonObject();
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

    private JsonObject itemToJson(int pantryID, Item obj) {
        // Creating a new JsonObject for the output conversion
        JsonObject output = new JsonObject();
        // Recording the Item's attributes
        output.addProperty("itemID", obj.getItemID());
        output.addProperty("itemName", obj.getName());
        output.add("dateAdded", calendarToJson(obj.getDateAdded()));
        output.addProperty("quantity", obj.getQuantity());
        // If this is a Kitchen Inventory (ID is less than the range), then 
        // we also need to record the expiration date
        if (pantryID < PantryService.getRange() && obj.getExpirationDate() != null) {
            output.add("expirationDate", calendarToJson(obj.getExpirationDate()));
        }
        return output;
    }

    private JsonObject notificationToJson(Notification obj) {
        // Creating a new JsonObject for the output conversion
        JsonObject output = new JsonObject();
        // Recording the output's attributes
        output.addProperty("message", obj.getMessage());
        output.addProperty("notificationID", obj.getNotifID());
        output.add("notifyDate", calendarToJson(obj.getNotifDate()));
        // Return the new JsonObject
        return output;
    }

    // Communicating with the Connection Class
     public void open() {
        conn.open();
    }

    public void save() {
        conn.save();
    }
    
}
