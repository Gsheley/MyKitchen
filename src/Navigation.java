import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Iterator;

enum AccessContext { // the context in which a list is accessed. Different printing based on different contexts
    DISPLAY,
    CREATE,
    REMOVE
}

public class Navigation {
    NotificationService ns = new NotificationService();

    public void printHomePage() {
        Navigation.clearConsole();
        System.out.println("Welcome to MyKitchen!\n");
        if (ns.checkForNotifications()) {
            System.out.println("You have new notification(s)! View them in the notifications menu.");
        } else {
            System.out.println("You have no new notifications.");
        }
        System.out.println("\nSelect a navigation option below.\n" +
        "1. Pantries\n" +
        "2. Shopping Carts\n" +
        "3. Cookbook\n" +
        "4. Notifications\n" +
        "5. Exit MyKitchen\n");

        int userInput = Navigation.getUserInputInt(1, 5, Controller.universalScanner);
        switch (userInput) {
            case 1:
                printPantryPage();                     
            case 2:
                printShoppingCartPage();
            case 3:
                printCookbookPage();
            case 4:
                printNotificationPage();
            case 5:
                System.exit(0);
        }
    }

    public void printPantryPage() {
        Navigation.clearConsole();
        System.out.println("Pantry Menu\n");
        System.out.println("\nSelect a navigation option below.\n" +
        "1. View/Edit/Delete Contents of a Pantry\n" +
        "2. Create a New Pantry\n" +
        "3. Remove an Existing Pantry\n" +
        "\n" +
        "4. Go Back\n");

        int userInput = Navigation.getUserInputInt(1, 4, Controller.universalScanner);
        switch (userInput) {
            case 1:
                printPantryList(PantryType.KITCHEN_INVENTORY, AccessContext.DISPLAY); // print list of pantries                      
            case 2:
                Controller.createPantry(); // add a new pantry
            case 3:
                printPantryList(PantryType.KITCHEN_INVENTORY, AccessContext.REMOVE); // remove a pantry
            case 4:
                printHomePage();
        }
    }

    public void printShoppingCartPage() {
        Navigation.clearConsole();
        System.out.println("Shopping Cart Menu\n");
        System.out.println("\nSelect a navigation option below.\n" +
        "1. View/Edit/Delete Contents of a Shopping Cart\n" +
        "2. Create a New Shopping Cart\n" +
        "3. Remove an Existing Shopping Cart\n" +
        "\n" +
        "4. Go Back\n");

        int userInput = Navigation.getUserInputInt(1, 4, Controller.universalScanner);
        switch (userInput) {
            case 1:
                // print list of shopping carts                      
            case 2:
                // add new shopping cart
            case 3:
                // remove a shopping cart
            case 4:
                printHomePage();
        }
    }

    public void printCookbookPage() {
        Navigation.clearConsole();
        System.out.println("Cookbook Menu\n");
        System.out.println("\nSelect a navigation option below.\n" +
        "1. View Recipes in Cookbook\n" +
        "2. Find New Recipes\n" +
        "\n" +
        "3. Go Back\n");

        int userInput = Navigation.getUserInputInt(1, 3, Controller.universalScanner);
        switch (userInput) {
            case 1:
                // print list of recipes in cookbook                       
            case 2:
                // print recipe search page
            case 3:
                printHomePage();
        }
    }

    public void printNotificationPage() {
        Navigation.clearConsole();
        System.out.println("Notificiation Menu\n");
        System.out.println("\nSelect a navigation option below.\n" +
        "1. View/Edit/Delete Upcoming Notifications\n" +
        "2. Create a New Notification\n" +
        "3. Remove an Existing Shopping Cart\n" +
        "\n" +
        "4. Go Back\n");

        int userInput = Navigation.getUserInputInt(1, 4, Controller.universalScanner);
        switch (userInput) {
            case 1:
                // print notification list                         
            case 2:
                // add new notification
            case 3:
                // remove a notification
            case 4:
                printHomePage();
        }
    }

