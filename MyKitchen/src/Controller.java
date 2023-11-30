import java.util.Date;

public class Controller {
    public static void main(String[] args) 
    {
        Navigation nv = new Navigation();
        nv.printHomePage();
    }

    int numKitchens = 0;
    int numCarts = 0;

    public void deletePantry(int id) 
    {

    }

    public void createPantry(int id) 
    {

    }

    public void createCart() 
    {

    }

    public void deleteCart(int id) 
    {

    }

    public void addItem(String name, int quantity, Date expirationDate) 
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