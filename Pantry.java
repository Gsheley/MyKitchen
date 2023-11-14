import java.util.ArrayList;
import java.util.Date;

public class Pantry {
    private int pantryID;
    private int currentItemID = 0;
    private String name;
    public ArrayList<Item> items; 

    public Pantry(int pantryID, String name)
    {
        this.pantryID = pantryID;
        this.name = name;
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
   
    public void editItem()
    {

    }
}
