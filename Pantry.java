import java.util.ArrayList;
import java.util.Date;

public class Pantry {
    private int id;
    protected String name;
    public ArrayList<Item> items; 

    public Pantry(String name)
    {
        this.name = name;
    }

    public Pantry retrievePantry()
    {
        return null;
    }

    public void addItem(String name, Date dateAdded, int quantity, Date expirDate)
    {
        items.add(new Item());
    }
    //rprfwjerpjfprej
    public void editItem()
    {

    }
}
