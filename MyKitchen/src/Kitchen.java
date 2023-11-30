import java.util.Calendar;

public class Kitchen 
{
    public Pantry inventory ;
    public Pantry shoppingCart;
    public Cookbook myCookbook;

    public Pantry createPantry(String name){
        Pantry newPantry = new Pantry(0,name);
        return newPantry;
    }

    public Pantry retrievePantry(int pantryID){
        return inventory;
    }

    public void addItem(String name, Calendar dateAdded, int quantity){

    }

    public void addItem(String name, Calendar dateAdded, int quantity, Calendar expirDate){

    }

    public void editItem(int itemID, String name, Calendar dateAdded, int quantity, Calendar expirDate){

    }

    public void editItem(int itemID, String name, Calendar dateAdded, int quantity){

    }
}