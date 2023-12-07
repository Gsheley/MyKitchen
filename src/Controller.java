import java.util.Calendar;
import java.util.Scanner;

public class Controller {
    static NotificationService ns = new NotificationService();
    static int numPantries = 0;
    static int numCarts = 0;
    static Scanner universalScanner = new Scanner(System.in);
    Navigation nv = new Navigation();
    
    public static void main(String[] args) 
    {   
        Kitchen.createPantry(PantryType.PANTRY, "My Pantry");
        numPantries++;
        Kitchen.inventory.get(0).items.add(new Item(1,"Apple",Calendar.getInstance(),12,Calendar.getInstance()));
        Navigation.printHomePage();
    }

    public static void deletePantry(int id) 
    {
        Kitchen.deletePantry(id);
        numPantries--;

        Navigation.clearConsole();
        System.out.println("Pantry removed!\n\n1. Go Back");
        Navigation.getUserInputInt(1, 1);
        Navigation.printPantryPage();
    }

    public static void createPantry(String pantryName) 
    {
        Kitchen.createPantry(PantryType.PANTRY, pantryName);
        numPantries++;
    }

    public static void createCart(String cartName) 
    {
        Kitchen.createPantry(PantryType.SHOPPING_CART, cartName);
        numCarts++;
    }

    public static void deleteCart(int id) 
    {
        Kitchen.deletePantry(id);
        numCarts--;
        Navigation.clearConsole();
        System.out.println("Shopping Cart removed!\n\n1. Go Back");
        Navigation.getUserInputInt(1, 1);
        Navigation.printShoppingCartPage();
    }

    public static void addItem(PantryType type, int pantryID) 
    {   
        Navigation.clearConsole();
        System.out.println("Please enter the name of the Item");
        String name = Navigation.getUserInputString(true, 30);
        System.out.println("Please enter the quantity of the Item");
        int quantity = Navigation.getUserInputInt(1, Integer.MAX_VALUE);
        if (pantryID < PantryService.getRange()) {
            System.out.println("Would you like to add an expiration date to your Item?\n" + 
            "1. Yes\n" +
            "2. No");
            int userInput = Navigation.getUserInputInt(1, 2);
            switch (userInput) {
                case 1:
                    Navigation.clearConsole();
                    System.out.println("Please enter the expiration date of the Item");
                    Calendar expirDate = Navigation.getUserInputDate(false);
                    Kitchen.addItem(pantryID, name, Calendar.getInstance(), quantity, expirDate);
                    break;
                case 2:
                    Kitchen.addItem(pantryID, name, Calendar.getInstance(), quantity, null);
                    break;
            }
        } else {
            Kitchen.addItem(pantryID, name, Calendar.getInstance(), quantity);
        }

        Navigation.clearConsole();
        System.out.println("Item added!\n1. Go Back");
        Navigation.getUserInputInt(1, 1);
        Navigation.viewItemList(type,pantryID);
    }

    public static int getNumPantries() {
        return numPantries;
    }

    public static int getNumCarts() {
        return numCarts;
    }

    public static void setNumPantries(int num) {
        numPantries = num;
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