import java.util.ArrayList;
import java.util.Calendar;

enum PantryType {
    KITCHEN_INVENTORY,
    SHOPPING_CART
}

public class Kitchen 
{
    private static ArrayList<Pantry> inventory = new ArrayList<Pantry>();
    private static ArrayList<Pantry> shoppingCart = new ArrayList<Pantry>();
    public Cookbook myCookbook;

    public static void createPantry(PantryType type, String name){
        // Call shared scanner for name here
        if (type == PantryType.KITCHEN_INVENTORY) { 
            inventory.add(PantryService.createPantry(type, name));
        } else {
            shoppingCart.add(PantryService.createPantry(type, name));
        }
    }

    public static Pantry retrievePantry(int pantryID){
        //checks each item in the items array list
        Pantry foundPantry = null;
        if (pantryID < PantryService.getRange()) {
            if (pantryID < PantryService.getNextKitchenInventoryID()) {
                foundPantry = inventory.get(pantryID);
            }
        } else {
            if (pantryID < PantryService.getNextShoppingCartID()) {
                foundPantry = shoppingCart.get(pantryID);
            }
        }
        return foundPantry;
    }

    public static void deletePantry(int pantryID) {
        inventory.remove(pantryID);
    }

    public static void deleteCart(int cartID) {
        shoppingCart.remove(cartID);
    }

    public static void addItem(int pantryID, String name, Calendar dateAdded, int quantity){
        shoppingCart.get(pantryID).addItem(name, dateAdded, quantity);
    }

    public static void addItem(int pantryID, String name, Calendar dateAdded, int quantity, Calendar expirDate){
        inventory.get(pantryID).addItem(name, dateAdded, quantity, expirDate);
    }

    public static void editItem(int pantryID, int itemID, String name, Calendar dateAdded, int quantity, Calendar expirDate){
        inventory.get(pantryID).editItem(itemID, name, dateAdded, quantity, expirDate);
    }

    public static void editItem(int pantryID, int itemID, String name, Calendar dateAdded, int quantity){
        inventory.get(pantryID).editItem(itemID, name, dateAdded, quantity);
    }

    public static void saveRecipe(Recipe recipe){
        CookbookService.saveRecipe(recipe);
    }

    public Pantry getPantryByID(int id) {
        //private Iterator<Pantry> iterator = pantries.iterator();
        return null;
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