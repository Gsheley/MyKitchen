import java.util.Calendar;
import java.util.Scanner;

public class Controller {
    NotificationService ns = new NotificationService();
    private int numPantries = 0;
    private int numCarts = 0;
    static Scanner universalScanner = new Scanner(System.in);
    static Navigation nv = new Navigation();
    static SaveAppData saveJson = new JsonData();
    Kitchen kitchen = new Kitchen();
    
    public static void main(String[] args) 
    {   
        saveJson.open();
        nv.printHomePage();
    }

    // PANTRIES & SHOPPING CARTS //

    public void createPantry(String pantryName) 
    {
        Pantry newPantry = kitchen.createPantry(PantryType.PANTRY, pantryName);
        numPantries++;
        saveJson.create(newPantry);
    }

    public void deletePantry(int id) 
    {
        Pantry deletedPantry = kitchen.deletePantry(id);
        numPantries--;

        saveJson.delete(deletedPantry);

        Navigation.clearConsole();
        System.out.println("Pantry removed!\n");
        Navigation.bufferContinue();
        nv.printPantryPage();
    }

    public void createCart(String cartName) 
    {
        Pantry newPantry = kitchen.createPantry(PantryType.SHOPPING_CART, cartName);
        numCarts++;
        saveJson.create(newPantry);
    }

    public void deleteCart(int id) 
    {
        Pantry deletedPantry = kitchen.deletePantry(id);
        numCarts--;

        saveJson.delete(deletedPantry);

        Navigation.clearConsole();
        System.out.println("Shopping Cart removed!\n");
        Navigation.bufferContinue();
        nv.printShoppingCartPage();
    }

    // ITEMS //

    public void addItem(PantryType type, int pantryID) 
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
                    System.out.println("Would you like to add a Threshold for this item?\n" +
                    "1. Yes\n" +
                    "2. No");
                    int userInput2 = Navigation.getUserInputInt(1, 2);
                    
                    switch(userInput2) {
                    case 1: 
                        Navigation.clearConsole();
                        System.out.println("Please enter the desired threshold:\n");
                        int threshold = Navigation.getUserInputInt(1, 1000);
                        //In Json file add threshold to item 

                        updatedPantry = kitchen.addItem(pantryID, name, Calendar.getInstance(), quantity, expirDate);
                        break;
                    case 2:
                        updatedPantry = kitchen.addItem(pantryID, name, Calendar.getInstance(), quantity, expirDate);
                        break;
                    }
                case 2:
                    updatedPantry = kitchen.addItem(pantryID, name, Calendar.getInstance(), quantity, null);
                    break;
            }
        } else {
            updatedPantry = kitchen.addItem(pantryID, name, Calendar.getInstance(), quantity);
        }

        saveJson.update(updatedPantry);

        Navigation.clearConsole();
        System.out.println("Item added!\n");
        Navigation.bufferContinue();
        nv.viewItemList(type,pantryID);
    }

    public void editItem(int pantryID, int idToEdit, PantryType type) {
        Navigation.clearConsole();
        int nextOptionValue = 3;
        Item item = kitchen.retrievePantry(pantryID).getItem(idToEdit);
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
                            nv.viewItemList(type, pantryID);
                        }
                        break;
                }

                kitchen.retrievePantry(pantryID).editItem(item.getItemID(), newName, newExpirationDate, newQuantity);
                
                Navigation.bufferContinue();
            case 4:
                nv.viewItemList(type, pantryID);
                break;
        }

        saveJson.update(kitchen.retrievePantry(pantryID));
    }

    public void deleteItem(int pantryID, int idToRemove) {
        Pantry pantry = kitchen.retrievePantry(pantryID);
        pantry.deleteItem(idToRemove);

        Navigation.clearConsole();
        System.out.println("Item Removed!");
        Navigation.bufferContinue();

        saveJson.update(pantry);
    }

    // NOTIFICATIONS //

    public void addNotification(Calendar dateOfNotif, String notifMessage) {
        Notification newNotification = ns.addNotification(dateOfNotif, notifMessage);
        saveJson.create(newNotification);
    }

    public void editNotification(int idToEdit, String editedMessage, Calendar editedDate) {
        Notification editedNotification = ns.modifyNotification(idToEdit, editedMessage, editedDate);
        saveJson.update(editedNotification);
        
        Navigation.clearConsole();
        System.out.println("Notification Edited!");
        Navigation.bufferContinue();
        nv.printNotificationList(AccessContext.DISPLAY);
    }

    public void deleteNotification(int idToDelete) {
        Notification removedNotification = ns.removeNotification(idToDelete);
        saveJson.delete(removedNotification);

        Navigation.clearConsole();
        System.out.println("Notification Removed!");
        Navigation.bufferContinue();
        nv.printNotificationList(AccessContext.DISPLAY);
    }

    // RECIPES //

    // nothing actually needs to go here

    // SETTERS & GETTERS //

    public int getNumPantries() {
        return this.numPantries;
    }

    public int getNumCarts() {
        return this.numCarts;
    }

    public void setNumPantries(int num) {
        this.numPantries = num;
    }

    public void setNumCarts(int num) {
        this.numCarts = num;
    }

    public NotificationService getNotificationService() {
        return this.ns;
    }

    public void setNotificationService(NotificationService obj) {
        this.ns = obj;
    }

    public void sortRecipeNames() {
        saveJson.update(kitchen.sortRecipeNames());
    }
}