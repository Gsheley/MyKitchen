import java.util.Calendar;

public class Controller {
    static Kitchen kitchen = new Kitchen();
    NotificationService ns = new NotificationService();
    int numKitchens = 0;
    int numCarts = 0;
    public static void main(String[] args) 
    {
        Navigation nv = new Navigation();
        nv.printHomePage();
    }

    public void deletePantry(int id) 
    {
        kitchen.deletePantry(id);
    }

    public void createPantry() 
    {
        Kitchen.createPantry(PantryType.KITCHEN_INVENTORY,Navigation.getUserInputString(false));
    }

    public void createCart() 
    {
        // Call to shared scanner class to ge the name of the new shopping cart
        Kitchen.createPantry(PantryType.SHOPPING_CART, Navigation.getUserInputString(false));
    }

    public void deleteCart(int id) 
    {
        kitchen.deleteCart(id);
    }

    public void addItem(String name, int quantity, Calendar expirationDate) 
    {

    }

    public int getNumKitchens() {
        return numKitchens;
    }

    public int getNumCarts() {
        return numCarts;
    }

    public void setNumKitchens(int num) {
        numKitchens = num;
    }

    public void setNumCarts(int num) {
        numCarts = num;
    }
}