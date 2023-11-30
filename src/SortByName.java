import java.util.ArrayList;
import java.util.Calendar;

public class SortByName implements Sort 
{
    public ArrayList<Object> sort(ArrayList<Object> list)
    {
        //Collections.sort(list, new ItemNameComparator());
        for (int i = 1; i < list.size(); ++i)
         {
            Item curr = (Item)list.get(i);
            String key = ((Item)list.get(i)).getName();
            int j = i - 1;
            while (j >= 0 && key.compareTo(((Item)list.get(j)).getName()) < 0) 
            {
                list.set(j+1, list.get(j));
                j--;
            }
            list.set(j+1,curr);
        }
        return list;
    }   
}

