import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.NoSuchElementException;

enum AccessContext { // the context in which a list is accessed. Different printing based on different contexts
    DISPLAY,
    REMOVE
}

public class Navigation {
    static NotificationService ns = new NotificationService();

    public static void printHomePage() {
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

        int userInput = Navigation.getUserInputInt(1, 5);
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

    public static void printPantryPage() {
        Navigation.clearConsole();
        System.out.println("Pantry Menu\n");
        System.out.println("\nSelect a navigation option below.\n" +
        "1. View/Edit/Delete Contents of a Pantry\n" +
        "2. Create a New Pantry\n" +
        "3. Remove an Existing Pantry\n" +
        "\n" +
        "4. Go Back\n");

        int userInput = Navigation.getUserInputInt(1, 4);
        switch (userInput) {
            case 1:
                printPantryList(PantryType.PANTRY, AccessContext.DISPLAY); // print list of pantries                      
            case 2:
                Controller.createPantry(); // add a new pantry
            case 3:
                printPantryList(PantryType.PANTRY, AccessContext.REMOVE); // remove a pantry
            case 4:
                printHomePage();
        }
    }

    public static void printShoppingCartPage() {
        Navigation.clearConsole();
        System.out.println("Shopping Cart Menu\n");
        System.out.println("\nSelect a navigation option below.\n" +
        "1. View/Edit/Delete Contents of a Shopping Cart\n" +
        "2. Create a New Shopping Cart\n" +
        "3. Remove an Existing Shopping Cart\n" +
        "\n" +
        "4. Go Back\n");

        int userInput = Navigation.getUserInputInt(1, 4);
        switch (userInput) {
            case 1:
                printPantryList(PantryType.SHOPPING_CART, AccessContext.DISPLAY); // print list of shopping carts                      
            case 2:
                Controller.createCart(); // add new shopping cart
            case 3:
                printPantryList(PantryType.SHOPPING_CART, AccessContext.REMOVE); // remove a shopping cart
            case 4:
                printHomePage();
        }
    }

    public static void printCookbookPage() {
        Navigation.clearConsole();
        System.out.println("Cookbook Menu\n");
        System.out.println("\nSelect a navigation option below.\n" +
        "1. View Recipes in Cookbook\n" +
        "2. Find New Recipes\n" +
        "\n" +
        "3. Go Back\n");

        int userInput = Navigation.getUserInputInt(1, 3);
        switch (userInput) {
            case 1:
                // print list of recipes in cookbook                       
            case 2:
                // print recipe search page
            case 3:
                printHomePage();
        }
    }

    public static void printNotificationPage() {
        Navigation.clearConsole();
        System.out.println("Notificiation Menu\n");
        System.out.println("\nSelect a navigation option below.\n" +
        "1. View/Edit/Delete Upcoming Notifications\n" +
        "2. Create a New Notification\n" +
        "3. Remove an Existing Shopping Cart\n" +
        "\n" +
        "4. Go Back\n");

        int userInput = Navigation.getUserInputInt(1, 4);
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

    public static void printRecipe(int recipeID, boolean fromSearch) {
        Navigation.clearConsole();
        ArrayList<Recipe> recipeList = Cookbook.recipes;
        Iterator<Recipe> iterator = recipeList.iterator();
        Recipe recipe = new Recipe(null);
        
        while (iterator.hasNext()) {
            Recipe r = iterator.next();
            if (r.getID() == recipeID) {
                recipe = r;
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

        int userInput = Navigation.getUserInputInt(1, 2);
        switch (userInput) {
            case 1:
                if (fromSearch) {
                    Cookbook.saveRecipe(recipe);
                } else {
                    Cookbook.removeRecipe(recipe);
                }
            case 2:
                printCookbookPage();
        }
    }

    public static void printItem(int pantryID, int itemID) {
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

        int userInput = Navigation.getUserInputInt(1, 3);
        switch (userInput) {
            case 1:
                // delete an item                         
            case 2:
                // remove an item
            case 3:
                if (pantryID < PantryService.getRange()) {
                    printPantryPage();
                } else {
                    printShoppingCartPage();
                }
        }
    }

    public static void printSearchResults(String query, ArrayList<Object> list) {
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

    public static void printPantryList(PantryType type, AccessContext context) {
        Navigation.clearConsole();
        int listSize = 1;

        if (Kitchen.inventory.isEmpty()) {
            System.out.println("You do not currently have any " + type.name().replace("_", " ").toLowerCase() + "s. Return to the previous menu to create one!");
        } else {
            System.out.println("Choose a " + type.name().replace("_", " ").toLowerCase() + " to " + context.name().toLowerCase() + ".\n");
            for (int i = 0; i < Kitchen.inventory.size(); i++) {
                System.out.println(i + 1 + ". " + Kitchen.inventory.get(i).getPantryName());
                listSize = Kitchen.inventory.size() + 1;
            }
        }

        System.out.println("\n" + listSize + ". Go Back");

        int userInput = Navigation.getUserInputInt(1, listSize);

        if (Kitchen.inventory.isEmpty()) {
            switch (type) {
                case PANTRY:
                    printPantryPage();
                case SHOPPING_CART:
                    printShoppingCartPage();
            }
        } else {
            switch (context) {
                case DISPLAY:
                    viewItemList(PantryType.PANTRY, Kitchen.inventory.get(userInput - 1).getPantryID());
                case REMOVE:
                    Controller.deletePantry(Kitchen.inventory.get(userInput - 1).getPantryID());
        }
        }
    }

    public static void viewItemList(PantryType type, int pantryToModify) {
        Navigation.clearConsole();
        int listSize = 1;
        Pantry pantry = Kitchen.retrievePantry(pantryToModify);
        System.out.println("Items in " + type.name().replace("_", " ").toLowerCase() + " named " + pantry.getPantryName() + 
        "\nChoose an item to view/edit its contents.\n");

        if (pantry.items.isEmpty()) {
            System.out.println("This " + type.name().replace("_", " ").toLowerCase() + " is empty!");
        } else {
            for (int i = 0; i < pantry.items.size(); i++) {
                System.out.print(i + 1 + ". " + pantry.items.get(i).getName() + "    " + "Quantity: " + pantry.items.get(i).getQuantity() + "    " + "Date Added: " + pantry.items.get(i).getDateAdded().getTime());
                if (pantry.items.get(i).getExpirationDate() != null) {
                    System.out.println("    Expires: " + pantry.items.get(i).getExpirationDate().getTime());
                }
            }
        }
        
        System.out.println("\n" + listSize + ". Go Back");
    }

    // Clears the screen for printing new menus
    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static int getUserInputInt(int min, int max) {
        int userInput = -1;

        try {
            do {
                System.out.print("Your input: ");
                while (!Controller.universalScanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter an integer.");
                    System.out.print("Your input: ");
                    Controller.universalScanner.next();
                }
                userInput = Controller.universalScanner.nextInt();

                if (userInput < min || userInput > max) {
                    System.out.printf("Input out of range. Please enter an integer between %d and %d.\n", min, max);
                }
            } while (userInput < min || userInput > max);
        } catch (NoSuchElementException e) {
            System.err.println("No input was given. Exiting.");
        } catch (IllegalArgumentException e) {
            System.err.println("Illegal input was given. Exiting.");
        }

        return userInput;
    }

    public static String getUserInputString(boolean allowSpaces, int maxLength) {
        String validChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!,.?#()";
        if (allowSpaces) {
            validChars += " ";
        }

        System.out.print("Your Input: ");
        String userInput = "";
        boolean isValid;

        try {
            do {
                userInput = Controller.universalScanner.nextLine();

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
        } catch (NoSuchElementException e) {
            System.err.println("No input was given. Exiting.");
        } catch (IllegalArgumentException e) {
            System.err.println("Illegal input was given. Exiting.");
        }

        return userInput;
    }

    public static Calendar getUserInputDate(boolean includeHoursMinutes) {
        try {
            System.out.println("Enter year.");
            int year = getUserInputInt(2000,3000);

            System.out.println("Enter month. (1-12)");
            int month = getUserInputInt(1,12);

            int numDaysInMonth = getNumDaysInMonth(month); // calculate day range for selected month
            System.out.println("Enter day of the month. (The month you selected has " + numDaysInMonth + " days.)");
            int day = getUserInputInt(1, numDaysInMonth);

            int hour = 0;
            int minute = 0;
            if (includeHoursMinutes) {
                System.out.println("Enter hour. (0-23)");
                hour = getUserInputInt(0,23);

                System.out.println("Enter minute. (0-59)");
                minute = getUserInputInt(0,59);
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

        return calendar;

        } catch (NoSuchElementException e) {
            System.err.println("No input was given. Exiting.");
        } catch (IllegalArgumentException e) {
            System.err.println("Illegal input was given. Exiting.");
        } catch (NullPointerException e) {
            System.err.println("Input was null. Exiting.");
        }

        return Calendar.getInstance(); // unreachable but necessary statement
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

        String testString = getUserInputString(true, 30);
        System.out.println(testString);

        int testInt = getUserInputInt(12,100);
        System.out.println(testInt);
    }
}
