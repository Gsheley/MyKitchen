import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

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
        ArrayList<Recipe> recipeList = Cookbook.recipes;
        // Recipe needs to be finished for implementation
    }

    public void printItem(int pantryID, int itemID) {
        Navigation.clearConsole();
    }

    public void printSearchResults(String query, ArrayList<Object> list) {
        ArrayList<Object> searchResults = Search.search(query, list);

        if(list.get(0) instanceof Item) {
            ArrayList<Item> pantryResults = new ArrayList<Item>();

            for (Object obj : searchResults) {
                // Typecasting each Object to Item and adding it to the new ArrayList
                Item item = (Item) obj;
                pantryResults.add(item);
            }
            for (int i = 0; i < pantryResults.size(); i++) {
                System.out.println(pantryResults.get(i).getName());
            }
        } else if (list.get(0) instanceof Recipe) {
            ArrayList<Recipe> cookbookResults = new ArrayList<Recipe>();

            for (Object obj : searchResults) {
                // Typecasting each Object to Recipe and adding it to the new ArrayList
                Recipe recipe = (Recipe) obj;
                cookbookResults.add(recipe);
            }
            for (int i = 0; i <= cookbookResults.size(); i++) {
                //System.out.println(cookbookResults[i].getRecipeName());
            }
        }
    }

    // Clears the screen for printing new menus
    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void main(String args[]) {
        Navigation nv = new Navigation();
        ArrayList<Object> items = new ArrayList<Object>();
        Item testItem1 = new Item(1,"test", Calendar.getInstance(),12);
        Item testItem2 = new Item(1,"apple", Calendar.getInstance(),12);
        Item testItem3 = new Item(1,"banana", Calendar.getInstance(),12);
        Item testItem4 = new Item(1,"pear", Calendar.getInstance(),12);
        items.add(testItem1);
        items.add(testItem2);
        items.add(testItem3);
        items.add(testItem4);

        nv.printSearchResults("app", items);
    }
}
