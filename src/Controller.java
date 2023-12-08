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
        int nextOptionValue = 3;
        Item item = Kitchen.retrievePantry(pantryID).getItem(idToEdit);
        System.out.println("What would you like to edit about this item?\n" +
        "1. Name: " + item.getName() +
        "\n2. Quantity: " + item.getQuantity());
        if (item.expirationDate != null) {
            System.out.println(nextOptionValue + ". Expiration Date: " + item.getExpirationDate().getTime());
            nextOptionValue++;
        }
        System.out.println("\n" + nextOptionValue + ". Cancel edit");

        int userInput = Navigation.getUserInputInt(1, nextOptionValue);
        String newName = item.getName();
        int newQuantity = item.getQuantity();
        Calendar newExpirationDate = item.getExpirationDate();

        switch (userInput) {
            case 1:
            case 2:
            case 3:
                switch (userInput) {
                    case 1:
                        System.out.println("Enter new name");
                        newName = Navigation.getUserInputString(true, 30);
                        Navigation.clearConsole();
                        System.out.println("Name updated!\n");
                        break;
                    case 2:
                        System.out.println("Enter new quantity");
                        newQuantity = Navigation.getUserInputInt(1, Integer.MAX_VALUE);
                        Navigation.clearConsole();
                        System.out.println("Quantity updated!\n");
                        break;
                    case 3:
                        if (newExpirationDate != null) {
                            System.out.println("Enter new date");
                            newExpirationDate = Navigation.getUserInputDate(false);
                            Navigation.clearConsole();
                            System.out.println("Expiration Date updated!\n");
                        } else {
                            Navigation.viewItemList(type, pantryID);
                        }
                        break;
                }

                Kitchen.retrievePantry(pantryID).editItem(item.getItemID(), newName, newExpirationDate, newQuantity);
                
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
        Notification editedNotification = ns.modifyNotification(idToEdit, editedMessage, editedDate);
        saveJson.update(editedNotification);
        
        Navigation.clearConsole();
        System.out.println("Notification Edited!");
        Navigation.bufferContinue();
        Navigation.printNotificationList(AccessContext.DISPLAY);
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