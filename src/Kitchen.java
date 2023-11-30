import java.util.ArrayList;
import java.util.Calendar;

enum PantryType {
    KITCHEN_INVENTORY,
    SHOPPING_CART
}

public class Kitchen 
{
    public ArrayList<Pantry> inventory = new ArrayList<Pantry>();
    public ArrayList<Pantry> shoppingCart = new ArrayList<Pantry>();
    public Cookbook myCookbook;

    public Pantry createPantry(PantryType type, String name){
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
}