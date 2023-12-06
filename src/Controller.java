import java.util.Calendar;
import java.util.Scanner;

public class Controller {
    static NotificationService ns = new NotificationService();
    static int numKitchens = 0;
    static int numCarts = 0;
    static Scanner universalScanner = new Scanner(System.in);
    Navigation nv = new Navigation();
    
    public static void main(String[] args) 
    {   
        Kitchen.createPantry(PantryType.PANTRY, "My Pantry");
        Kitchen.inventory.get(0).items.add(new Item(1,"Apple",Calendar.getInstance(),12,Calendar.getInstance()));
        Navigation.printHomePage();
    }

    public static void deletePantry(int id) 
    {
        Kitchen.deletePantry(id);
        numKitchens--;
    }

    public static void createPantry() 
    {
        System.out.println("Please enter the name of the new Pantry: ");
        String pantryName = Navigation.getUserInputString(true, 30);
        Kitchen.createPantry(PantryType.PANTRY, pantryName);
        numKitchens++;
    }

    public static void createCart() 
    {
        System.out.println("Please enter the name of the new Shopping Cart: ");
        String cartName = Navigation.getUserInputString(true, 30);
        Kitchen.createPantry(PantryType.SHOPPING_CART, cartName);
        numCarts++;
    }

    public static void deleteCart(int id) 
    {
        Kitchen.deletePantry(id);
        numCarts--;
    }

    public static void addItem(PantryType type, int pantryID) 
    {
        System.out.println("Please enter the name of the Item: ");
        String name = Navigation.getUserInputString(true, 30);
        System.out.println("Please enter the quantity of the Item: ");
        int quantity = Navigation.getUserInputInt(1, Integer.MAX_VALUE);
        if (pantryID < PantryService.getRange()) {
            System.out.println("Would you like to add an expiration date to your Item?\n" + 
            "1. Yes\n" +
            "2. No");
            int userInput = Navigation.getUserInputInt(1, 2);
            switch (userInput) {
                case 1:
                    Navigation.clearConsole();
                    System.out.println("Please enter the expiration date of the Item: ");
                    Calendar expirDate = Navigation.getUserInputDate(false);
                    Kitchen.addItem(pantryID, name, Calendar.getInstance(), quantity, expirDate);
                case 2:
                    Kitchen.addItem(pantryID, name, Calendar.getInstance(), quantity, null);
            }
        } else {
            Kitchen.addItem(pantryID, name, Calendar.getInstance(), quantity);
        }

        Navigation.clearConsole();
        System.out.println("Item added!\nInput anything to continue.");
        Navigation.getUserInputString(true, 1000);
        Navigation.viewItemList(type,pantryID);
        
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

    public static NotificationService getNotificationService() {
        return ns;
    }

    public static void setNotificationService(NotificationService obj) {
        ns = obj;
    }
}