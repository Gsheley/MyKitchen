import java.util.Calendar;

public class Controller {
    static Kitchen kitchen = new Kitchen();
    NotificationService ns = new NotificationService();
    static int numKitchens = 0;
    static int numCarts = 0;
    public static void main(String[] args) 
    {
        Navigation nv = new Navigation();
        nv.printHomePage();
    }

    public static void deletePantry(int id) 
    {
        Kitchen.deletePantry(id);
        numKitchens--;
    }

    public static void createPantry() 
    {
        Kitchen.createPantry(PantryType.KITCHEN_INVENTORY, Navigation.getUserInputString(true, 30));
        numKitchens++;
    }

    public static void createCart() 
    {
        Kitchen.createPantry(PantryType.SHOPPING_CART, Navigation.getUserInputString(true, 30));
        numCarts++;
    }

    public static void deleteCart(int id) 
    {
        Kitchen.deletePantry(id);
        numCarts--;
    }

    public static void addItem(int pantryID) 
    {
        System.out.println("Please enter the name of the Item: ");
        String name = Navigation.getUserInputString(true, 30);
        System.out.println("Please enter the quantity of the Item: ");
        int quantity = Navigation.getUserInputInt(1, Integer.MAX_VALUE);
        if (pantryID < 10000) {
            System.out.println("Please enter the expiration date of the Item: ");
            Calendar expirDate = Navigation.getUserInputDate(false);
            Kitchen.addItem(pantryID, name, Calendar.getInstance(), quantity, expirDate);
        } else {
            Kitchen.addItem(pantryID, name, Calendar.getInstance(), quantity);
        }
    }

    public static int getNumKitchens() {
        return numKitchens;
    }

    public static int getNumCarts() {
        return numCarts;
    }

    public static void setNumKitchens(int num) {
        numKitchens = num;
    }

    public static void setNumCarts(int num) {
        numCarts = num;
    }
}