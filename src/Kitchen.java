import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

enum PantryType {
    KITCHEN_INVENTORY,
    SHOPPING_CART
}

public class Kitchen 
{
    private static ArrayList<Pantry> inventory = new ArrayList<Pantry>();
    public Cookbook myCookbook;

    public static void createPantry(PantryType type, String name){
        inventory.add(PantryService.createPantry(type, name));
    }

    public static Pantry retrievePantry(int pantryID){
        //checks each item in the items array list
        int index = getPantryIndex(pantryID);
        if (index == -1) {
            return null; 
        } else {
            return inventory.get(pantryID);
        }
    }

    public static void deletePantry(int pantryID) {
        int index = getPantryIndex(pantryID);
        if (index != -1) {
            inventory.remove(index);
        }
    }

    public static void addItem(int pantryID, String name, Calendar dateAdded, int quantity){
        int index = getPantryIndex(pantryID);
        if (index != -1) {
            inventory.get(index).addItem(name, dateAdded, quantity);
        }
    }

    public static void addItem(int pantryID, String name, Calendar dateAdded, int quantity, Calendar expirDate){
        int index = getPantryIndex(pantryID);
        if (index != -1) {
            inventory.get(index).addItem(name, dateAdded, quantity, expirDate);
        }
    }

    public static void editItem(int pantryID, int itemID, String name, Calendar dateAdded, int quantity, Calendar expirDate){
        int index = getPantryIndex(pantryID);
        if (index != -1) {
            inventory.get(index).editItem(itemID, name, dateAdded, quantity, expirDate);
        }
    }

    public static void editItem(int pantryID, int itemID, String name, Calendar dateAdded, int quantity){
        int index = getPantryIndex(pantryID);
        if (index != -1) {
            inventory.get(index).editItem(itemID, name, dateAdded, quantity);
        }
    }

    public static void saveRecipe(Recipe recipe){
        CookbookService.saveRecipe(recipe);
    }

    private static int getPantryIndex(int pantryID) {
        Iterator<Pantry> iterator = inventory.iterator();
        Pantry foundPantry = null;
        int count = 0;
        while(iterator.hasNext()) {
            Pantry list = iterator.next();
            if (list.getPantryID() == pantryID) {
                foundPantry = list;
                break;
            }
            count++;
        }
        if (foundPantry == null) {
            System.out.println("Pantry with ID: " + pantryID + " cannot be found");
            return -1;
        } else {
            return count;
        }
    }

    public static void main(String[] args) {
        createPantry(PantryType.KITCHEN_INVENTORY, "test1");
        Calendar testDate = Calendar.getInstance();
        addItem(0, "Milk", testDate, 2, testDate);
        System.out.println(inventory.get(0).getPantryName());
        System.out.println(inventory.get(0).getItem(0).getName());
        deletePantry(0);
        System.out.println("Pantry has been deleted");
    }
}