import java.util.ArrayList;

public abstract class List {
    abstract void deleteList();
    abstract boolean saveList();

    private int id = 0;
    protected String name = "";
    public ArrayList<Item> items = new ArrayList<Item>();

    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}