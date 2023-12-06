import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;

public class JsonConnection extends Connection {
    // Creating an object to get input from the json file
    InputStream inputFile;
    // An overall Json Object for the file
    private JsonObject jsonObject;
    // 3 Json Objects for saving and retrieving different kinds of data
    public JsonArray inventory;
    public JsonObject cookbook;
    public JsonObject notifications;
    // A constant for the file name
    private final String FILE_NAME = "AppData.json";
    
    public void open() {
        // Trying to retrieve the file
        try {
            // If it is present, get the contents of the file into a String
            inputFile = new FileInputStream(FILE_NAME);
        // If the file is not present, simply print it out and exit the method
        } catch (FileNotFoundException e) {
            System.out.println("Previous data file could not be found");
            return;
        }
        // Getting the data from the file
        String fileData = inputFile.toString();
        // Using the file contents to go through and recover data
        jsonObject = JsonParser.parseString(fileData).getAsJsonObject();
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
                        if (pantryID < PantryService.getRange()) {
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
            String cookbookName = cookbook.get("cookbookName").getAsString();
            JsonArray recipes = cookbook.getAsJsonArray("recipes");
            // Setting up the values for the cookbook
            Cookbook.setName(cookbookName);
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
            // Getting the data from the original Notification aggreagte
            int currentID = notifications.get("currentID").getAsInt();
            // Getting the list of notifications for record
            JsonArray listNotifications = notifications.getAsJsonArray("listNotifications");
            // Making a new NotificationService object
            NotificationService newService = new NotificationService();
            newService.setCurrentNotifID(currentID);
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
            }
            newService.setCurrentNotifID(currentID);
            newService.setNotificationList(newNotificationList);
            Controller.setNotificationService(newService);
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
            newObject.add("inventory", inventory);
            newObject.add("cookbook", cookbook);
            newObject.add("notifications", notifications);
            jsonFile.write(newObject.toString());
            jsonFile.close();
        // Catching any errors that may come when trying to create or retrieve the file
        } catch (IOException e) {
            System.out.println("An Error occured when trying to save to the file");
        } 
    }

    public void close() {
        // Trying to close the json file
        try {
            // If it is present, then we need to close it
            inputFile.close();
        // If the file is not present, simply print it out and exit the method
        } catch (IOException e) {
            System.out.println("App data file could not be found");
            return;
        }
    }

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
