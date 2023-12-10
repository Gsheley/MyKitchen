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

    public Pantry createPantry(PantryType type, String name){
        Pantry newPantry = PantryService.createPantry(type, name);
        inventory.add(newPantry);
        return newPantry;
    }

    public Pantry retrievePantry(int pantryID){
        //checks each item in the items array list
        int index = getPantryIndex(pantryID);
        if (index == -1) {
            return null; 
        } else {
            return inventory.get(index);
        }
    }

    public Pantry deletePantry(int pantryID) {
        Pantry deletedPantry = null;
        Iterator<Pantry> iterator = inventory.iterator();
        while (iterator.hasNext()) {
            Pantry pantry = iterator.next();
            if (pantry.getPantryID() == pantryID) {
                deletedPantry = pantry;
                iterator.remove();
            }
        }
        return deletedPantry;
    }

    public Pantry addItem(int pantryID, String name, Calendar dateAdded, int quantity){
        int index = getPantryIndex(pantryID);
        if (index != -1) {
            inventory.get(index).addItem(name, dateAdded, quantity);
        }
        return inventory.get(index);
    }

    public Pantry addItem(int pantryID, String name, Calendar dateAdded, int quantity, Calendar expirDate, int lowQuantityNotifThreshold) {
        int index = getPantryIndex(pantryID);
        if (index != -1) {
            inventory.get(index).addItem(name, dateAdded, quantity, expirDate, lowQuantityNotifThreshold);
        }
        return inventory.get(index);
    }

    public Pantry editItem(int pantryID, int itemID, String name, Calendar dateAdded, int quantity, Calendar expirDate, int lowQuantityNotifThreshold) {
        int index = getPantryIndex(pantryID);
        if (index != -1) {
            inventory.get(index).editItem(itemID, name, dateAdded, quantity, expirDate, lowQuantityNotifThreshold);
        }
        return inventory.get(index);
    }

    public Pantry editItem(int pantryID, int itemID, String name, Calendar dateAdded, int quantity){
        int index = getPantryIndex(pantryID);
        if (index != -1) {
            inventory.get(index).editItem(itemID, name, dateAdded, quantity);
        }
        return inventory.get(index);
    }

    public void saveRecipe(Recipe recipe){
        CookbookService.saveRecipe(recipe);
    }

    private int getPantryIndex(int pantryID) {
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

    public ArrayList<Recipe> sortRecipeNames() {
        return CookbookService.sortRecipeNames();
    }
}