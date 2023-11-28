import java.util.ArrayList;
import java.util.Collections;

public class SortByCreationDate implements Sort 
{
    public ArrayList<Item> sort(ArrayList<Item> list)
    {
        Collections.sort(list, new ItemCreationDateComparator());
        return list;
    }   

//  public static void main(String[] args) 
// {
//     Calendar date1 =  Calendar.getInstance();
//     date1.set(1999, 8, 22);
//     Calendar date2 =  Calendar.getInstance();
//     Calendar date3 =  Calendar.getInstance();
//     date3.set(2005, 12, 2);

//     Item item1 = new Item(1234, "apple", date1, 2);
//     Item item2 = new Item(1234, "bannana", date2, 7);
//     Item item3 = new Item(1234, "taco", date3, 1);
//     ArrayList<Item> items = new ArrayList<>();
//     items.add(item1);
//     items.add(item2);
//     items.add(item3);

//     SortByCreationDate sort1 = new SortByCreationDate();
//     sort1.sort(items);

//     for(int i =0; i < items.size(); i++)
//     {
//         System.out.println(items.get(i).getName());
//     }
// }

}

class ItemCreationDateComparator implements java.util.Comparator<Item>
{
    public int compare(Item a, Item b)
    {
        int result = a.getDateAdded().compareTo(b.getDateAdded());
        return result;
    }
}

