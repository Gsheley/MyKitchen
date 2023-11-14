import java.util.ArrayList;
import java.util.Date;

public class Pantry {
    private int PantryID;
    private int currentItemID = 0;
    private String name;
    public ArrayList<Item> items; 

    public Pantry(String name)
    {
        this.name = name;
    }

    public Pantry retrievePantry()
    {
        return this;
    }

    public void addItem(String name, Date dateAdded, int quantity, Date expirDate)
    {
        items.add(new Item(currentItemID++, name, ));
    }
   
    public void editItem()
    {

    }
}