    public void printRecipe(int recipeID, boolean fromSearch) {
        Navigation.clearConsole();
        ArrayList<Recipe> recipeList = Cookbook.recipes;
        Iterator<Recipe> iterator = recipeList.iterator();
        
        while (iterator.hasNext()) {
            Recipe r = iterator.next();
            if (r.getID() == recipeID) {
                r.printRecipe();
            }
        }

        System.out.println("\nSelect a navigation option below.");
        if (fromSearch) {
            System.out.println("1. Save this recipe to your Cookbook\n" +
            "\n" +
            "2. Go Back\n");
        } else {
            System.out.println("1. Remove this recipe from your Cookbook\n" +
            "\n" +
            "2. Go Back\n");
        }

        int userInput = Navigation.getUserInputInt(1, 2, Controller.universalScanner);
        switch (userInput) {
            case 1:
                if (fromSearch) {
                    // save recipe to cookbook
                } else {
                    // remove recipe from cookbook
                }
            case 2:
                printCookbookPage();
        }
    }

    public void printItem(int pantryID, int itemID) {
        Navigation.clearConsole();
        Pantry pantry = Kitchen.retrievePantry(pantryID);
        Iterator<Item> iterator = pantry.items.iterator();

        while (iterator.hasNext()) {
            Item i = iterator.next();

            if (i.getItemID() == itemID) {
                System.out.println("Item ID: " + i.getItemID() +
                "\nItem Name: " + i.getName() + 
                "\nDate Added: " + i.getDateAdded().getTime() +
                "\nQuantity: " + i.getQuantity());
                if (i.getExpirationDate() != null) {
                    System.out.println("Expiration Date: " + i.getExpirationDate().getTime());
                }
            } else {
                System.out.println("Could not find an item of the specified ID inside the specified list.");
            }
        }

        System.out.println("\nSelect a navigation option below.\n" +
            "1. Edit this Item\n" +
            "2. Delete this Item\n" +
            "\n" +
            "3. Go Back\n");

        int userInput = Navigation.getUserInputInt(1, 3, Controller.universalScanner);
        switch (userInput) {
            case 1:
                                    
            case 2:

            case 3:
                if (pantryID < PantryService.getRange()) {
                    printPantryPage();
                } else {
                    printShoppingCartPage();
                }
        }
    }

    public void printSearchResults(String query, ArrayList<Object> list) {
        Navigation.clearConsole();
        System.out.println("Search Results from query: " + query + "\n");
        ArrayList<Object> searchResults = Search.search(query, list);

        if (searchResults.isEmpty()) {
            System.out.println("No Results Found.");
        } else if(list.get(0) instanceof Item) {
            ArrayList<Item> pantryResults = new ArrayList<Item>();

            for (Object obj : searchResults) {
                // Typecasting each Object to Item and adding it to the new ArrayList
                Item item = (Item) obj;
                pantryResults.add(item);
            }
            for (int i = 0; i < pantryResults.size(); i++) {
                System.out.println(i + 1 + ". " + pantryResults.get(i).getName());
            }
        } else if (list.get(0) instanceof Recipe) {
            ArrayList<Recipe> cookbookResults = new ArrayList<Recipe>();

            for (Object obj : searchResults) {
                // Typecasting each Object to Recipe and adding it to the new ArrayList
                Recipe recipe = (Recipe) obj;
                cookbookResults.add(recipe);
            }
            for (int i = 0; i <= cookbookResults.size(); i++) {
                System.out.println(i + 1 + ". " + cookbookResults.get(i).getName());
            }
        }
    }

