import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;

public class JsonConnection extends Connection {
    // Object for the file
    File jsonFile;
    // An overall Json Object for the file
    private JsonObject jsonObject;
    // Json Objects for saving and retrieving different kinds of data
    public JsonArray inventory = new JsonArray();
    public JsonObject cookbook = new JsonObject();
    public JsonArray recipes = new JsonArray();
    public JsonObject notifications = new JsonObject();
    public JsonArray listNotifications = new JsonArray();
    public int numKitchens = 0;
    public int numShoppingCarts = 0;
    public int nextShoppingCartID = 0;
    public int nextKitchenInventoryID = PantryService.getRange();
    // A constant for the file name
    private final String FILE_NAME = "MyKitchenData.json";
    
    public void open() {
        // Trying to retrieve the file
        FileReader inputFile;
        try {
            // If it is present, get the contents of the file into a String
            jsonFile = new File(FILE_NAME);
            inputFile = new FileReader(jsonFile);
        // If the file is not present, simply print it out and exit the method
        } catch (FileNotFoundException e) {
            return;
        }
        // Using the file contents to go through and recover data
        jsonObject = JsonParser.parseReader(inputFile).getAsJsonObject();
        // Retreiving the number of kitchens and shopping carts
        numKitchens = jsonObject.get("numKitchens").getAsInt();
        Navigation.contr.setNumPantries(numKitchens);
        numShoppingCarts = jsonObject.get("numShoppingCarts").getAsInt();
        Navigation.contr.setNumCarts(numShoppingCarts);
        // Retrieving the next IDs for Shopping Carts and KitchenInventories
        nextShoppingCartID = jsonObject.get("nextSCID").getAsInt();
        PantryService.setNextShoppingCartID(nextShoppingCartID);
        nextKitchenInventoryID = jsonObject.get("nextKIID").getAsInt();
        PantryService.setNextKitchenInventoryID(nextKitchenInventoryID);
        // Starting with the inventory of Pantries to get the contents of items
        inventory = jsonObject.getAsJsonArray("inventory");
        // If the inventory has elements, then we need to find and record them
        if (inventory != null) {
            // New object for the pantry to record
            JsonObject pantryObject;
            // Looping through each of the pantries
            for(int index1 = 0; index1 < inventory.size(); index1++) {
                // Initalizing the pantry
                pantryObject = inventory.get(index1).getAsJsonObject();
                // Getting the ID of the pantry
                int pantryID = pantryObject.get("pantryID").getAsInt();
                // Getting the current Item ID of the pantry
                int pantryCurID = pantryObject.get("currentItemID").getAsInt();
                // Getting the name of the pantry
                String pantryName = pantryObject.get("pantryName").getAsString();
                // Creating the pantry object with the above attributes
                Pantry newPantry = new Pantry(pantryID, pantryName);
                newPantry.setCurrentItemID(pantryCurID);
                // Creating an array for the items contained within the pantry
                JsonArray items = pantryObject.getAsJsonArray("items");
                // If the pantry has items, we need to record them
                if (items != null) {
                    // Creating another JsonObject for each item to add within the pantry
                    JsonObject currentItem;
                    // Looping through the items
                    for(int index2 = 0; index2 < items.size(); index2++) { 
                        // Initalizing the item
                        currentItem = items.get(index2).getAsJsonObject();
                        // Getting the ID of the item
                        int itemID = currentItem.get("itemID").getAsInt();
                        // Getting the name of the item
                        String itemName = currentItem.get("itemName").getAsString();
                        // Getting the date the item was added
                        JsonObject itemAddedEntry = currentItem.get("dateAdded").getAsJsonObject();
                        Calendar dateAdded = null;
                        if (itemAddedEntry != null) { 
                            dateAdded = jsonToCalendar(itemAddedEntry);
                        }
                        int itemQuantity = currentItem.get("quantity").getAsInt();
                        if (currentItem.has("expirationDate")) {
                            JsonObject expirationEntry = currentItem.get("expirationDate").getAsJsonObject();
                            Calendar expirationDate = null;
                            if (expirationEntry != null) { 
                                expirationDate = jsonToCalendar(expirationEntry);
                            }
                            Item newItem = new Item(itemID, itemName, dateAdded, itemQuantity, expirationDate);
                            newPantry.items.add(newItem);
                        } else {
                            Item newItem = new Item(itemID, itemName, dateAdded, itemQuantity);
                            newPantry.items.add(newItem);
                        }
                    }
                }
                Kitchen.inventory.add(newPantry);
            }
        }
        // Getting the data from the cookbook in the json file
        cookbook = jsonObject.getAsJsonObject("cookbook");
        // If the cookbook has elements, then we need to find and record them
        if (cookbook != null) {
            // Getting the data from the original Cookbook
            if (cookbook.has("cookbookName")) {
                String cookbookName = cookbook.get("cookbookName").getAsString();
                // Setting up the values for the cookbook
                Cookbook.setName(cookbookName);
            }
            recipes = cookbook.getAsJsonArray("recipes");
            // Populating the Cookbook with its recipes
            for (int index = 0; index < recipes.size(); index++) {
                // Getting each recipe from the JsonArray was a JsonString
                Cookbook.recipes.add(new Recipe((recipes.get(index)).toString()));
            }
        }
        // Getting the data from the Notifications in the json file
        notifications = jsonObject.getAsJsonObject("notifications");
        // If there is data regarding notifications, we need to record it
        if (notifications != null) {
            // Making a new NotificationService object
            NotificationService newService = new NotificationService();
            // Getting the data from the original Notification aggreagte
            if (notifications.has("currentID")) {
                int currentID = notifications.get("currentID").getAsInt();
                newService.setCurrentNotifID(currentID);
            }
            // Getting the list of notifications for record
            if (notifications.has("listNotifications")) {
                listNotifications = notifications.getAsJsonArray("listNotifications");
                ArrayList<Notification> newNotificationList = new ArrayList<Notification>();
                // If the list of notifications has elements, then we need to find and record them
                if (listNotifications != null) {
                    // New object for the notification to record
                    JsonObject notificationObject;
                    // Looping through each of the notifications
                    for(int index = 0; index < listNotifications.size(); index++) {
                        // Getting the notification object
                        notificationObject = listNotifications.get(index).getAsJsonObject();
                        // Getting the notification message
                        String notificationMessage = notificationObject.get("message").getAsString();
                        // Getting the notification ID
                        int notificationID = notificationObject.get("notificationID").getAsInt();
                        // Getting the notify date for the notification
                        JsonObject notifyDate = notificationObject.get("notifyDate").getAsJsonObject();
                        Calendar notifyCalendar = null;
                        if (notifyDate != null) { 
                            notifyCalendar = jsonToCalendar(notifyDate);
                        }
                        newNotificationList.add(new ViewNotification(notificationID, notifyCalendar, notificationMessage));
                    }
                    newService.setNotificationList(newNotificationList);
                }
            }
            Navigation.contr.setNotificationService(newService);
        }
    }

