import java.util.ArrayList;

public class SortByCreationDate implements Sort 
{
    public ArrayList<Item> sort(ArrayList<Item> list)
    {
        for (int i = 1; i < list.size(); ++i)
         {
            Item curr = (Item)list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j).getDateAdded().compareTo(list.get(i).getDateAdded()) > 0) 
            {
                list.set(j+1, list.get(j));
                j--;
            }
            list.set(j+1,curr);
        }
        return list;
    }   

}
