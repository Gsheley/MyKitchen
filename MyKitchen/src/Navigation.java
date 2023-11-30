public class Navigation {
    NotificationService ns = new NotificationService();

    public void printHomePage() {
        System.out.println("Welcome to MyKitchen!\n");
        if (ns.checkForNotifications()) {
            System.out.println("You have new notification(s)!");
        } else {
            System.out.println("You have no new notifications.");
        }
        System.out.println("\nSelect a navigation option below.");
    }

    public void printPantryPage() {

    }

    public void printShoppingCartPage() {

    }

    public void printCookbookPage() {

    }

    public void printItem() {

    }

    public void printSearchResults() {
        
    }

    // Clears the screen for printint new menuse
    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
