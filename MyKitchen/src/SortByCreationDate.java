import java.util.ArrayList;
import java.util.Collections;

public class SortByCreationDate implements Sort 
{
    public ArrayList<Item> sort(ArrayList<Item> list)
    {
        Collections.sort(list, new ItemCreationDateComparator());
        return list;
    }   

}

//Comparator class for comparing dateAdded
class ItemCreationDateComparator implements java.util.Comparator<Item>
{
    public int compare(Item a, Item b)
    {
        int result = a.getDateAdded().compareTo(b.getDateAdded());
        return result;
    }
}

