import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Navigation {
    NotificationService ns = new NotificationService();
    Kitchen kitchen = Controller.kitchen;

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
    }

    public void printPantryPage() {
        Navigation.clearConsole();
        System.out.println("Pantry Menu\n");
        System.out.println("\nSelect a navigation option below.\n" +
        "1. View/Edit Contents of a Pantry\n" +
        "2. Create a New Pantry\n" +
        "3. Remove an Existing Pantry\n" +
        "\n" +
        "4. Go Back\n");
    }

    public void printShoppingCartPage() {
        Navigation.clearConsole();
        System.out.println("Shopping Cart Menu\n");
        System.out.println("\nSelect a navigation option below.\n" +
        "1. View/Edit Contents of a Shopping Cart\n" +
        "2. Create a New Shopping Cart\n" +
        "3. Remove an Existing Shopping Cart\n" +
        "\n" +
        "4. Go Back\n");
    }

    public void printCookbookPage() {
        Navigation.clearConsole();
        Navigation.clearConsole();
        System.out.println("Cookbook Menu\n");
        System.out.println("\nSelect a navigation option below.\n" +
        "1. View Recipes in Cookbook\n" +
        "2. Find New Recipes\n" +
        "\n" +
        "3. Go Back\n");
    }

    public void printRecipe(int recipeID) {
        Navigation.clearConsole();
        //ArrayList<Recipe> recipeList = Cookbook.recipes;
        // Recipe needs to be finished for implementation
    }

    public void printItem(int pantryID, int itemID) {
        Navigation.clearConsole();
        //ArrayList<Item> itemList = kitchen.getPantryByID(pantryID);
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

    // Clears the screen for printing new menus
    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static int getUserInputInt(int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int userInput;

        do {
            System.out.print("Your input: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }
            userInput = scanner.nextInt();

            if (userInput < min || userInput > max) {
                System.out.printf("Input out of range. Please enter an integer between %d and %d.\n", min, max);
            }
        } while (userInput < min || userInput > max);

        scanner.close();
        return userInput;
    }

    public static String getUserInputString(boolean allowSpaces) {
        Scanner scanner = new Scanner(System.in);
        String validChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!,.?#()";
        if (allowSpaces) {
            validChars += " ";
        }

        System.out.print("Your Input: ");
        String userInput;
        boolean isValid;

        do {
            userInput = scanner.nextLine();

            isValid = true;
            for (char c : userInput.toCharArray()) {
                if (validChars.indexOf(c) == -1) {
                    isValid = false;
                    break;
                }
            }

            if (!isValid) {
                System.out.println("Invalid input. Your input may contain only alphanumeric"
                                   + (allowSpaces ? " and space" : "") + " characters. Also accepted: !,.?#()");
            }
        } while (!isValid);

        scanner.close();
        return userInput;
    }

    public static Calendar getUserInputDate() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter year.");
        int year = getUserInputInt(2000,3000);

        System.out.println("Enter month. (1-12)");
        int month = getUserInputInt(1,12);

        int numDaysInMonth = getNumDaysInMonth(month); // calculate day range for selected month
        System.out.println("Enter day of the month. (The month you selected has " + numDaysInMonth + " days.)");
        int day = getUserInputInt(1, numDaysInMonth);

        System.out.println("Enter hour. (0-23)");
        int hour = getUserInputInt(0,23);

        System.out.println("Enter minute. (0-59)");
        int minute = getUserInputInt(0,59);

        // Create a Calendar instance and set the provided values
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1); // Calendar months are zero-based (January is 0)
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        scanner.close();

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
        Navigation nv = new Navigation();
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
    }
}
