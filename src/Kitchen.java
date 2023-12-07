import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

enum PantryType {
    PANTRY,
    SHOPPING_CART
}

public class Kitchen 
{
    public static ArrayList<Pantry> inventory = new ArrayList<Pantry>();

    public static Pantry createPantry(PantryType type, String name){
        Pantry newPantry = PantryService.createPantry(type, name);
        inventory.add(newPantry);
        return newPantry;
    }

    public static Pantry retrievePantry(int pantryID){
        //checks each item in the items array list
        int index = getPantryIndex(pantryID);
        if (index == -1) {
            return null; 
        } else {
            return inventory.get(index);
        }
    }

    public static Pantry deletePantry(int pantryID) {
        Pantry deletedPantry = null;
        int index = getPantryIndex(pantryID);
        if (index != -1) {
            deletedPantry = inventory.get(index);
            inventory.remove(index);
        }
        return deletedPantry;
    }

    public static Pantry addItem(int pantryID, String name, Calendar dateAdded, int quantity){
        int index = getPantryIndex(pantryID);
        if (index != -1) {
            inventory.get(index).addItem(name, dateAdded, quantity);
        }
        return inventory.get(index);
    }

    public static Pantry addItem(int pantryID, String name, Calendar dateAdded, int quantity, Calendar expirDate){
        int index = getPantryIndex(pantryID);
        if (index != -1) {
            inventory.get(index).addItem(name, dateAdded, quantity, expirDate);
        }
        return inventory.get(index);
    }

    public static Pantry editItem(int pantryID, int itemID, String name, Calendar dateAdded, int quantity, Calendar expirDate){
        int index = getPantryIndex(pantryID);
        if (index != -1) {
            inventory.get(index).editItem(itemID, name, dateAdded, quantity, expirDate);
        }
        return inventory.get(index);
    }

    public static Pantry editItem(int pantryID, int itemID, String name, Calendar dateAdded, int quantity){
        int index = getPantryIndex(pantryID);
        if (index != -1) {
            inventory.get(index).editItem(itemID, name, dateAdded, quantity);
        }
        return inventory.get(index);
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
        createPantry(PantryType.PANTRY, "test1");
        Calendar testDate = Calendar.getInstance();
        addItem(0, "Milk", testDate, 2, testDate);
        System.out.println(inventory.get(0).getPantryName());
        System.out.println(inventory.get(0).getItem(0).getName());
        deletePantry(0);
        System.out.println("Pantry has been deleted");
    }
}