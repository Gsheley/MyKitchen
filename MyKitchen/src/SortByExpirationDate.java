import java.util.ArrayList;
import java.util.Collections;

public class SortByExpirationDate implements Sort 
{
    public ArrayList<Item> sort(ArrayList<Item> list)
    {
        Collections.sort(list, new ItemExDateComparator());
        return list;
    }   
}

//Comparator class for comparing by ExpirationDate
class ItemExDateComparator implements java.util.Comparator<Item>
{
    public int compare(Item a, Item b)
    {
        int result = a.getExpirationDate().compareTo(b.getExpirationDate());
        return result;
    }
}