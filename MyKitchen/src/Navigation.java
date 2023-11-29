public class Navigation {
    NotificationService ns = new NotificationService();

    public void printHomePage() {
        Controller.print("Welcome to MyKitchen!\n");
        if (ns.checkForNotifications()) {
            Controller.print("You have new notification(s)!");
        } else {
            Controller.print("You have no new notifications.");
        }
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
