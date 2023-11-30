import java.util.ArrayList;
import java.util.Calendar;

public class Kitchen 
{
    public ArrayList<Pantry> inventory = new ArrayList<Pantry>();
    public ArrayList<Pantry> shoppingCart = new ArrayList<Pantry>();
    public Cookbook myCookbook;

    public Pantry createPantry(String name){
        return PantryService.createPantry(name);
    }

    public Pantry retrievePantry(int pantryID){
        //checks each recipe in the recipes array list
        Pantry foundPantry = null;
        for(int i = 0; i < Pantry.PantryList.size(); i++) {
            Pantry curPantry = (PantryList.get(i)).pantryID;
            if(curPantry.equals(pantryID)){
                foundPantry = PantryList.get(i);
            }
        }
        return foundPantry;
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