    public void save() {
       // Trying to retrieve the file
        try {
            File newFile = new File(FILE_NAME);
            // If the file is not present, simply print it out and create a new file
            if (newFile.createNewFile()) {
                System.out.println("Previous data file could not be found, creating a new file to save");
            }
            FileWriter jsonFile = new FileWriter(FILE_NAME);
            JsonObject newObject = new JsonObject();
            cookbook.add("recipes", recipes);
            notifications.add("listNotifications", listNotifications);
            newObject.addProperty("numKitchens", numKitchens);
            newObject.addProperty("numShoppingCarts", numShoppingCarts);
            newObject.addProperty("nextSCID", nextShoppingCartID);
            newObject.addProperty("nextKIID", nextKitchenInventoryID);
            newObject.add("inventory", inventory);
            newObject.add("cookbook", cookbook);
            newObject.add("notifications", notifications);
            Gson gson = new Gson();
            gson.toJson(newObject, jsonFile);
            jsonFile.close();
        // Catching any errors that may come when trying to create or retrieve the file
        } catch (IOException e) {
            System.out.println("An Error occured when trying to save to the file");
        } 
    }

    // Private helper method for the conversion of json objects to Calendar objects
    private Calendar jsonToCalendar(JsonObject obj) {
        // Creating a new Calendar Object
        Calendar newDate = Calendar.getInstance();
        // Getting each of the time measurements from the JsonObject
        int year = obj.get("year").getAsInt();
        int month = obj.get("month").getAsInt();
        int day = obj.get("day").getAsInt();
        int hour = obj.get("hour").getAsInt();
        int minute = obj.get("minute").getAsInt();
        // Setting the time measurement into the Calendar
        newDate.set(Calendar.YEAR, year);
        newDate.set(Calendar.MONTH, month - 1);
        newDate.set(Calendar.DAY_OF_MONTH, day);
        newDate.set(Calendar.HOUR_OF_DAY, hour);
        newDate.set(Calendar.MINUTE, minute);
        newDate.set(Calendar.SECOND, 0);
        newDate.set(Calendar.MILLISECOND, 0);
        // Returning the Calendar
        return newDate;
    }
}
