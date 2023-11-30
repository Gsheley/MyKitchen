import java.util.ArrayList;
import java.util.Calendar;

public class SortByExpirationDate implements Sort 
{
    public ArrayList<Object> sort(ArrayList<Object> list)
    {
        //Collections.sort(list, new ItemExDateComparator());
        for (int i = 1; i < list.size(); ++i)
         {
            Item curr = (Item)list.get(i);
            //int key = ((Item)list.get(i)).getQuantity();
            int j = i - 1;
            while (j >= 0 && ((Item)list.get(j)).getExpirationDate().compareTo(((Item)list.get(i)).getExpirationDate()) > 0) 
            {
                list.set(j+1, list.get(j));
                j--;
            }
            list.set(j+1,curr);
        }
        return list;
    }   
}

