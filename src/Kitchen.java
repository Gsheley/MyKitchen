import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

enum PantryType {
    KITCHEN_INVENTORY,
    SHOPPING_CART
}

public class Kitchen 
{
    public ArrayList<Pantry> inventory = new ArrayList<Pantry>();
    public ArrayList<Pantry> shoppingCart = new ArrayList<Pantry>();
    public Cookbook myCookbook;

    public static Pantry createPantry(PantryType type, String name){
        // Call shared scanner for name here
        return PantryService.createPantry(type, name);
    }

    public Pantry retrievePantry(int pantryID){
        //checks each recipe in the recipes array list
        Pantry foundPantry = null;
        if (pantryID < PantryService.range) {
            if (pantryID < PantryService.nextPantrykiID) {
                foundPantry = inventory.get(pantryID);
            }
        } else {
            if (pantryID < PantryService.nextPantryscID) {
                foundPantry = shoppingCart.get(pantryID);
            }
        }
        return foundPantry;
    }

    public void addItem(String name, Calendar dateAdded, int quantity){

    }

    public void addItem(String name, Calendar dateAdded, int quantity, Calendar expirDate){

    }

    public void editItem(int itemID, String name, Calendar dateAdded, int quantity, Calendar expirDate){

    }

    public void editItem(int itemID, String name, Calendar dateAdded, int quantity){

    }

    public void saveRecipe(Recipe recipe){
        CookbookService.saveRecipe(recipe);
    }

    public Pantry getPantryByID(int id) {
        //private Iterator<Pantry> iterator = pantries.iterator();
        return null;
    }
}