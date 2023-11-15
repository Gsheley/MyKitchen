import java.util.ArrayList;
import java.util.Date;

public class Pantry {
    private int pantryID;
    private int currentItemID = 0;
    private String pantryName;
    public ArrayList<Item> items; 

    public Pantry(int pantryID, String pantryName)
    {
        this.pantryID = pantryID;
        this.pantryName = pantryName;
    }

    public Pantry retrievePantry()
    {
        return this;
    }

    public void addItem(String name, Date dateAdded, int quantity, Date expirDate)
    {
        items.add(new Item(currentItemID++, name, dateAdded, quantity, expirDate));
    }

    public void addItem(String name, Date dateAdded, int quantity) 
    {
        items.add(new Item(currentItemID++, name, dateAdded, quantity));
    }
   
    public void editItem(int itemID, String name, Date dateAdded, int quantity, Date expirDate)
    {
        for (Item item : items) {
            if (item.getItemID)
        }
    }

    public void editItem(int itemID, String name, Date dateAdded, int quantity)
    {

    }

    public int getPantryID() 
    {
        return pantryID;
    }

    public String getPantryName() 
    {
        return pantryName;
    }

    public void setPantryName(String pantryName) 
    {
        this.pantryName = pantryName;
    }
}
