import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonConnection extends Connection {
    private JsonObject jsonObject;
    private File data;
    private String fileName = "AppData.json";

    public void open() {
        // Getting the AppData json file for previous data and storage
        data = new File(fileName);
        // Testing to see if the file is present
        if (data.exists()) { 
            // If it is present, get the contents of the file into a String
            InputStream inputFile = new FileInputStream(fileName);
            String fileData = inputFile.toString();
            // Using the file contents to go through and recover data
            jsonObject = JsonParser.parseString(fileData).getAsJsonObject();
            // Starting with the inventory of Pantries to get the contents of items
            JsonArray inventory = jsonObject.getAsJsonArray("inventory");
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
                                int year = itemAddedEntry.get("year").getAsInt();
                                int month = itemAddedEntry.get("month").getAsInt();
                                int day = itemAddedEntry.get("day").getAsInt();
                                int hour = itemAddedEntry.get("hour").getAsInt();
                                int minute = itemAddedEntry.get("minute").getAsInt();
                                dateAdded = Calendar.getInstance();
                                dateAdded.set(Calendar.YEAR, year);
                                dateAdded.set(Calendar.MONTH, month - 1);
                                dateAdded.set(Calendar.DAY_OF_MONTH, day);
                                dateAdded.set(Calendar.HOUR_OF_DAY, hour);
                                dateAdded.set(Calendar.MINUTE, minute);
                                dateAdded.set(Calendar.SECOND, 0);
                                dateAdded.set(Calendar.MILLISECOND, 0);
                            }
                            int itemQuantity = currentItem.get("quantity").getAsInt();
                            if (pantryID < PantryService.getRange()) {
                                JsonObject expirationEntry = currentItem.get("expirationDate").getAsJsonObject();
                                Calendar expirationDate = null;
                                if (expirationEntry != null) { 
                                    int year = expirationEntry.get("year").getAsInt();
                                    int month = expirationEntry.get("month").getAsInt();
                                    int day = expirationEntry.get("day").getAsInt();
                                    int hour = expirationEntry.get("hour").getAsInt();
                                    int minute = expirationEntry.get("minute").getAsInt();
                                    dateAdded = Calendar.getInstance();
                                    dateAdded.set(Calendar.YEAR, year);
                                    dateAdded.set(Calendar.MONTH, month - 1);
                                    dateAdded.set(Calendar.DAY_OF_MONTH, day);
                                    dateAdded.set(Calendar.HOUR_OF_DAY, hour);
                                    dateAdded.set(Calendar.MINUTE, minute);
                                    dateAdded.set(Calendar.SECOND, 0);
                                    dateAdded.set(Calendar.MILLISECOND, 0);
                                }
                                Item newItem = new Item(itemID, itemName, dateAdded, itemQuantity, expirationDate);
                                newPantry.items.add(newItem);
                            } else {
                                Item newItem = new Item(itemID, itemName, dateAdded, itemQuantity);
                                newPantry.items.add(newItem);
                            }
                        }
                    }
                }
            }

            
        } else {
            System.out.println("Previous data file could not be found");
        }
    }
    public void save() {

    }
    public void close() {
        jsonObject = new JsonObject();
        JsonArray kitchen = jsonObject.getJsonArray("kitchen");
        jsonObject.
        JsonArray mealsArray = jsonObject.getAsJsonArray("meals");
        JsonObject mealObject = mealsArray.get(0).getAsJsonObject();
    }
}
