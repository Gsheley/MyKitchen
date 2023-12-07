import java.util.Calendar;
import java.util.Scanner;

public class Controller {
    static NotificationService ns = new NotificationService();
    static int numPantries = 0;
    static int numCarts = 0;
    static Scanner universalScanner = new Scanner(System.in);
    Navigation nv = new Navigation();
    static SaveAppData saveJson = new JsonData();
    
    public static void main(String[] args) 
    {   
        saveJson.open();
        /*Controller.createPantry("New Test Pantry");
        Kitchen.addItem(0,"Apple",Calendar.getInstance(),12,Calendar.getInstance());
        Kitchen.addItem(0,"Banana",Calendar.getInstance(),9,Calendar.getInstance());
        Kitchen.addItem(0,"Pear",Calendar.getInstance(),6,Calendar.getInstance());
        Kitchen.addItem(0,"Lettuce",Calendar.getInstance(),4,Calendar.getInstance());
        Kitchen.addItem(0,"Chips",Calendar.getInstance(),2212,Calendar.getInstance());*/
        Navigation.printHomePage();
    }

    // PANTRIES & SHOPPING CARTS //

    public static void createPantry(String pantryName) 
    {
        Pantry newPantry = Kitchen.createPantry(PantryType.PANTRY, pantryName);
        numPantries++;
        saveJson.create(newPantry);
    }

    public static void deletePantry(int id) 
    {
        Pantry deletedPantry = Kitchen.deletePantry(id);
        numPantries--;

        saveJson.delete(deletedPantry);

        Navigation.clearConsole();
        System.out.println("Pantry removed!\n");
        Navigation.bufferContinue();
        Navigation.printPantryPage();
    }

    public static void createCart(String cartName) 
    {
        Pantry newPantry = Kitchen.createPantry(PantryType.SHOPPING_CART, cartName);
        numCarts++;
        saveJson.create(newPantry);
    }

    public static void deleteCart(int id) 
    {
        Pantry deletedPantry = Kitchen.deletePantry(id);
        numCarts--;

        saveJson.delete(deletedPantry);

        Navigation.clearConsole();
        System.out.println("Shopping Cart removed!\n");
        Navigation.bufferContinue();
        Navigation.printShoppingCartPage();
    }

    // ITEMS //

    public static void addItem(PantryType type, int pantryID) 
    {   
        Pantry updatedPantry = null;
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
                    updatedPantry = Kitchen.addItem(pantryID, name, Calendar.getInstance(), quantity, expirDate);
                    break;
                case 2:
                    updatedPantry = Kitchen.addItem(pantryID, name, Calendar.getInstance(), quantity, null);
                    break;
            }
        } else {
            updatedPantry = Kitchen.addItem(pantryID, name, Calendar.getInstance(), quantity);
        }
        saveJson.update(updatedPantry);

        Navigation.clearConsole();
        System.out.println("Item added!\n");
        Navigation.bufferContinue();
        Navigation.viewItemList(type,pantryID);
    }

    public static void editItem(int pantryID, int idToEdit, PantryType type) {
        Navigation.clearConsole();
        Item item = Kitchen.retrievePantry(pantryID).getItem(idToEdit);
        System.out.println("What would you like to edit about this item?\n" +
        "1. Name: " + item.getName() +
        "\n2. Quantity: " + item.getQuantity());
        if (item.expirationDate != null) {
            System.out.println("3. Expiration Date: " + item.getExpirationDate().getTime());
        }
        System.out.println("\n4. Cancel edit");

        int userInput = Navigation.getUserInputInt(1, 4);

        switch (userInput) {
            case 1:
            case 2:
            case 3:
                switch (userInput) {
                    case 1:
                        System.out.println("Enter new name");
                        String userString = Navigation.getUserInputString(true, 30);
                        Kitchen.retrievePantry(pantryID).getItem(idToEdit).setName(userString);
                        System.out.println("Name updated!\n");
                        break;
                    case 2:
                        System.out.println("Enter new quantity");
                        int userInt = Navigation.getUserInputInt(1, Integer.MAX_VALUE);
                        Kitchen.retrievePantry(pantryID).getItem(idToEdit).setQuantity(userInt);
                        System.out.println("Quantity updated!\n");
                        break;
                    case 3:
                        System.out.println("Enter new date");
                        Calendar userDate = Navigation.getUserInputDate(false);
                        Kitchen.retrievePantry(pantryID).getItem(idToEdit).setExpirationDate(userDate);
                        Navigation.clearConsole();
                        System.out.println("Expiration Date updated!\n");
                        break;
                }
                
                Navigation.bufferContinue();
            case 4:
                Navigation.viewItemList(type, pantryID);
                break;
        }

        saveJson.update(Kitchen.retrievePantry(pantryID));
    }

    public static void deleteItem(int pantryID, int idToRemove) {
        // TODO
        Pantry pantry = Kitchen.retrievePantry(pantryID);
        pantry.deleteItem(idToRemove);

        Navigation.clearConsole();
        System.out.println("Item Removed!");
        Navigation.bufferContinue();

        saveJson.update(pantry);
    }

    // NOTIFICATIONS //

    public static void addNotification(Calendar dateOfNotif, String notifMessage) {
        Notification newNotification = ns.addNotification(dateOfNotif, notifMessage);
        saveJson.create(newNotification);
    }

    public static void editNotification(int idToEdit, String editedMessage, Calendar editedDate) {
        // TODO 
        
        ns.modifyNotification(idToEdit, editedMessage, editedDate);
    }

    public static void deleteNotification(int idToDelete) {
        Notification removedNotification = ns.removeNotification(idToDelete);
        saveJson.delete(removedNotification);

        Navigation.clearConsole();
        System.out.println("Notification Removed!");
        Navigation.bufferContinue();
        Navigation.printNotificationList(AccessContext.DISPLAY);
    }

    // RECIPES //

    // not sure if anything needs to go here yet

    // SETTERS & GETTERS //

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