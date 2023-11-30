import java.util.ArrayList;
import java.util.Calendar;

public class SortByCreationDate implements Sort 
{
    public ArrayList<Object> sort(ArrayList<Object> list)
    {
        for (int i = 1; i < list.size(); ++i)
         {
            Item curr = (Item)list.get(i);
            //int key = ((Item)list.get(i)).getQuantity();
            int j = i - 1;
            while (j >= 0 && ((Item)list.get(j)).getDateAdded().compareTo(((Item)list.get(i)).getDateAdded()) > 0) 
            {
                list.set(j+1, list.get(j));
                j--;
            }
            list.set(j+1,curr);
        }
        return list;
    }   

}