    public void printPantryList(PantryType type, AccessContext context) {
        Navigation.clearConsole();
        switch (context) {
            case DISPLAY:
            case REMOVE:
                System.out.println("Choose a " + type.name().toLowerCase() + " to " + context.name().toLowerCase() + ".");
            default: // if somehow an invalid context is given
                printHomePage();
        }

        for (int i = 0; i < Kitchen.inventory.size(); i++) {
            System.out.println(i + 1 + ". " + Kitchen.inventory.get(i).getPantryName());
        }
    }

    // Clears the screen for printing new menus
    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static int getUserInputInt(int min, int max, Scanner scanInt) {
        int userInput;

        do {
            System.out.print("Your input: ");
            while (!scanInt.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer.");
                System.out.print("Your input: ");
                scanInt.next();
            }
            userInput = scanInt.nextInt();

            if (userInput < min || userInput > max) {
                System.out.printf("Input out of range. Please enter an integer between %d and %d.\n", min, max);
            }
        } while (userInput < min || userInput > max);

        return userInput;
    }

    public static String getUserInputString(boolean allowSpaces, int maxLength, Scanner scanString) {
        String validChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!,.?#()";
        if (allowSpaces) {
            validChars += " ";
        }

        System.out.print("Your Input: ");
        String userInput;
        boolean isValid;

        do {
            userInput = scanString.nextLine();

            isValid = true;
            for (char c : userInput.toCharArray()) {
                if (validChars.indexOf(c) == -1 || userInput.length() > maxLength) {
                    isValid = false;
                    break;
                }
            }

            if (!isValid) {
                System.out.println("Invalid input. Your input may contain only alphanumeric"
                                   + (allowSpaces ? " and space" : "") + " characters. Also accepted: !,.?#(). Input must also be less than " + maxLength + " characters.");
            }
        } while (!isValid);

        scanString.close();
        return userInput;
    }

    public static Calendar getUserInputDate(boolean includeHoursMinutes) {
        Scanner scanDate = new Scanner(System.in);
        
        System.out.println("Enter year.");
        int year = getUserInputInt(2000,3000,scanDate);

        System.out.println("Enter month. (1-12)");
        int month = getUserInputInt(1,12,scanDate);

        int numDaysInMonth = getNumDaysInMonth(month); // calculate day range for selected month
        System.out.println("Enter day of the month. (The month you selected has " + numDaysInMonth + " days.)");
        int day = getUserInputInt(1, numDaysInMonth,scanDate);

        int hour = 0;
        int minute = 0;
        if (includeHoursMinutes) {
            System.out.println("Enter hour. (0-23)");
            hour = getUserInputInt(0,23,scanDate);

            System.out.println("Enter minute. (0-59)");
            minute = getUserInputInt(0,59,scanDate);
        }

        // Create a Calendar instance and set the provided values
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1); // Calendar months are zero-based (January is 0)
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        scanDate.close();

        return calendar;
    }

    private static int getNumDaysInMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month. Month should be between 1 and 12.");
        }

        // Get the maximum number of days for the given month
        YearMonth yearMonth = YearMonth.of(YearMonth.now().getYear(), month);
        return yearMonth.lengthOfMonth();
    }

    public static void main(String args[]) {
        //Navigation nv = new Navigation();
        // Search test
        /* 
        ArrayList<Object> items = new ArrayList<Object>();
        Item testItem1 = new Item(1,"test", Calendar.getInstance(),12);
        Item testItem2 = new Item(2,"apple", Calendar.getInstance(),12);
        Item testItem3 = new Item(3,"apple guy", Calendar.getInstance(),12);
        Item testItem4 = new Item(4,"pear", Calendar.getInstance(),12);
        items.add(testItem1);
        items.add(testItem2);
        items.add(testItem3);
        items.add(testItem4);

        nv.printSearchResults("ple", items);
        */

        // Input test
        //Calendar testCal = getUserInputDate(true);
        //System.out.println(testCal.getTime());

        //String testString = getUserInputString(true);
        //System.out.println(testString);

        int testInt = getUserInputInt(12,100, Controller.universalScanner);
        System.out.println(testInt);
    }
}
