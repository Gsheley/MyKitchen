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
        // Recipe needs to be finished for implementation
    }

    public void printItem(int itemID) {
        Navigation.clearConsole();
    }

    public void printSearchResults() {
        
    }

    // Clears the screen for printing new menus
    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
