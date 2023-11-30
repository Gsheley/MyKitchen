import java.util.ArrayList;
import java.util.Collections;

public class SortByName implements Sort 
{
    public ArrayList<Item> sort(ArrayList<Item> list)
    {
        Collections.sort(list, new ItemNameComparator());
        return list;
    }   
}

//Compartor for comparing by name
class ItemNameComparator implements java.util.Comparator<Item>
{
    public int compare(Item a, Item b)
    {
        int result = a.getName().compareTo(b.getName());
        return result;
    }
}
